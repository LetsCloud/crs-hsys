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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.crs.hsys.shared.dto.profile.RelationshipDto;
import io.crs.hsys.shared.dto.profile.RelationshipDtor;

import static io.crs.hsys.shared.api.ApiParameters.ONLY_ACTIVE;
import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.REDUCED;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.RELATIONSHIP;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

/**
 * @author robi
 *
 */
@Path(ROOT + RELATIONSHIP)
@Produces(MediaType.APPLICATION_JSON)
public interface RelationshipResource {

	@GET
	List<RelationshipDto> getAll(@QueryParam(ONLY_ACTIVE) Boolean onlyActive);

	@GET
	@Path(REDUCED)
	List<RelationshipDtor> getAllReduced(@QueryParam(ONLY_ACTIVE) Boolean onlyActive);

	@GET
	@Path(PATH_WEBSAFEKEY)
	RelationshipDto get(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	RelationshipDto saveOrCreate(RelationshipDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);
}
