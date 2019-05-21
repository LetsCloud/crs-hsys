/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.hotel.OooRoom;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.HotelRepository;
import io.crs.hsys.server.repository.OooRoomRepository;
import io.crs.hsys.server.service.OooRoomService;

/**
 * @author CR
 *
 */
public class OooRoomServiceImpl extends HotelChildServiceImpl<OooRoom, OooRoomRepository> implements OooRoomService {
	private static final Logger logger = LoggerFactory.getLogger(OooRoomServiceImpl.class.getName());

	public OooRoomServiceImpl(OooRoomRepository repository, AccountRepository accountRepository,
			HotelRepository hotelRepository) {
		super(repository, accountRepository, hotelRepository);
		logger.info("OooRoomServiceImpl");
	}

}
