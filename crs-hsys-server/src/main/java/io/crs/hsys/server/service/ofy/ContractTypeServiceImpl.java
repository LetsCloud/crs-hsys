/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import io.crs.hsys.server.entity.doc.ContractType;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.ContractTypeRepository;
import io.crs.hsys.server.service.ContractTypeService;

/**
 * @author robi
 *
 */
public class ContractTypeServiceImpl extends AccountChildServiceImpl<ContractType, ContractTypeRepository>
		implements ContractTypeService {

	public ContractTypeServiceImpl(ContractTypeRepository repository, AccountRepository accountRepository) {
		super(repository, accountRepository);
	}
}
