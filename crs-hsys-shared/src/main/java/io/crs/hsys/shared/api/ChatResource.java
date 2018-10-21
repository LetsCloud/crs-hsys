/**
 * 
 */
package io.crs.hsys.shared.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.crs.hsys.shared.dto.chat.AddPostDto;
import io.crs.hsys.shared.dto.chat.ChatDto;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ADD_POST;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.CHAT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

/**
 * @author robi
 *
 */
@Path(ROOT + CHAT)
@Produces(MediaType.APPLICATION_JSON)
public interface ChatResource {

	@GET
	List<ChatDto> list();

	@GET
	@Path(PATH_WEBSAFEKEY)
	ChatDto getByKey(@PathParam(WEBSAFEKEY) String key);

	@POST
	ChatDto create(ChatDto dto);

	@POST
	@Path(ADD_POST)
	ChatDto addPost(AddPostDto addPost);

}
