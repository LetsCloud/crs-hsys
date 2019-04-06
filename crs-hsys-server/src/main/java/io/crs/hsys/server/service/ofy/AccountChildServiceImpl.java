/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.server.entity.common.AccountChild;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.CrudRepository;

/**
 * @author robi
 *
 */
public abstract class AccountChildServiceImpl<T extends AccountChild, R extends CrudRepository<T>>
		extends CrudServiceImpl<T, R> {

	protected final AccountRepository accountRepository;

	public AccountChildServiceImpl(R repository, AccountRepository accountRepository) {
		super(repository);
		this.accountRepository = accountRepository;
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findById(accountId));
		return parents;
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findByWebSafeKey(accountWebSafeKey));
		return parents;
	}
}
