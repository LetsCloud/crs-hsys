/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.profile.ProfileGroup;
import io.crs.hsys.server.repository.ProfileGroupRepository;
import io.crs.hsys.shared.exception.ExceptionSubType;

/**
 * @author robi
 *
 */
public class ProfileGroupRepositoryImpl extends CrudRepositoryImpl<ProfileGroup> implements ProfileGroupRepository {
	private static final Logger logger = LoggerFactory.getLogger(ProfileGroupRepositoryImpl.class.getName());

	public ProfileGroupRepositoryImpl() {
		super(ProfileGroup.class);
		logger.info("ProfileGroupRepositoryImpl");
	}

	@Override
	protected Object getParent(ProfileGroup entity) {
		logger.info("getParent->entity=" + entity);
		return entity.getAccount();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	public String getAccountId(String webSafeKey) {
		Key<ProfileGroup> key = getKey(webSafeKey);
		return key.getParent().getString();
	}

	@Override
	protected void loadUniqueIndexMap(ProfileGroup entiy) {

		if (entiy.getCode() != null) {
			entiy.addUniqueIndex(ProfileGroup.PROFILEGROUP_CODE, entiy.getCode(),
					ExceptionSubType.PROFILEGROUP_CODE_ALREADY_EXISTS);
		}
	}

	@Override
	protected void prepareForeignKeys(String webSafeKey) {
		// TODO Auto-generated method stub
		
	}
}
