/**
 * 
 */
package io.crs.hsys.client.fro.ratemanager;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class RateManagerModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(RateManagerPresenter.class, RateManagerPresenter.MyView.class, RateManagerView.class,
				RateManagerPresenter.MyProxy.class);
	}
}
