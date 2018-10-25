/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.profile.Organization;
import io.crs.hsys.server.repository.OrganizationRepository;

/**
 * @author robi
 *
 */
public class OrganizationRepositoryImpl extends CrudRepositoryImpl<Organization> implements OrganizationRepository {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationRepositoryImpl.class.getName());

	public OrganizationRepositoryImpl() {
		super(Organization.class);
		logger.info("OrganizationRepositoryImpl()");
	}

	@Override
	public String getAccountId(String id) {
		Key<Organization> key = getKey(id);
		return key.getParent().getString();
	}

	@Override
	protected Object getParent(Organization entity) {
		return entity.getAccount();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(Organization entiy) {
		// TODO Auto-generated method stub
		
	}
}
