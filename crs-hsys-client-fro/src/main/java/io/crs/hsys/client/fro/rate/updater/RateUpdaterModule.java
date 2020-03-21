/**
 * 
 */
package io.crs.hsys.client.fro.rate.updater;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class RateUpdaterModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(RateUpdaterPresenter.class, RateUpdaterPresenter.MyView.class, RateUpdaterView.class,
				RateUpdaterPresenter.MyProxy.class);
	}
}
