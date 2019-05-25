/**
 * 
 */
package io.crs.hsys.shared.api;

import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;
import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.OOO_ROOM;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.OOO_ROOMS_CREATE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.crs.hsys.shared.dto.hotel.OooCreateDto;
import io.crs.hsys.shared.dto.hotel.OooRoomDto;

/**
 * @author CR
 *
 */
@Path(ROOT + OOO_ROOM)
@Produces(MediaType.APPLICATION_JSON)
public interface OooRoomResource {

	@GET
	List<OooRoomDto> getByHotel(@QueryParam(HOTEL_KEY) String hotelKey);

	@GET
	@Path(PATH_WEBSAFEKEY)
	OooRoomDto get(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	OooRoomDto saveOrCreate(OooRoomDto dto);

	@POST
	@Path(OOO_ROOMS_CREATE)
	OooCreateDto create(OooCreateDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);
}
