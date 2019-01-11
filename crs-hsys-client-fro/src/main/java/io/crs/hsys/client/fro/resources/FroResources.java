/**
 * 
 */
package io.crs.hsys.client.fro.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author robi
 *
 */
public interface FroResources extends ClientBundle {
	public static final FroResources INSTANCE = GWT.create(FroResources.class);

	@Source("img/red3-background.png")
	ImageResource profileBackgroundImg();
}