/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.hotel.Rate;
import io.crs.hsys.server.entity.hotel.RateByRoomType;
import io.crs.hsys.server.entity.hotel.RateByYear;
import io.crs.hsys.server.entity.hotel.RateCode;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.HotelRepository;
import io.crs.hsys.server.repository.RateByYearRepository;
import io.crs.hsys.server.repository.RateCodeRepository;
import io.crs.hsys.server.service.RateCodeService;
import io.crs.hsys.shared.dto.hotel.RateDto;
import io.crs.hsys.shared.dto.hotel.RateUpdateDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;
import io.crs.hsys.shared.util.DateUtils;

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
	public void updateRate(RateUpdateDto dto) throws EntityValidationException, UniqueIndexConflictException {

		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("rateCode", dto.getRateCode().getWebSafeKey());
		filters.put("currency", dto.getCurrency().getWebSafeKey());

		// Végig járjuk a megküldött ár módosításokat
		for (RateDto rateDto : dto.getRates()) {
			int yearFrom = DateUtils.getYearOfDate(rateDto.getFromDate());
			int yearTo = DateUtils.getYearOfDate(rateDto.getToDate());
			// Az ár módosításban szereplő évszámok alapján kigyűjtjük az érintett
			// RateByYear entitásokat
			for (int i = yearFrom; i <= yearTo; i++) {
				filters.remove("year");
				filters.put("year", i);
				List<RateByYear> rateByYears = rateByYearRepository
						.getChildrenByFilters(dto.getRateCode().getHotel().getWebSafeKey(), filters);
				for (RateByYear rateByYear : rateByYears) {
					// Mivel több RateByYear entitás is lehet szobatípusonként vizsgáljuk
					// meg őket
					if (dto.getRoomTypes().isEmpty()) {
						for (RateByRoomType rateByRoomType : rateByYear.getRatesByRoomType()) {
							rateSync(rateByRoomType.getRates(), rateDto);
						}
					} else {
						for (RoomTypeDtor roomTypeDtor : dto.getRoomTypes()) {
							// Kikeressük szobatípusonként éérintett árakat
							List<RateByRoomType> ratesByRoomType = rateByYear.getRatesByRoomType().stream()
									.filter(o -> o.getRoomType().getWebSafeKey().equals(roomTypeDtor.getWebSafeKey()))
									.collect(Collectors.toList());
							for (RateByRoomType rateByRoomType : ratesByRoomType) {
								rateSync(rateByRoomType.getRates(), rateDto);
							}
						}
					}
					rateByYearRepository.save(rateByYear);
				}
			}
		}
	}

	private List<Rate> rateSync(List<Rate> rates, RateDto rateDto) {
		// Ha van átfedés a tárolt árakkal, akkor eltávolítjuk azokat
		Iterator<Rate> it = rates.iterator();
		while (it.hasNext()) {
			Rate rate2 = it.next(); // must be called before you can call i.remove()
			if (rate2.getFromDate().compareTo(rateDto.getFromDate()) < 0) {
				// Tárolt ár kezdete kisebb mint a moódosítás kezdete
				if (rate2.getToDate().compareTo(rateDto.getToDate()) > 0) {
					// Ha a tárolt ár vége nagyobb mint a moódosítás vége, akkor kivágjuk a
					// módosított időzakot.
					// Azaz hozzáadjuk a tárolt ár túllógó végét,
					rates.add(new Rate(DateUtils.addDay(rateDto.getToDate(), 1), rate2.getToDate(),
							rate2.getRestrictions(), rate2.getRates()));
					// és visszavesszük az eredeti rekord végét.
					rate2.setToDate(DateUtils.addDay(rateDto.getFromDate(), -1));
				} else if (rate2.getToDate().compareTo(rateDto.getFromDate()) >= 0) {
					// Ha a tárolt ár vége nagyobb vagy egyenlő mint a moódosítás vége, akkor
					// levágjuk a tárol ár végét.
					rate2.setToDate(DateUtils.addDay(rateDto.getFromDate(), -1));
				}

			} else {
				if (rate2.getToDate().compareTo(rateDto.getFromDate()) <= 0) {
					// Ha tárolt ár vége kisebb vagy egyenlő mint a módosítás vége, akkor a
					// tárolt ár teljes mértékben bele esik a módosítás időszakába és törölni
					// kell
					it.remove();
				} else if (rate2.getFromDate().compareTo(rateDto.getFromDate()) <= 0) {
				}
				// Ha tárolt ár kezdete kisebb vagy egyenlő mint a módosítás vége, akkor a
				// tárolt ár eleje belelóg a módosításba, ezért ezt a részt le kell vágni
				rate2.setFromDate(DateUtils.addDay(rateDto.getToDate(), 1));
			}
		}
		// Vegül beszúrjuk a módosítást
		rates.add(new Rate(rateDto));

		return rates;
	}

}
