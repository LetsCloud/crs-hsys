/**
 * 
 */
package io.crs.hsys.shared.api;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.crs.hsys.shared.dto.common.RoleDto;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROLE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

/**
 * @author CR
 *
 */
@Path(ROOT + ROLE)
@Produces(MediaType.APPLICATION_JSON)
public interface RoleResource {

	@GET
	@Path("")
	List<RoleDto> list();

	@POST
	RoleDto create(RoleDto roleDto);

	@GET
	@Path(PATH_WEBSAFEKEY)
	RoleDto read(@PathParam(WEBSAFEKEY) String id);

	@PUT
	RoleDto update(RoleDto roleDto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String id);
}
