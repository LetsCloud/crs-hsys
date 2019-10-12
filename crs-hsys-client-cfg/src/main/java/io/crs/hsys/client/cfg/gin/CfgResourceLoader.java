/**
 * 
 */
package io.crs.hsys.client.cfg.gin;

import javax.inject.Inject;

import io.crs.hsys.client.cfg.resources.CfgResources;
import io.crs.hsys.client.core.resources.ThemeResourcesBlueGrey;

/**
 * @author CR
 *
 */
public class CfgResourceLoader {
	@Inject
    CfgResourceLoader(CfgResources cfgResources, ThemeResourcesBlueGrey resources) {
        resources.override().ensureInjected();
    }
}
