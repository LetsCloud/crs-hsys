/**
 * 
 */
package io.crs.hsys.shared.api;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.TASK_GROUP;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import io.crs.hsys.shared.dto.task.TaskGroupDto;

/**
 * @author robi
 *
 */
@Path(ROOT + TASK_GROUP)
public interface TaskGroupResource {

	@GET
	List<TaskGroupDto> getAll();

	@GET
	@Path(PATH_WEBSAFEKEY)
	TaskGroupDto get(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	TaskGroupDto saveOrCreate(TaskGroupDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);
}
