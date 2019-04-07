/**
 * 
 */
package io.crs.hsys.shared.api;

import static io.crs.hsys.shared.api.ApiParameters.ORGANIZATION_KEY;
import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.QUOTATION;
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

import io.crs.hsys.shared.dto.doc.QuotationDto;

/**
 * @author robi
 *
 */
@Path(ROOT + QUOTATION)
@Produces(MediaType.APPLICATION_JSON)
public interface QuotationResource {

	@GET
	List<QuotationDto> getAll(@QueryParam(ORGANIZATION_KEY) String organizationKey);

	@GET
	@Path(PATH_WEBSAFEKEY)
	QuotationDto get(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	QuotationDto saveOrCreate(QuotationDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);
}
