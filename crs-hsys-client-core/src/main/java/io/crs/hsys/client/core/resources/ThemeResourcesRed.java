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
public interface ThemeResourcesRed extends ClientBundle {
	public static final ThemeResourcesRed INSTANCE = GWT.create(ThemeResourcesRed.class);

	interface Style extends CssResource {
		// Your classes here
	}

	@Source({ "io/crs/hsys/client/core/resources/css/theme-red.gss",
			"io/crs/hsys/client/core/resources/css/override.gss" })
	Style override();
}
