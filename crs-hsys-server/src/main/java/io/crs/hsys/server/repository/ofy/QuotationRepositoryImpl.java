/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.doc.Document;
import io.crs.hsys.server.entity.doc.Quotation;
import io.crs.hsys.server.repository.QuotationRepository;
import io.crs.hsys.shared.exception.ExceptionSubType;

/**
 * @author robi
 *
 */
public class QuotationRepositoryImpl extends AccountChildRepositoryImpl<Quotation> implements QuotationRepository {

	public QuotationRepositoryImpl() {
		super(Quotation.class);
	}

	@Override
	protected void loadUniqueIndexMap(Quotation entiy) {
		if ((entiy.getCode() != null) && (!entiy.getCode().isEmpty()))
			entiy.addUniqueIndex(Document.PROPERTY_CODE, entiy.getCode(),
					ExceptionSubType.QUOTATION_CODE_ALREADY_EXISTS);
	}
}
