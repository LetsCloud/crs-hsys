/**
 * 
 */
package io.crs.hsys.client.admin.gin;

import javax.inject.Inject;

import io.crs.hsys.client.admin.resources.AdminResources;
import io.crs.hsys.client.core.resources.ThemeResourcesGrey;

/**
 * @author CR
 *
 */
public class ResourceLoader {
	@Inject
	ResourceLoader(AdminResources adminResources, ThemeResourcesGrey resources) {
		resources.override().ensureInjected();
	}
}
