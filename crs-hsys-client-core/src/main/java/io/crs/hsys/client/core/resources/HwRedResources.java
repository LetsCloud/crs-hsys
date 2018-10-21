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
public interface HwRedResources extends ClientBundle {
	public static final HwRedResources INSTANCE = GWT.create(HwRedResources.class);

	interface Style extends CssResource {
		// Your classes here
	}

	@Source({ "io/crs/hsys/client/core/resources/css/theme_hw_red.gss",
			"io/crs/hsys/client/core/resources/css/override.gss" })
	Style override();
}
