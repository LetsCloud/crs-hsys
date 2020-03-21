/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.hotel.RateCode;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.HotelRepository;
import io.crs.hsys.server.repository.RateCodeRepository;
import io.crs.hsys.server.service.RateCodeService;

/**
 * @author robi
 *
 */
public class RateCodeServiceImpl extends HotelChildServiceImpl<RateCode, RateCodeRepository>
		implements RateCodeService {
	private static final Logger logger = LoggerFactory.getLogger(RateCodeServiceImpl.class.getName());

	public RateCodeServiceImpl(RateCodeRepository repository, AccountRepository accountRepository,
			HotelRepository hotelRepository) {
		super(repository, accountRepository, hotelRepository);
		logger.info("RoomTypeServiceImpl");
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
}
