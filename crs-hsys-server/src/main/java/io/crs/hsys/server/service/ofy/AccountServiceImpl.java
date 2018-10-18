/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.model.Registration;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.service.AccountService;
import io.crs.hsys.server.service.AppUserService;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.IdNotFoundException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author CR
 *
 */
public class AccountServiceImpl implements AccountService {
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	private AccountRepository accountRepository;

	private AppUserService appUserService;

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public void setAppUserService(AppUserService userService) {
		this.appUserService = userService;
	}

	@Override
	public Boolean sameAccountIds(String webSafeKey, Long id) {
		return accountRepository.getWebSafeKeyById(id).equals(webSafeKey);
	}

	@Override
	public AppUser register(Registration registration)
			throws EntityValidationException, IdNotFoundException, UniqueIndexConflictException {
		logger.debug("register()");

		Account account = accountRepository.save(new Account(registration));
		if (account == null) {
			throw new IdNotFoundException(Account.class.getSimpleName(), registration.getAccountName());
		}
		
		AppUser appUser = appUserService.createAdminUser(registration, account);

		return appUser;
	}

}
