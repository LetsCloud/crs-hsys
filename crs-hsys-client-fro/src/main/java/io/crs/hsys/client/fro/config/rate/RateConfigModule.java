/**
 * 
 */
package io.crs.hsys.client.fro.config.rate;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.fro.browser.ratecode.RateCodeBrowserModule;
import io.crs.hsys.client.fro.ratemanager.RateManagerModule;

/**
 * @author robi
 *
 */
public class RateConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new RateCodeBrowserModule());

		bindPresenter(RateConfigPresenter.class, RateConfigPresenter.MyView.class, RateConfigView.class,
				RateConfigPresenter.MyProxy.class);
	}
}
