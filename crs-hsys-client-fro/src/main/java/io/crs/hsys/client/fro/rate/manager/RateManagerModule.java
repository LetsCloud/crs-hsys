/**
 * 
 */
package io.crs.hsys.client.fro.rate.manager;

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
