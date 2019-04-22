/**
 * 
 */
package io.crs.hsys.client.cfg.config.doc;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.cfg.browser.qtnstatus.QuotationStatusBrowserModule;

/**
 * @author robi
 *
 */
public class DocConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new QuotationStatusBrowserModule());

		bindPresenter(DocConfigPresenter.class, DocConfigPresenter.MyView.class, DocConfigView.class,
				DocConfigPresenter.MyProxy.class);
	}
}
