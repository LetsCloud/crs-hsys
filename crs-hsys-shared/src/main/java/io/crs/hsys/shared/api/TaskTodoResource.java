/**
 * 
 */
package io.crs.hsys.shared.api;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.TASK_TODO;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
@Path(ROOT + TASK_TODO)
public interface TaskTodoResource {

	@GET
	List<TaskTodoDto> getAll();

	@GET
	@Path(PATH_WEBSAFEKEY)
	TaskTodoDto get(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	TaskTodoDto saveOrCreate(TaskTodoDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);
}
