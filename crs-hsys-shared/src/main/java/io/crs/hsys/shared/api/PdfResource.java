/**
 * 
 */
package io.crs.hsys.shared.api;

import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.gwtplatform.dispatch.rest.shared.RestAction;

/**
 * @author robi
 *
 */
@Path(ROOT + "/pdf")
public interface PdfResource {
	
	@GET
	@Path("/test")
	String testPdf();
}
