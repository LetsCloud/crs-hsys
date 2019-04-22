/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.task.TaskTodo;
import io.crs.hsys.server.repository.TaskTodoRepository;
import io.crs.hsys.shared.exception.ExceptionSubType;

/**
 * @author robi
 *
 */
public class TaskTodoRepositoryImpl extends AccountChildRepositoryImpl<TaskTodo> implements TaskTodoRepository {

	/**
	 * 
	 */
	private static final String PROPERTY_DESCRIPTION = "description";

	protected TaskTodoRepositoryImpl() {
		super(TaskTodo.class);
	}

	@Override
	protected void loadUniqueIndexMap(TaskTodo entiy) {
		if ((entiy.getDescription() != null) && (!entiy.getDescription().isEmpty()))
			entiy.addUniqueIndex(PROPERTY_DESCRIPTION, entiy.getDescription(),
					ExceptionSubType.TASKTODO_DESCRIPTION_ALREADY_EXISTS);
	}
}
