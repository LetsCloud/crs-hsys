/**
 * 
 */
package io.crs.hsys.client.cfg.browser.organization;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class OrganizationBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		bindPresenterWidget(OrganizationBrowserPresenter.class, OrganizationBrowserPresenter.MyView.class,
				OrganizationBrowserView.class);

		install(new GinFactoryModuleBuilder().build(OrganizationBrowserFactory.class));
	}
}
