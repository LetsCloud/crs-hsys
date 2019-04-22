/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.hotel.RoomType;
import io.crs.hsys.server.repository.RoomTypeRepository;
import io.crs.hsys.shared.exception.ExceptionSubType;

/**
 * @author CR
 *
 */
public class RoomTypeRepositoryImpl extends HotelChildRepositoryImpl<RoomType> implements RoomTypeRepository {
	private static final Logger logger = LoggerFactory.getLogger(RoomTypeRepositoryImpl.class.getName());

	/**
	 * 
	 */
	private static final String ROOMTYPE_CODE = "code";

	public RoomTypeRepositoryImpl() {
		super(RoomType.class);
		logger.info("RoomTypeRepositoryImpl");
	}

	@Override
	protected void loadUniqueIndexMap(RoomType entiy) {

		if (entiy.getCode() != null)
			entiy.addUniqueIndex(ROOMTYPE_CODE, entiy.getCode(), ExceptionSubType.ROOMTYPECODE_CODE_ALREADY_EXISTS);
	}

	@Override
	protected void prepareForeignKeys(String webSafeKey) {
		// TODO Auto-generated method stub
		
	}
}
