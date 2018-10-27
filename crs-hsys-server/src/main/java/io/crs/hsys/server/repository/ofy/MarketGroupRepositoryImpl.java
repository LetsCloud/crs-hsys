/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.reservation.MarketGroup;
import io.crs.hsys.server.repository.MarketGroupRepository;

/**
 * @author robi
 *
 */
public class MarketGroupRepositoryImpl extends HotelChildRepositoryImpl<MarketGroup> implements MarketGroupRepository {
	private static final Logger logger = LoggerFactory.getLogger(MarketGroupRepositoryImpl.class.getName());

	/**
	 * 
	 */
	private static final String MARKET_GROUP_CODE = "code";

	public MarketGroupRepositoryImpl() {
		super(MarketGroup.class);
		logger.info("MarketGroupRepositoryImpl");
	}

	@Override
	protected void loadUniqueIndexMap(MarketGroup entiy) {

		if (entiy.getCode() != null)
			entiy.addUniqueIndex(MARKET_GROUP_CODE, entiy.getCode());
	}
}