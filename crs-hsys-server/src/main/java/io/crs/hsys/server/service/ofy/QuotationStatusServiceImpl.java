/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import io.crs.hsys.server.entity.doc.QuotationStatus;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.QuotationStatusRepository;
import io.crs.hsys.server.service.QuotationStatusService;

/**
 * @author robi
 *
 */
public class QuotationStatusServiceImpl extends AccountChildServiceImpl<QuotationStatus, QuotationStatusRepository>
		implements QuotationStatusService {

	public QuotationStatusServiceImpl(QuotationStatusRepository repository, AccountRepository accountRepository) {
		super(repository, accountRepository);
	}
}
