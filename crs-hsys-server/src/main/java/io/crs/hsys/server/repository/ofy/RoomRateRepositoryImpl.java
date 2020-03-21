/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.hotel.RoomRate;
import io.crs.hsys.server.repository.RoomRateRepository;

/**
 * @author robi
 *
 */
public class RoomRateRepositoryImpl extends HotelChildRepositoryImpl<RoomRate> implements RoomRateRepository {
	private static final Logger logger = LoggerFactory.getLogger(RoomRateRepositoryImpl.class.getName());

	public RoomRateRepositoryImpl() {
		super(RoomRate.class);
		logger.info("RoomRateRepositoryImpl");
	}

	@Override
	protected void loadUniqueIndexMap(RoomRate entiy) {
	}

	@Override
	protected void prepareForeignKeys(String webSafeKey) {
	}
}
