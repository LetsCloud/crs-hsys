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
public class CfgResourceLoader {
	@Inject
    CfgResourceLoader(HwRedResources resources) {
        resources.override().ensureInjected();
    }
}
