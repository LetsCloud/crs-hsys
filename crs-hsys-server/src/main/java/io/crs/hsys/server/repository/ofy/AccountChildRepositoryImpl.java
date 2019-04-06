/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.ForeignKey;
import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.common.AccountChild;

/**
 * @author robi
 *
 */
public abstract class AccountChildRepositoryImpl<T extends AccountChild> extends CrudRepositoryImpl<T> {

	protected AccountChildRepositoryImpl(Class<T> clazz) {
		super(clazz);
	}

	@Override
	protected Object getParent(T entity) {
		return entity.getAccount();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(T entiy) {
	}

	@Override
	protected void prepareForeignKeys(String webSafeKey) {
		if ((foreignKeys == null) || (foreignKeys.isEmpty()))
			return;

		T entity = this.findByWebSafeKey(webSafeKey);
		if (entity == null)
			return;
		for (ForeignKey foreignKey : foreignKeys) {
			foreignKey.setParent(entity.getAccount());
			foreignKey.setValue(entity);
		}
	}
}
