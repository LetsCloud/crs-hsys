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
public interface HwBlueResources extends ClientBundle {
	public static final HwBlueResources INSTANCE = GWT.create(HwBlueResources.class);

	interface Style extends CssResource {
		// Your classes here
	}

	@Source({ "hu/hw/cloud/client/core/resources/css/theme_hw_blue.gss",
			"hu/hw/cloud/client/core/resources/css/override.gss" })
	Style override();
}
