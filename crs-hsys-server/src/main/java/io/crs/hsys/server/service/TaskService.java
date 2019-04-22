/**
 * 
 */
package io.crs.hsys.server.service;

import java.util.List;

import io.crs.hsys.server.entity.hotel.Room;
import io.crs.hsys.server.entity.task.Task;
import io.crs.hsys.shared.cnst.TaskStatus;
import io.crs.hsys.shared.exception.RestApiException;

/**
 * @author robi
 *
 */
public interface TaskService extends CrudService<Task> {

	List<Task> getByRoom(Room room);

	Task changeStatus(final String taskKey, final TaskStatus status) throws RestApiException;
}
