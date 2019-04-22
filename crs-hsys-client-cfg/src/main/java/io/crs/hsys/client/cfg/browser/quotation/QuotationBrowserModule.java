/**
 * 
 */
package io.crs.hsys.client.cfg.browser.quotation;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class QuotationBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		bindPresenterWidget(QuotationBrowserPresenter.class, QuotationBrowserPresenter.MyView.class,
				QuotationBrowserView.class);

		install(new GinFactoryModuleBuilder().build(QuotationBrowserFactory.class));
	}
}
