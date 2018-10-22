/**
 * 
 */
package io.crs.hsys.client.cfg.gin;

import javax.inject.Inject;

import io.crs.hsys.client.core.resources.GreyThemeResources;

/**
 * @author CR
 *
 */
public class CfgResourceLoader {
	@Inject
    CfgResourceLoader(GreyThemeResources resources) {
        resources.override().ensureInjected();
    }
}
