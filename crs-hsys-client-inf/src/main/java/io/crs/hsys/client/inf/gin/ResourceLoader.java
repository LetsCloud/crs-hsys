package io.crs.hsys.client.inf.gin;

import javax.inject.Inject;

import com.google.gwt.dom.client.StyleInjector;

import io.crs.hsys.client.core.resources.ThemeResourcesOrange;
import io.crs.hsys.client.inf.resources.InfResources;

public class ResourceLoader {
	@Inject
	ResourceLoader(ThemeResourcesOrange resources, InfResources infRes) {
        resources.override().ensureInjected();
		StyleInjector.injectAtEnd(infRes.infCss().getText());
    }
}
