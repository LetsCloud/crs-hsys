package io.crs.hsys.shared.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gwtplatform.dispatch.rest.shared.RestAction;

import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.dto.common.FcmTokenDto;
import io.crs.hsys.shared.dto.hotel.HotelDtor;

import static io.crs.hsys.shared.api.ApiParameters.TOKEN;
import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.REDUCED;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.INVITE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.SUBSCRIBE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.USER;

@Path(ROOT + USER)
@Produces(MediaType.APPLICATION_JSON)
public interface AppUserResource {

	@GET
	List<AppUserDto> list();

	@GET
	@Path(REDUCED)
	List<AppUserDto> listReduced();

	@GET
	@Path(PATH_WEBSAFEKEY)
	AppUserDto read(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	AppUserDto saveOrCreate(AppUserDto userDto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	@Path(INVITE)
	AppUserDto invite(AppUserDto userDto);

	@POST
	@Path(SUBSCRIBE)
	Boolean subscribe(FcmTokenDto token);

	@GET
	@Path("/activate/{token}")
	RestAction<Boolean> activate(@PathParam(TOKEN) String token);

}
