/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.profile.Profile;
import io.crs.hsys.server.repository.ProfileRepository;

/**
 * @author robi
 *
 */
public class ProfileRepositoryImpl extends CrudRepositoryImpl<Profile> implements ProfileRepository {
	private static final Logger logger = LoggerFactory.getLogger(ProfileRepositoryImpl.class.getName());

	public ProfileRepositoryImpl() {
		super(Profile.class);
		logger.info("ContactRepositoryImpl()");
	}

	@Override
	public String getAccountId(String id) {
		Key<Profile> key = getKey(id);
		return key.getParent().getString();
	}

	@Override
	protected Object getParent(Profile entity) {
		return entity.getAccount();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(Profile entiy) {
		// TODO Auto-generated method stub

	}
}
