/**
 * 
 */
package io.crs.hsys.client.cfg.gin;

import javax.inject.Inject;

import io.crs.hsys.client.cfg.resources.CfgResources;
import io.crs.hsys.client.core.resources.BlueGreyThemeResources;

/**
 * @author CR
 *
 */
public class CfgResourceLoader {
	@Inject
    CfgResourceLoader(CfgResources cfgResources, BlueGreyThemeResources resources) {
        resources.override().ensureInjected();
    }
}
