/**
 * 
 */
package io.crs.hsys.client.cfg.browser.qtnstatus;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.cfg.meditor.qtnstatus.QuotationStatusEditorModule;

/**
 * @author robi
 *
 */
public class QuotationStatusBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new QuotationStatusEditorModule());

		bindPresenterWidget(QuotationStatusBrowserPresenter.class, QuotationStatusBrowserPresenter.MyView.class,
				QuotationStatusBrowserView.class);

		install(new GinFactoryModuleBuilder().build(QuotationStatusBrowserFactory.class));
	}
}
