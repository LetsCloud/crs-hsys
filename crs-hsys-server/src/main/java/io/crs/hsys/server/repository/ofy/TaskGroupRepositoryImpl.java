/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.ForeignKey;
import io.crs.hsys.server.entity.task.TaskGroup;
import io.crs.hsys.server.entity.task.TaskTodo;
import io.crs.hsys.server.entity.task.TaskType;
import io.crs.hsys.server.repository.TaskGroupRepository;
import io.crs.hsys.server.repository.TaskTodoRepository;
import io.crs.hsys.server.repository.TaskTypeRepository;
import io.crs.hsys.shared.exception.cnst.ErrorMessageCode;

/**
 * @author CR
 *
 */
public class TaskGroupRepositoryImpl extends AccountChildRepositoryImpl<TaskGroup> implements TaskGroupRepository {

	/**
	 * 
	 */
	protected TaskGroupRepositoryImpl(TaskTodoRepository taskTodoRepository, TaskTypeRepository taskTypeRepository) {
		super(TaskGroup.class);
		foreignKeys.add(new ForeignKey(TaskTodo.PROPERTY_TASKGROUP, taskTodoRepository,
				ErrorMessageCode.TASKGROUP_ID_USED_BY_TASKTODO));
		foreignKeys.add(new ForeignKey(TaskType.PROPERTY_TASKGROUP, taskTypeRepository,
				ErrorMessageCode.TASKGROUP_ID_USED_BY_TASKTYPE));
	}

	@Override
	protected void loadUniqueIndexMap(TaskGroup entiy) {
		if ((entiy.getCode() != null) && (!entiy.getCode().isEmpty()))
			entiy.addUniqueIndex(TaskGroup.PROPERTY_CODE, entiy.getCode(),
					ErrorMessageCode.TASKGROUP_CODE_ALREADY_EXISTS);
	}
}
