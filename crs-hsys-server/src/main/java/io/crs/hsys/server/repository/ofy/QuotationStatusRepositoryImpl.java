/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.ForeignKey;
import io.crs.hsys.server.entity.doc.Quotation;
import io.crs.hsys.server.entity.doc.QuotationStatus;
import io.crs.hsys.server.repository.QuotationRepository;
import io.crs.hsys.server.repository.QuotationStatusRepository;
import io.crs.hsys.shared.exception.ExceptionSubType;

/**
 * @author robi
 *
 */
public class QuotationStatusRepositoryImpl extends AccountChildRepositoryImpl<QuotationStatus>
		implements QuotationStatusRepository {

	protected QuotationStatusRepositoryImpl(QuotationRepository quotationRepository) {
		super(QuotationStatus.class);
		foreignKeys.add(new ForeignKey(Quotation.PROPERTY_STATUS, quotationRepository,
				ExceptionSubType.QOUTATIONSTATUS_IS_USED_BY_QOUTATION));
	}

	@Override
	protected void loadUniqueIndexMap(QuotationStatus entiy) {
		if ((entiy.getCode() != null) && (!entiy.getCode().isEmpty()))
			entiy.addUniqueIndex(QuotationStatus.PROPERTY_CODE, entiy.getCode(),
					ExceptionSubType.QOUTATIONSTATUS_CODE_ALREADY_EXISTS);
	}
}
