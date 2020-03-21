/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.hotel.Hotel;
import io.crs.hsys.server.entity.hotel.RateCode;
import io.crs.hsys.server.entity.hotel.RoomRate;
import io.crs.hsys.server.entity.hotel.RoomRateByDate;
import io.crs.hsys.server.entity.hotel.RoomType;
import io.crs.hsys.server.repository.HotelRepository;
import io.crs.hsys.server.repository.RateCodeRepository;
import io.crs.hsys.server.repository.RoomRateRepository;
import io.crs.hsys.server.repository.RoomTypeRepository;
import io.crs.hsys.server.service.RoomRateService;
import io.crs.hsys.shared.cnst.RatePriceType;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.dto.rate.RateRestrictionDto;
import io.crs.hsys.shared.dto.rate.update.RoomRateOperationDto;
import io.crs.hsys.shared.dto.rate.update.RoomRateUpdateDto;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;
import io.crs.hsys.shared.util.DateUtils;

/**
 * @author robi
 *
 */
public class RoomRateServiceImpl implements RoomRateService {
	private static final Logger logger = LoggerFactory.getLogger(RoomRateServiceImpl.class.getName());

	private final RoomRateRepository roomRateRepository;
	private final HotelRepository hotelRepository;
	private final RoomTypeRepository roomTypeRepository;
	private final RateCodeRepository rateCodeRepository;

	public RoomRateServiceImpl(RoomRateRepository roomRateRepository, HotelRepository hotelRepository,
			RoomTypeRepository roomTypeRepository, RateCodeRepository rateCodeRepository) {
		logger.info("RoomRateServiceImpl");
		this.roomRateRepository = roomRateRepository;
		this.hotelRepository = hotelRepository;
		this.roomTypeRepository = roomTypeRepository;
		this.rateCodeRepository = rateCodeRepository;
	}

	@Override
	public void update(RoomRateUpdateDto rateUpdateDto) throws EntityValidationException, UniqueIndexConflictException {
		logger.info("update");
		RateCode rateCode = rateCodeRepository.findByWebSafeKey(rateUpdateDto.getRateCode().getWebSafeKey());
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("rateCode", rateCode);

		// Az ár módosításban szereplő évszámok alapján kigyűjtjük az érintett
		// RateByYear entitásokat
		for (RoomTypeDtor roomTypeDtor : rateUpdateDto.getRoomTypes()) {
			RoomType roomType = roomTypeRepository.findByWebSafeKey(roomTypeDtor.getWebSafeKey());
			filters.remove("roomType");
			filters.put("roomType", roomType);
			logger.info("update->roomType=" + roomTypeDtor.getWebSafeKey());
			logger.info("update->hotel=" + rateUpdateDto.getHotel().getWebSafeKey());
			filters.forEach((k, v) -> logger.info("update->filters= " + "Key : " + k + " Value : " + v));

			RoomRate roomRate = roomRateRepository.getChildByFilters(rateUpdateDto.getHotel().getWebSafeKey(), filters);
			if (roomRate == null) {
				logger.info("update->roomRate == null");
				roomRate = new RoomRate();

				Hotel hotel = hotelRepository.findByWebSafeKey(rateUpdateDto.getHotel().getWebSafeKey());
				logger.info("update->hotel=" + hotel);
				roomRate.setHotel(hotel);

				logger.info("update->roomType=" + roomType);
				roomRate.setRoomType(roomType);

				logger.info("update->rateCode=" + rateCode);
				roomRate.setRateCode(rateCode);
			}
			roomRate.setRoomRatesByDate(roomRateSync(roomRate.getRoomRatesByDate(), rateUpdateDto));
			roomRateRepository.save(roomRate);
		}
	}

