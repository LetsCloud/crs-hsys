/**
 * 
 */
package io.crs.hsys.shared.api;

import static io.crs.hsys.shared.api.ApiPaths.SpaV1.PROFILE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.crs.hsys.shared.dto.profile.ProfileDtor;

/**
 * @author robi
 *
 */
@Path(ROOT + PROFILE)
@Produces(MediaType.APPLICATION_JSON)
public interface ProfileResource {

	@GET
	List<ProfileDtor> getAll();
}
