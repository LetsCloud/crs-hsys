/**
 * 
 */
package io.crs.hsys.client.core.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * @author CR
 *
 */
public interface ThemeResourcesOrange extends ClientBundle {
	public static final ThemeResourcesOrange INSTANCE = GWT.create(ThemeResourcesOrange.class);

	interface Style extends CssResource {
		// Your classes here
	}

	@Source({ "io/crs/hsys/client/core/resources/css/theme-orange.gss",
			"io/crs/hsys/client/core/resources/css/override.gss" })
	Style override();
}
