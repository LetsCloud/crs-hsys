/**
 * 
 */
package io.crs.hsys.client.admin.gin;

import javax.inject.Inject;

import io.crs.hsys.client.admin.resources.AdminResources;
import io.crs.hsys.client.core.resources.GreyThemeResources;

/**
 * @author CR
 *
 */
public class ResourceLoader {
	@Inject
	ResourceLoader(AdminResources adminResources, GreyThemeResources resources) {
		resources.override().ensureInjected();
	}
}
