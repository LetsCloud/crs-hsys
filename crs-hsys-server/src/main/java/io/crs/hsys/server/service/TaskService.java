/**
 * 
 */
package io.crs.hsys.server.service;

import java.util.List;

import io.crs.hsys.server.entity.hotel.Room;
import io.crs.hsys.server.entity.task.Task;

/**
 * @author robi
 *
 */
public interface TaskService extends CrudService<Task> {

	List<Task> getByRoom(Room room);

}
