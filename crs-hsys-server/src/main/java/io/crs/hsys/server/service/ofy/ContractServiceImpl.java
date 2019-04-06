/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import io.crs.hsys.server.entity.doc.Contract;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.ContractRepository;
import io.crs.hsys.server.service.ContractService;

/**
 * @author robi
 *
 */
public class ContractServiceImpl extends AccountChildServiceImpl<Contract, ContractRepository>
		implements ContractService {

	public ContractServiceImpl(ContractRepository repository, AccountRepository accountRepository) {
		super(repository, accountRepository);
	}
}
