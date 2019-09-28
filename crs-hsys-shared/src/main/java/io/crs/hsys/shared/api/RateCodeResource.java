/**
 * 
 */
package io.crs.hsys.shared.api;

import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;
import static io.crs.hsys.shared.api.ApiParameters.ONLY_ACTIVE;
import static io.crs.hsys.shared.api.ApiParameters.SEL_INV_TYPE;
import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.REDUCED;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.RATE_CODE;
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

import io.crs.hsys.shared.cnst.InventoryType;
import io.crs.hsys.shared.dto.rate.RateCodeDto;
import io.crs.hsys.shared.dto.rate.RateCodeDtor;

/**
 * @author robi
 *
 */
@Path(ROOT + RATE_CODE)
@Produces(MediaType.APPLICATION_JSON)
public interface RateCodeResource {

	@GET
	List<RateCodeDto> getAll(@QueryParam(HOTEL_KEY) String hotelKey, @QueryParam(ONLY_ACTIVE) Boolean onlyActive);

	@GET
	@Path(REDUCED)
	List<RateCodeDtor> listReduced(@QueryParam(HOTEL_KEY) String hotelKey);

	@GET
	@Path(PATH_WEBSAFEKEY)
	RateCodeDto get(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	RateCodeDto saveOrCreate(RateCodeDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);
}
