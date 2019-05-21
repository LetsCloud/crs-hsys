/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.hotel.OooRoom;
import io.crs.hsys.server.repository.OooRoomRepository;

/**
 * @author CR
 *
 */
public class OooRoomRepositoryImpl extends HotelChildRepositoryImpl<OooRoom> implements OooRoomRepository {
	private static final Logger logger = LoggerFactory.getLogger(OooRoomRepositoryImpl.class.getName());

	public OooRoomRepositoryImpl() {
		super(OooRoom.class);
		logger.info("OooRoomRepositoryImpl");
	}
}
