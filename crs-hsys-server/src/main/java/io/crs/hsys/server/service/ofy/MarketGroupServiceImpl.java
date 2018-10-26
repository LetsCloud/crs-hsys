/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.reservation.MarketGroup;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.HotelRepository;
import io.crs.hsys.server.repository.MarketGroupRepository;
import io.crs.hsys.server.service.MarketGroupService;

/**
 * @author robi
 *
 */
public class MarketGroupServiceImpl extends HotelChildServiceImpl<MarketGroup, MarketGroupRepository>
		implements MarketGroupService {
	private static final Logger logger = LoggerFactory.getLogger(MarketGroupServiceImpl.class.getName());

	public MarketGroupServiceImpl(MarketGroupRepository roomTypeRepository, AccountRepository accountRepository,
			HotelRepository hotelRepository) {
		super(roomTypeRepository, accountRepository, hotelRepository);
		logger.info("RoomTypeServiceImpl");
	}
}
