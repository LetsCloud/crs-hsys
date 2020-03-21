/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.hotel.RateCode;
import io.crs.hsys.server.repository.RateCodeRepository;
import io.crs.hsys.shared.exception.cnst.ErrorMessageCode;

/**
 * @author robi
 *
 */
public class RateCodeRepositoryImpl extends HotelChildRepositoryImpl<RateCode> implements RateCodeRepository {
	private static final Logger logger = LoggerFactory.getLogger(RateCodeRepositoryImpl.class.getName());

	/**
	 * 
	 */
	private static final String ROOMTYPE_CODE = "code";

	public RateCodeRepositoryImpl() {
		super(RateCode.class);
		logger.info("RateCodeRepositoryImpl");
	}

	@Override
	protected void loadUniqueIndexMap(RateCode entiy) {

		if (entiy.getCode() != null)
			entiy.addUniqueIndex(ROOMTYPE_CODE, entiy.getCode(), ErrorMessageCode.ROOMTYPECODE_CODE_ALREADY_EXISTS);
	}

	@Override
	protected void prepareForeignKeys(String webSafeKey) {
		// TODO Auto-generated method stub

	}
}
