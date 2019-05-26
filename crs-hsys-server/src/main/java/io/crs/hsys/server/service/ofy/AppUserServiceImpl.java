/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.entity.common.FcmToken;
import io.crs.hsys.server.entity.common.VerificationToken;
import io.crs.hsys.server.model.Registration;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.AppUserRepository;
import io.crs.hsys.server.security.LoggedInChecker;
import io.crs.hsys.server.service.AppUserService;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
public class AppUserServiceImpl extends CrudServiceImpl<AppUser, AppUserRepository> implements AppUserService {
//	private static final Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class.getName());

	private final LoggedInChecker loggedInChecker;
	private final AccountRepository accountRepository;
	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;

	AppUserServiceImpl(LoggedInChecker loggedInChecker, AccountRepository accountRepository,
			AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
		super(appUserRepository);
//		logger.info("AppUserServiceImpl()");
		this.loggedInChecker = loggedInChecker;
		this.accountRepository = accountRepository;
		this.appUserRepository = appUserRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public AppUser getUserByUsername(String username, Long accountId) {
		Account account = accountRepository.findById(accountId);

		if (account == null)
			return null;

		AppUser user = this.appUserRepository.findByUsername(account, username);
		return user;
	}

	@Override
	public List<String> getPermissions(String username) {
		List<String> ret = new ArrayList<String>();
		ret.add("USER");
		return ret;
	}

	@Override
	public AppUser getCurrentUser() {
		return loggedInChecker.getLoggedInUser();
	}

	@Override
	public Boolean isCurrentUserLoggedIn() {
		return loggedInChecker.getLoggedInUser() != null;
	}

	@Override
	public void createVerificationToken(AppUser user, String token) throws Throwable {
		VerificationToken myToken = new VerificationToken(token);
		List<VerificationToken> tokens = new ArrayList<VerificationToken>();
		tokens.add(myToken);
		user.setVerificationTokens(tokens);
		appUserRepository.save(user);
	}

	@Override
	public List<AppUser> getAll(String accountWebSafeKey) {
		Account account = accountRepository.findByWebSafeKey(accountWebSafeKey);

		if (account == null)
			return null;

		return appUserRepository.getByAccount(account);
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

	@Override
	public AppUser createAdminUser(Registration registration, Account account)
			throws EntityValidationException, UniqueIndexConflictException {
		AppUser appUser = new AppUser(registration);
		appUser.setAccount(account);
		appUser.setPassword(passwordEncoder.encode(registration.getPassword()));
		appUser.setAdmin(true);
		appUser = appUserRepository.save(appUser);
		return appUser;
	}

	@Override
	public Boolean activate(String token) throws EntityValidationException, UniqueIndexConflictException {
		AppUser user = this.appUserRepository.findByToken(token);
		user.setEnabled(true);
		this.appUserRepository.save(user);
		return true;
	}

	@Override
	public void fcmSubscribe(String iidToken, String userAgent) throws Throwable {
		AppUser currentUser = loggedInChecker.getLoggedInUser();
		currentUser = appUserRepository.findByWebSafeKey(currentUser.getWebSafeKey());
		List<FcmToken> tokens = currentUser.getFcmTokens();

		if (FcmToken.getToken(tokens, iidToken) == null) {
			tokens.add(new FcmToken(iidToken, userAgent));
			currentUser.setFcmTokens(tokens);
			update(currentUser);
		}
	}

	@Override
	public AppUser getByEmail(String email) {
		AppUser user = this.appUserRepository.findByEmail(email);
		return user;
	}

	
	@Override
	public Boolean resetPsw(String token) throws EntityValidationException, UniqueIndexConflictException {
		AppUser user = this.appUserRepository.findByWebSafeKey(token);
		user.setPassword(passwordEncoder.encode("*"));
		this.appUserRepository.save(user);
		return true;
	}
}
