/**
 * 
 */
package io.crs.hsys.client.cfg.browser.contact;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class ContactBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		bindPresenterWidget(ContactBrowserPresenter.class, ContactBrowserPresenter.MyView.class,
				ContactBrowserView.class);

		install(new GinFactoryModuleBuilder().build(ContactBrowserFactory.class));
	}
}
