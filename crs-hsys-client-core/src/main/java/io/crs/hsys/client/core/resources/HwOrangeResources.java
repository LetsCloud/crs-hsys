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
public interface HwOrangeResources extends ClientBundle {
	public static final HwOrangeResources INSTANCE = GWT.create(HwOrangeResources.class);

	interface Style extends CssResource {
		// Your classes here
	}

	@Source({ "hu/hw/cloud/client/core/resources/css/theme_hw_orange.gss",
			"hu/hw/cloud/client/core/resources/css/override.gss" })
	Style override();
}
