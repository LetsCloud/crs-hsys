/**
 * 
 */
package io.crs.hsys.client.fro.gin;

import javax.inject.Inject;

import io.crs.hsys.client.core.resources.RedThemeResources;
import io.crs.hsys.client.fro.resources.FroGssResources;
import io.crs.hsys.client.fro.resources.FroResources;

/**
 * @author CR
 *
 */
public class ResourceLoader {
	@Inject
	ResourceLoader(FroResources kipResources, RedThemeResources resources, FroGssResources froGssRes) {
        resources.override().ensureInjected();
		froGssRes.bookingStyle().ensureInjected();

    }
}
