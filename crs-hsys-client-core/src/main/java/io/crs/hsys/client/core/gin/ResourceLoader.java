/**
 * 
 */
package io.crs.hsys.client.core.gin;

import javax.inject.Inject;

import com.google.gwt.dom.client.StyleInjector;

import io.crs.hsys.client.core.resources.CoreResources;
import io.crs.hsys.client.core.resources.GssResources;

/**
 * @author CR
 *
 */
public class ResourceLoader {
	@Inject
    ResourceLoader(CoreResources resources, GssResources gssResources) {

		StyleInjector.injectAtStart(resources.materialize().getText());
		StyleInjector.inject(resources.gwtMaterial().getText());
		StyleInjector.injectAtEnd(resources.coreCss().getText());
		
//		resources.wallpaperCss().ensureInjected();
		gssResources.common().ensureInjected();
       
    }
}
