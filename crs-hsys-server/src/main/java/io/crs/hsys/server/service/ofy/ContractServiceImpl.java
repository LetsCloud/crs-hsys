/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import io.crs.hsys.server.entity.doc.Contract;
import io.crs.hsys.server.repository.ContractRepository;
import io.crs.hsys.server.service.AccountService;
import io.crs.hsys.server.service.ContractService;

/**
 * @author robi
 *
 */
public class ContractServiceImpl extends AccountChildServiceImpl<Contract, ContractRepository>
		implements ContractService {

	public ContractServiceImpl(ContractRepository repository, AccountService accountService) {
		super(repository, accountService);
	}
}
