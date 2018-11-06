/**
 * 
 */
package io.crs.hsys.client.kip.gfilter.config;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class GfilterConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindSingletonPresenterWidget(GfilterConfigPresenter.class, GfilterConfigPresenter.MyView.class,
				GfilterConfigView.class);

		install(new GinFactoryModuleBuilder().build(GfilterConfigPresenterFactory.class));
	}

}
