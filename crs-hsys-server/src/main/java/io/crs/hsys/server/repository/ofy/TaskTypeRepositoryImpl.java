/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.task.TaskType;
import io.crs.hsys.server.repository.TaskTypeRepository;
import io.crs.hsys.shared.exception.ExceptionSubType;

/**
 * @author robi
 *
 */
public class TaskTypeRepositoryImpl extends CrudRepositoryImpl<TaskType> implements TaskTypeRepository {

	/**
	 * 
	 */
	private static final String PROPERTY_CODE = "code";

	protected TaskTypeRepositoryImpl() {
		super(TaskType.class);
	}

	@Override
	protected Object getParent(TaskType entity) {
		return entity.getAccount();
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<TaskType> key = getKey(webSafeString);
		return key.getParent().getString();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(TaskType entiy) {
		if ((entiy.getCode() != null) && (!entiy.getCode().isEmpty()))
			entiy.addUniqueIndex(PROPERTY_CODE, entiy.getCode(), ExceptionSubType.TASKTYPE_CODE_ALREADY_EXISTS);
	}

}
