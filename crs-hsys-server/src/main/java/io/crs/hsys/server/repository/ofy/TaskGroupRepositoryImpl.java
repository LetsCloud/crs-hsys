/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.ForeignKey;
import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.task.TaskGroup;
import io.crs.hsys.server.repository.TaskGroupRepository;
import io.crs.hsys.server.repository.TaskTodoRepository;
import io.crs.hsys.server.repository.TaskTypeRepository;
import io.crs.hsys.shared.exception.ExceptionSubType;

/**
 * @author CR
 *
 */
public class TaskGroupRepositoryImpl extends CrudRepositoryImpl<TaskGroup> implements TaskGroupRepository {
	private static final Logger logger = LoggerFactory.getLogger(TaskGroupRepositoryImpl.class.getName());

	/**
	 * 
	 */
	private static final String PROPERTY_CODE = "code";

	protected TaskGroupRepositoryImpl(TaskTodoRepository taskTodoRepository, TaskTypeRepository taskTypeRepository) {
		super(TaskGroup.class);
		logger.info("TaskGroupRepositoryImpl");

		foreignKeys
				.add(new ForeignKey("taskGroup", taskTodoRepository, ExceptionSubType.TASKGROUP_ID_USED_BY_TASKTODO));
		foreignKeys
				.add(new ForeignKey("taskGroup", taskTypeRepository, ExceptionSubType.TASKGROUP_ID_USED_BY_TASKTYPE));
	}

	@Override
	protected Object getParent(TaskGroup entity) {
		return entity.getAccount();
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<TaskGroup> key = getKey(webSafeString);
		return key.getParent().getString();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(TaskGroup entiy) {
		if ((entiy.getCode() != null) && (!entiy.getCode().isEmpty()))
			entiy.addUniqueIndex(PROPERTY_CODE, entiy.getCode(), ExceptionSubType.TASKGROUP_CODE_ALREADY_EXISTS);
	}

	@Override
	protected void prepareForeignKeys(String webSafeKey) {
		logger.info("prepareForeignKeys()");
		TaskGroup entity = this.findByWebSafeKey(webSafeKey);
		if (entity == null)
			return;
		logger.info("prepareForeignKeys()-2");
		for (ForeignKey foreignKey : foreignKeys) {
			logger.info("prepareForeignKeys()-3->" + entity.getCode());
			foreignKey.setParent(entity.getAccount());
			foreignKey.setValue(entity);
		}
	}
}
