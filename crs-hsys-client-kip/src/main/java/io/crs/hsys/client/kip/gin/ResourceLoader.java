/**
 * 
 */
package io.crs.hsys.client.kip.gin;

import javax.inject.Inject;

import io.crs.hsys.client.core.resources.BlueThemeResources;
import io.crs.hsys.client.kip.resources.KipGssResources;
import io.crs.hsys.client.kip.resources.KipResources;

/**
 * @author CR
 *
 */
public class ResourceLoader {
	@Inject
	ResourceLoader(KipResources kipResources, BlueThemeResources resources, KipGssResources kipGssRes) {
        resources.override().ensureInjected();
		kipGssRes.chatStyle().ensureInjected();
		kipGssRes.taskStyle().ensureInjected();
    }
}
