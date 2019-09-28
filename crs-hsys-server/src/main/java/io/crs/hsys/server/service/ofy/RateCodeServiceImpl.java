/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.hotel.RateCode;
import io.crs.hsys.server.entity.hotel.RoomRate;
import io.crs.hsys.server.entity.hotel.RoomRateByDate;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.HotelRepository;
import io.crs.hsys.server.repository.RateByYearRepository;
import io.crs.hsys.server.repository.RateCodeRepository;
import io.crs.hsys.server.service.RateCodeService;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.dto.rate.update.RoomRateOperationDto;
import io.crs.hsys.shared.dto.rate.update.RoomRateUpdateDto;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
public class RateCodeServiceImpl extends HotelChildServiceImpl<RateCode, RateCodeRepository>
		implements RateCodeService {
	private static final Logger logger = LoggerFactory.getLogger(RateCodeServiceImpl.class.getName());

	private RateByYearRepository rateByYearRepository;

	public RateCodeServiceImpl(RateCodeRepository repository, AccountRepository accountRepository,
			HotelRepository hotelRepository, RateByYearRepository rateByYearRepository) {
		super(repository, accountRepository, hotelRepository);
		logger.info("RoomTypeServiceImpl");
		this.rateByYearRepository = rateByYearRepository;
	}

	@Override
	public List<RateCode> getChildren(String parentWebSafeKey) {
		List<RateCode> roomTypes = repository.getChildren(parentWebSafeKey);
		return roomTypes;
	}

	@Override
	public List<RateCode> getChildrenByFilters(String parentWebSafeKey, Map<String, Object> filters) {
		List<RateCode> roomTypes = super.getChildrenByFilters(parentWebSafeKey, filters);
		return roomTypes;
	}

	@Override
	public void updateRate(RoomRateUpdateDto rateUpdateDto)
			throws EntityValidationException, UniqueIndexConflictException {
		Map<String, Object> filters = new HashMap<String, Object>();

		filters.remove("rateCode");
		filters.put("rateCode", rateUpdateDto.getRateCode().getWebSafeKey());

		// Az ár módosításban szereplő évszámok alapján kigyűjtjük az érintett
		// RateByYear entitásokat
		for (RoomTypeDtor roomTypeDtor : rateUpdateDto.getRoomTypes()) {
			filters.remove("roomType");
			filters.put("roomType", roomTypeDtor.getWebSafeKey());

			List<RoomRate> roomRates = rateByYearRepository
					.getChildrenByFilters(rateUpdateDto.getHotel().getWebSafeKey(), filters);
			for (RoomRate roomRate : roomRates) {
				roomRateSync(roomRate.getRoomRatesByDate(), rateUpdateDto.getRoomRateOperations());
				rateByYearRepository.save(roomRate);
			}
		}
	}

	private Map<Date, RoomRateByDate> roomRateSync(Map<Date, RoomRateByDate> roomRatesByDate,
			Map<Date, RoomRateOperationDto> roomRateOperations) {

		return roomRatesByDate;
	}

}
