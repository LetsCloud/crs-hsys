/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import io.crs.hsys.server.entity.doc.Quotation;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.QuotationRepository;
import io.crs.hsys.server.service.QuotationService;

/**
 * @author robi
 *
 */
public class QuotationServiceImpl extends AccountChildServiceImpl<Quotation, QuotationRepository>
		implements QuotationService {

	public QuotationServiceImpl(QuotationRepository repository, AccountRepository accountRepository) {
		super(repository, accountRepository);
	}
}
