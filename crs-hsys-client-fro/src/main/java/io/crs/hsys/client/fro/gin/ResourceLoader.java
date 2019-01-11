/**
 * 
 */
package io.crs.hsys.client.fro.gin;

import javax.inject.Inject;

import io.crs.hsys.client.core.resources.HwRedResources;
import io.crs.hsys.client.fro.resources.FroResources;

/**
 * @author CR
 *
 */
public class ResourceLoader {
	@Inject
	ResourceLoader(FroResources kipResources, HwRedResources resources) {
        resources.override().ensureInjected();
    }
}
