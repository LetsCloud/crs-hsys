/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.ForeignKey;
import io.crs.hsys.server.entity.task.Task;
import io.crs.hsys.server.entity.task.TaskType;
import io.crs.hsys.server.repository.TaskRepository;
import io.crs.hsys.server.repository.TaskTypeRepository;
import io.crs.hsys.shared.exception.cnst.ErrorMessageCode;

/**
 * @author robi
 *
 */
public class TaskTypeRepositoryImpl extends AccountChildRepositoryImpl<TaskType> implements TaskTypeRepository {

	/**
	 * 
	 */
	private static final String PROPERTY_CODE = "code";

	protected TaskTypeRepositoryImpl(TaskRepository taskRepository) {
		super(TaskType.class);
		foreignKeys.add(new ForeignKey(Task.PROPERTY_TYPE, taskRepository, ErrorMessageCode.TASKTYPE_ID_USED_BY_TASK));
	}

	@Override
	protected void loadUniqueIndexMap(TaskType entiy) {
		if ((entiy.getCode() != null) && (!entiy.getCode().isEmpty()))
			entiy.addUniqueIndex(PROPERTY_CODE, entiy.getCode(), ErrorMessageCode.TASKTYPE_CODE_ALREADY_EXISTS);
	}
}
