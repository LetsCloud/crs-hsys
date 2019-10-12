/**
 * 
 */
package io.crs.hsys.client.kip.gin;

import javax.inject.Inject;

import io.crs.hsys.client.core.resources.ThemeResourcesBlue;
import io.crs.hsys.client.core.resources.GssResources;
import io.crs.hsys.client.kip.resources.KipGssResources;
import io.crs.hsys.client.kip.resources.KipResources;

/**
 * @author CR
 *
 */
public class ResourceLoader {
	@Inject
	ResourceLoader(KipResources kipResources, ThemeResourcesBlue resources, GssResources gssResources,
			KipGssResources kipGssRes) {
		resources.override().ensureInjected();
		gssResources.common().ensureInjected();
		kipGssRes.chatStyle().ensureInjected();
		kipGssRes.taskStyle().ensureInjected();
	}
}
