/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.task.Task;
import io.crs.hsys.server.repository.TaskRepository;

/**
 * @author robi
 *
 */
public class TaskRepositoryImpl extends AccountChildRepositoryImpl<Task> implements TaskRepository {

	protected TaskRepositoryImpl() {
		super(Task.class);
	}
}
