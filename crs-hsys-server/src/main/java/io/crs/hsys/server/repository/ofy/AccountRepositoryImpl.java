/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.repository.AccountRepository;

/**
 * @author robi
 *
 */
public class AccountRepositoryImpl extends CrudRepositoryImpl<Account> implements AccountRepository {

	public AccountRepositoryImpl() {
		super(Account.class);
	}

	@Override
	protected Object getParent(Account entity) {
		return null;
	}

	@Override
	public String getWebSafeKeyById(Long id) {
		Key<Account> key = Key.create(Account.class, id);
		return key.getString();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		return null;
	}

	@Override
	protected void loadUniqueIndexMap(Account entiy) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void prepareForeignKeys(String webSafeKey) {
		// TODO Auto-generated method stub
		
	}
}
