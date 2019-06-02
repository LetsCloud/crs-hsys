/**
 * 
 */
package io.crs.hsys.client.fro.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * @author robi
 *
 */
public interface FroGssResources extends ClientBundle {

	interface BookingStyle extends CssResource {
		String blue_arrow();

		String red_arrow();
	}

	@Source({ "io/crs/hsys/client/fro/resources/css/bookingwidget.gss" })
	BookingStyle bookingStyle();

}
