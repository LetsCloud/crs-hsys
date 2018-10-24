/**
 * 
 */
package io.crs.hsys.client.cfg.gin;

import javax.inject.Inject;

import io.crs.hsys.client.cfg.resources.CfgResources;
import io.crs.hsys.client.core.resources.GreyThemeResources;

/**
 * @author CR
 *
 */
public class CfgResourceLoader {
	@Inject
    CfgResourceLoader(CfgResources cfgResources, GreyThemeResources resources) {
        resources.override().ensureInjected();
    }
}
