/**
 * 
 */
package io.crs.hsys.client.kip.gin;

import javax.inject.Inject;

import io.crs.hsys.client.core.resources.HwBlueResources;
import io.crs.hsys.client.kip.resources.KipGssResources;

/**
 * @author CR
 *
 */
public class ResourceLoader {
	@Inject
	ResourceLoader(HwBlueResources resources, KipGssResources kipGssRes) {
        resources.override().ensureInjected();
		kipGssRes.chatStyle().ensureInjected();
		kipGssRes.taskStyle().ensureInjected();
    }
}
