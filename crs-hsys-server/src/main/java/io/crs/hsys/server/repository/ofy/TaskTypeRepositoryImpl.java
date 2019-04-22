/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.task.TaskType;
import io.crs.hsys.server.repository.TaskTypeRepository;
import io.crs.hsys.shared.exception.ExceptionSubType;

/**
 * @author robi
 *
 */
public class TaskTypeRepositoryImpl extends AccountChildRepositoryImpl<TaskType> implements TaskTypeRepository {

	/**
	 * 
	 */
	private static final String PROPERTY_CODE = "code";

	protected TaskTypeRepositoryImpl() {
		super(TaskType.class);
	}

	@Override
	protected void loadUniqueIndexMap(TaskType entiy) {
		if ((entiy.getCode() != null) && (!entiy.getCode().isEmpty()))
			entiy.addUniqueIndex(PROPERTY_CODE, entiy.getCode(), ExceptionSubType.TASKTYPE_CODE_ALREADY_EXISTS);
	}
}
