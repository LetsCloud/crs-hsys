/**
 * 
 */
package io.crs.hsys.client.core.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * @author robi
 *
 */
public interface ThemeResourcesGrey extends ClientBundle {
	public static final ThemeResourcesGrey INSTANCE = GWT.create(ThemeResourcesGrey.class);

	interface Style extends CssResource {
		// Your classes here
	}

	@Source({ "io/crs/hsys/client/core/resources/css/theme-grey.gss",
			"io/crs/hsys/client/core/resources/css/override.gss" })
	Style override();
}
