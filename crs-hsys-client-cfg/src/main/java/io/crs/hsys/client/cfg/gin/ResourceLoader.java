/**
 * 
 */
package io.crs.hsys.client.cfg.gin;

import javax.inject.Inject;

import io.crs.hsys.client.core.resources.HwRedResources;

/**
 * @author CR
 *
 */
public class ResourceLoader {
	@Inject
    ResourceLoader(HwRedResources resources) {
        resources.override().ensureInjected();
    }
}
