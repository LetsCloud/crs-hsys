/**
 * 
 */
package io.crs.hsys.client.inf.dashboard;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class DashboardModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(DashboardPresenter.class, DashboardPresenter.MyView.class, DashboardView.class,
				DashboardPresenter.MyProxy.class);
	}
}
