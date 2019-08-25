/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.hotel.RateByYear;
import io.crs.hsys.server.repository.RateByYearRepository;

/**
 * @author robi
 *
 */
public class RateByYearRepositoryImpl extends HotelChildRepositoryImpl<RateByYear> implements RateByYearRepository {
	private static final Logger logger = LoggerFactory.getLogger(RateCodeRepositoryImpl.class.getName());

	public RateByYearRepositoryImpl() {
		super(RateByYear.class);
		logger.info("RateCodeRepositoryImpl");
	}

	@Override
	protected void loadUniqueIndexMap(RateByYear entiy) {
	}

	@Override
	protected void prepareForeignKeys(String webSafeKey) {
	}
}