	private Map<Date, RoomRateByDate> roomRateSync(Map<Date, RoomRateByDate> roomRates, RoomRateUpdateDto updateDto) {
		logger.info("roomRateSync-START");
		roomRates.forEach((k, v) -> logger.info("roomRateSync->roomRates= " + "Key : " + k + " Value : " + v));
		Date tempDate = updateDto.getFromDate();
		Date toDateX = DateUtils.addDay(updateDto.getToDate(), 1);
		while (tempDate.before(toDateX)) {
			logger.info("roomRateSync-while->tempDate=" + tempDate);
			if (!checkWeekDay(updateDto, tempDate)) {
				logger.info("roomRateSync-continue");
				tempDate = DateUtils.addDay(tempDate, 1);
				continue;
			}
			RoomRateByDate rrbd = roomRates.get(tempDate);
			if (rrbd == null) {
				logger.info("roomRateSync-not exists->tempDate=" + tempDate);
				rrbd = new RoomRateByDate();
				roomRates.put(tempDate, rrbd);
			}
			logger.info("roomRateSync-calcRoomRate");
			rrbd = calcRoomRate(rrbd, updateDto.getOperations(), updateDto.getRestrictions());
//			roomRates.replace(tempDate, rrbd);
			roomRates.put(tempDate, rrbd);
			logger.info("roomRateSync-go");
			tempDate = DateUtils.addDay(tempDate, 1);
		}
		logger.info("roomRateSync-END");
		roomRates.forEach((k, v) -> logger.info("roomRateSync->roomRates= " + "Key : " + k + " Value : " + v));
		return roomRates;
	}

	private boolean checkWeekDay(RoomRateUpdateDto updateDto, Date date) {
		logger.info("checkWeekDay-START");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (calendar.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			return updateDto.getDay1();
		case 2:
			return updateDto.getDay2();
		case 3:
			return updateDto.getDay3();
		case 4:
			return updateDto.getDay4();
		case 5:
			return updateDto.getDay5();
		case 6:
			return updateDto.getDay6();
		case 7:
			return updateDto.getDay7();
		}
		logger.info("checkWeekDay-END");
		return false;
	}

	private RoomRateByDate calcRoomRate(RoomRateByDate rrbd, List<RoomRateOperationDto> operations,
			List<RateRestrictionDto> restrictions) {
		logger.info("calcRoomRate-START");
		for (RoomRateOperationDto operation : operations) {
			logger.info("calcRoomRate-OPERATION=" + operation.getOperation());
			logger.info("calcRoomRate-TYPE=" + operation.getType());
			logger.info("calcRoomRate-VALUE=" + operation.getValue());
			switch (operation.getOperation()) {
			case NO_CHANGE:
				break;
			case SET_AMOUNT:
				rrbd.setRoomRates(setAmount(rrbd.getRoomRates(), operation.getType(), operation.getValue()));
				break;
			case INC_BY_AMOUNT:
				rrbd.setRoomRates(modifyByAmount(rrbd.getRoomRates(), operation.getType(), operation.getValue()));
				break;
			case DEC_BY_AMOUNT:
				rrbd.setRoomRates(modifyByAmount(rrbd.getRoomRates(), operation.getType(), -operation.getValue()));
				break;
			case INC_BY_PERCENT:
				rrbd.setRoomRates(modifyByPercent(rrbd.getRoomRates(), operation.getType(), operation.getValue()));
				break;
			case DEC_BY_PERCENT:
				rrbd.setRoomRates(modifyByPercent(rrbd.getRoomRates(), operation.getType(), -operation.getValue()));
				break;
			}
		}
		return rrbd;
	}

	private Map<RatePriceType, Double> setAmount(Map<RatePriceType, Double> roomRates, RatePriceType type,
			Double value) {
		logger.info("setAmount-START");
		logger.info("setAmount->replace(" + type + ", " + value + ")");
		roomRates.put(type, value);
/*
		Double temp = roomRates.replace(type, value);
		if (temp == null) {
			logger.info("setAmount-NOT EXISTS");
			logger.info("setAmount->put(" + type + ", " + value + ")");
			roomRates.put(type, value);
		}
*/		
		logger.info("setAmount-END");
		return roomRates;
	}

	private Map<RatePriceType, Double> modifyByAmount(Map<RatePriceType, Double> roomRates, RatePriceType type,
			Double value) {
		Double price = roomRates.get(type);
		if (price == null) {
			price = 0d;
			roomRates.put(type, price);
		}
		price = price + value;
		roomRates.replace(type, price);
		return roomRates;
	}

	private Map<RatePriceType, Double> modifyByPercent(Map<RatePriceType, Double> roomRates, RatePriceType type,
			Double value) {
		Double percent = 100 + value;
		Double price = roomRates.get(type);
		if (price == null) {
			price = 0d;
			roomRates.put(type, price);
		}
		price = price * percent;
		roomRates.replace(type, price);
		return roomRates;
	}
}
