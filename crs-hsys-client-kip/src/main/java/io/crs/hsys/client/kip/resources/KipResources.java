/**
 * 
 */
package io.crs.hsys.client.kip.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author robi
 *
 */
public interface KipResources extends ClientBundle {
	public static final KipResources INSTANCE = GWT.create(KipResources.class);

	@Source("img/grey-wallpaper.jpg")
	ImageResource profileBackgroundImg();
}