/**
 * 
 */
package io.crs.hsys.shared.api;

import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOM_RATE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.crs.hsys.shared.dto.rate.update.RoomRateUpdateDto;

/**
 * @author robi
 *
 */
@Path(ROOT + ROOM_RATE)
@Produces(MediaType.APPLICATION_JSON)
public interface RoomRateResource {

	@POST
	void roomRateUpdate(RoomRateUpdateDto dto);

}
