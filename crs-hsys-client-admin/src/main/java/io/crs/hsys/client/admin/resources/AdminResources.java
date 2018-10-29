/**
 * 
 */
package io.crs.hsys.client.admin.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author CR
 *
 */
public interface AdminResources extends ClientBundle {
	public static final AdminResources INSTANCE = GWT.create(AdminResources.class);

	@Source("img/grey-blue-wallpaper-3.jpg")
	ImageResource profileBackgroundImg();
}