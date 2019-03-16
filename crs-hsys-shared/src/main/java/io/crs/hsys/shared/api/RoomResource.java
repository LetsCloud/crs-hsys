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

import io.crs.hsys.shared.constans.RoomStatus;
import io.crs.hsys.shared.dto.filter.RoomStatusFilterDto;
import io.crs.hsys.shared.dto.hk.RoomStatusDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;

import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;
import static io.crs.hsys.shared.api.ApiParameters.ONLY_ACTIVE;
import static io.crs.hsys.shared.api.ApiParameters.ROOM_KEY;
import static io.crs.hsys.shared.api.ApiParameters.ROOM_STATUS;
import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.AVAILABLE_ON_DATE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOM;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.STATUS_CHANGE;

/**
 * @author CR
 *
 */
@Path(ROOT + ROOM)
@Produces(MediaType.APPLICATION_JSON)
public interface RoomResource {

	@GET
	List<RoomDto> getByHotel(@QueryParam(HOTEL_KEY) String hotelKey, @QueryParam(ONLY_ACTIVE) Boolean onlyActive);

	@GET
	@Path(ROOM_STATUS)
	List<RoomStatusDto> getRoomStatusesByHotel(@QueryParam(HOTEL_KEY) String hotelKey);

	@GET
	@Path(PATH_WEBSAFEKEY)
	RoomDto get(@PathParam(WEBSAFEKEY) String webSafeKey);

	@GET
	@Path(ROOM_STATUS + PATH_WEBSAFEKEY)
	RoomStatusDto getRoomStatus(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	RoomDto saveOrCreate(RoomDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);

	@GET
	@Path(STATUS_CHANGE)
	RoomDto changeRoomStatus(@QueryParam(ROOM_KEY) String roomKey, @QueryParam(ROOM_STATUS) RoomStatus roomStatus);

	@POST
	@Path(AVAILABLE_ON_DATE)
	List<RoomDto> getAvailableRoomsOnDate(RoomStatusFilterDto filterDto);
}
