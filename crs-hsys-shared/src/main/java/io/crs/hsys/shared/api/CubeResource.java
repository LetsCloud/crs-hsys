/**
 * 
 */
package io.crs.hsys.shared.api;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.crs.hsys.shared.dto.cube.D3m6Dto;
import io.crs.hsys.shared.dto.cube.DataWidgetValueM1Dto;
import io.crs.hsys.shared.dto.cube.Perf1Dto;
import io.crs.hsys.shared.dto.cube.Perf1QueryParamDto;
import io.crs.hsys.shared.dto.cube.query.CubeQueryParamDto;

import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.DATACUBE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.D3M6_QUERY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.M1_QUERY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.PERF1_QUERY;

/**
 * @author robi
 *
 */
@Path(ROOT + DATACUBE)
@Produces(MediaType.APPLICATION_JSON)
public interface CubeResource {

	@POST
	@Path(D3M6_QUERY)
	List<D3m6Dto> d3m6CubeQuery(CubeQueryParamDto dto);

	@POST
	@Path(M1_QUERY)
	List<DataWidgetValueM1Dto> dataWidgetValueM1Query(CubeQueryParamDto dto);

	@POST
	@Path(PERF1_QUERY)
	List<Perf1Dto> perf1Query(Perf1QueryParamDto paramDto);
}