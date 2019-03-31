/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import java.util.List;
import java.util.logging.Logger;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.repository.AppUserRepository;
import io.crs.hsys.shared.exception.ExceptionSubType;

/**
 * @author robi
 *
 */
public class AppUserRepositoryImpl extends CrudRepositoryImpl<AppUser> implements AppUserRepository {
	private static final Logger LOGGER = Logger.getLogger(AppUserRepositoryImpl.class.getName());

	/**
	 * 
	 */
	private static final String PROPERTY_USERNAME = "username";
	private static final String PROPERTY_EMAIL = "emailAddress";

	public AppUserRepositoryImpl() {
		super(AppUser.class);
	}

	@Override
	public AppUser findByUsername(Account account, String username) {
		AppUser user = getChildByProperty(account, PROPERTY_USERNAME, username);
//		user.setAccount(account);
//		user.getAccount();
		return user;
	}

	@Override
	public AppUser findByEmail(String emailAddress) {
		return getFirstByProperty(PROPERTY_EMAIL, emailAddress);
	}

	@Override
	protected Object getParent(AppUser entity) {
		return entity.getAccount();
	}

	@Override
	public String getAccountId(String webSafeString) {
		LOGGER.info("getAccountId->id=" + webSafeString);
		Key<AppUser> key = getKey(webSafeString);
		return key.getParent().getString();
	}

	@Override
	public List<AppUser> getByAccount(Object account) {
		return getChildren(account);
	}

	@Override
	public AppUser findByToken(String token) {
		return getFirstByProperty("verificationTokens.token", token);
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(AppUser entiy) {
		if ((entiy.getEmailAddress() != null) && (!entiy.getEmailAddress().isEmpty()))
			entiy.addUniqueIndex(PROPERTY_EMAIL, entiy.getEmailAddress(),
					ExceptionSubType.APPUSER_EMAIL_ALREADY_EXISTS);

		if ((entiy.getUsername() != null) && (!entiy.getUsername().isEmpty()))
			entiy.addUniqueIndex(PROPERTY_USERNAME, entiy.getUsername(), ExceptionSubType.APPUSER_CODE_ALREADY_EXISTS);
	}

	@Override
	protected void prepareForeignKeys(String webSafeKey) {
		// TODO Auto-generated method stub
		
	}
}
