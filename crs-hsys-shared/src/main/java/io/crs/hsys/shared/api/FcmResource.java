/**
 * 
 */
package io.crs.hsys.shared.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.gwtplatform.dispatch.rest.shared.RestAction;

import io.crs.hsys.shared.dto.chat.MessageDto;

import static io.crs.hsys.shared.api.ApiParameters.IID_TOKEN;
import static io.crs.hsys.shared.api.ApiParameters.USER_AGENT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.FCM;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.MESSAGE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.SUBSCRIBE;

/**
 * @author CR
 *
 */
@Path(ROOT + FCM)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface FcmResource {

	@POST
	@Path(SUBSCRIBE)
	RestAction<Void> subscribe(@QueryParam(IID_TOKEN) String iidToken, @QueryParam(USER_AGENT) String userAgent);

	@DELETE
	@Path(SUBSCRIBE)
	RestAction<Void> unsubscribeAll();

	@POST
	@Path(MESSAGE)
	RestAction<Void> notifyAllUser1(MessageDto message);

}
