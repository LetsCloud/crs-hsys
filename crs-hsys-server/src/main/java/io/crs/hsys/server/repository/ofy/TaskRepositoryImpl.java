/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.task.Task;
import io.crs.hsys.server.repository.TaskRepository;

/**
 * @author robi
 *
 */
public class TaskRepositoryImpl extends CrudRepositoryImpl<Task> implements TaskRepository {

	protected TaskRepositoryImpl() {
		super(Task.class);
	}

	@Override
	protected Object getParent(Task entity) {
		return entity.getAccount();
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<Task> key = getKey(webSafeString);
		return key.getParent().getString();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(Task entiy) {
	}

	@Override
	protected void prepareForeignKeys(String webSafeKey) {
		// TODO Auto-generated method stub
		
	}

}
