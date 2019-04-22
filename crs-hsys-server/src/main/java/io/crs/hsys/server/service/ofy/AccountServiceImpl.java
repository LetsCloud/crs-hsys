/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.model.Registration;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.security.LoggedInChecker;
import io.crs.hsys.server.service.AccountService;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.IdNotFoundException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author CR
 *
 */
public class AccountServiceImpl extends CrudServiceImpl<Account, AccountRepository> implements AccountService {
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	private final AccountRepository accountRepository;
	private final LoggedInChecker loggedInChecker;
	
	public AccountServiceImpl(AccountRepository repository, LoggedInChecker loggedInChecker) {
		super(repository);
		logger.info("AccountServiceImpl(");
		this.accountRepository = repository;
		this.loggedInChecker = loggedInChecker;
	}

	@Override
	public Boolean sameAccountIds(String webSafeKey, Long id) {
		return accountRepository.getWebSafeKeyById(id).equals(webSafeKey);
	}

	@Override
	public AppUser getCurrentUser() {
		return loggedInChecker.getLoggedInUser();
	}

	@Override
	public Account getCurrentAccount() {
		AppUser currentUser = getCurrentUser();
		if (currentUser == null)
			return null;
		return currentUser.getAccount();
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppUser register(Registration registration)
			throws EntityValidationException, UniqueIndexConflictException, IdNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
