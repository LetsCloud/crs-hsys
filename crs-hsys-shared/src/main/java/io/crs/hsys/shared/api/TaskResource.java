/**
 * 
 */
package io.crs.hsys.shared.api;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.crs.hsys.shared.dto.task.TaskDto;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.TASK;

/**
 * @author robi
 *
 */
@Path(ROOT + TASK)
@Produces(MediaType.APPLICATION_JSON)
public interface TaskResource {

	@GET
	List<TaskDto> list();

	@GET
	@Path(PATH_WEBSAFEKEY)
	TaskDto read(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	TaskDto saveOrCreate(TaskDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);
}
