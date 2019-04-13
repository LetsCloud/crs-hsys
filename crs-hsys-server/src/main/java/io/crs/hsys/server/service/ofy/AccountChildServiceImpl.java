/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.common.AccountChild;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.CrudRepository;
import io.crs.hsys.server.service.AccountChildService;
import io.crs.hsys.server.service.AccountService;

/**
 * @author robi
 *
 */
public abstract class AccountChildServiceImpl<T extends AccountChild, R extends CrudRepository<T>>
		extends CrudServiceImpl<T, R> implements AccountChildService<T> {

	protected final AccountService accountService;

	public AccountChildServiceImpl(R repository, AccountService accountService) {
		super(repository);
		this.accountService = accountService;
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountService.findById(accountId));
		return parents;
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountService.findByWebSafeKey(accountWebSafeKey));
		return parents;
	}

	@Override
	public Account getCurrentAccount() {
		return accountService.getCurrentAccount();
	}
}
