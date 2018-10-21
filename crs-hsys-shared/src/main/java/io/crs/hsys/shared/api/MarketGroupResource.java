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

import io.crs.hsys.shared.dto.hotel.MarketGroupDto;

import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;
import static io.crs.hsys.shared.api.ApiParameters.ONLY_ACTIVE;
import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.MARKET_GROUP;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

/**
 * @author robi
 *
 */
@Path(ROOT + MARKET_GROUP)
@Produces(MediaType.APPLICATION_JSON)
public interface MarketGroupResource {

	@GET
	List<MarketGroupDto> getAll(@QueryParam(HOTEL_KEY) String hotelKey, @QueryParam(ONLY_ACTIVE) Boolean onlyActive);

	@GET
	@Path(PATH_WEBSAFEKEY)
	MarketGroupDto get(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	MarketGroupDto saveOrCreate(MarketGroupDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);
}
