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

import io.crs.hsys.shared.dto.profile.OrganizationDto;
import io.crs.hsys.shared.dto.profile.OrganizationDtor;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ORGANIZATION;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

/**
 * @author robi
 *
 */
@Path(ROOT + ORGANIZATION)
@Produces(MediaType.APPLICATION_JSON)
public interface OrganizationResource {

	@GET
	List<OrganizationDtor> list();

	@GET
	@Path(PATH_WEBSAFEKEY)
	OrganizationDto read(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	OrganizationDto saveOrCreate(OrganizationDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);
}
