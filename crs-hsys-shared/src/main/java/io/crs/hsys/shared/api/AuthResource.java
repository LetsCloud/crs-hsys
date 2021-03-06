/**
 * 
 */
package io.crs.hsys.shared.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.gwtplatform.dispatch.rest.shared.RestAction;

import io.crs.hsys.shared.dto.common.AppUserDto;

import static io.crs.hsys.shared.api.ApiParameters.PASSWORD;
import static io.crs.hsys.shared.api.ApiParameters.REMEMBER_ME;
import static io.crs.hsys.shared.api.ApiParameters.USERNAME;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.CURRENTUSER;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.IS_LOGGED_IN;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.LOGIN;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.LOGOUT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;

/**
 * @author CR
 *
 */
@Path("")
public interface AuthResource {

	@POST
	@Path(LOGIN)
	@Consumes(APPLICATION_FORM_URLENCODED)
	RestAction<Void> login(@FormParam(USERNAME) String username, @FormParam(PASSWORD) String password,
			@FormParam(REMEMBER_ME) Boolean remeberMe);

	@POST
	@Path(LOGOUT)
	RestAction<Void> logout();

	@GET
	@Path(ROOT + LOGIN + IS_LOGGED_IN)
	RestAction<Boolean> isCurrentUserLoggedIn();

	@GET
	@Path(ROOT + LOGIN + CURRENTUSER)
	RestAction<AppUserDto> getCurrentUser();

}
