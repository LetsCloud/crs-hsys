/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.task.TaskTodo;
import io.crs.hsys.server.repository.TaskTodoRepository;
import io.crs.hsys.shared.exception.ExceptionSubType;

/**
 * @author robi
 *
 */
public class TaskTodoRepositoryImpl extends CrudRepositoryImpl<TaskTodo> implements TaskTodoRepository {

	/**
	 * 
	 */
	private static final String PROPERTY_DESCRIPTION = "description";

	protected TaskTodoRepositoryImpl() {
		super(TaskTodo.class);
	}

	@Override
	protected Object getParent(TaskTodo entity) {
		return entity.getAccount();
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<TaskTodo> key = getKey(webSafeString);
		return key.getParent().getString();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(TaskTodo entiy) {
		if ((entiy.getDescription() != null) && (!entiy.getDescription().isEmpty()))
			entiy.addUniqueIndex(PROPERTY_DESCRIPTION, entiy.getDescription(),
					ExceptionSubType.TASKTODO_DESCRIPTION_ALREADY_EXISTS);
	}

}
