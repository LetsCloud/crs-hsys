/**
 * 
 */
package io.crs.hsys.client.cfg.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author CR
 *
 */
public interface CfgResources extends ClientBundle {
	public static final CfgResources INSTANCE = GWT.create(CfgResources.class);

	@Source("img/grey-wallpaper.jpg")
	ImageResource profileBackgroundImg();
}