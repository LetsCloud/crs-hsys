package io.crs.hsys.client.cfg;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.core.app.AppView;

public class CfgAppModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(CfgAppPresenter.class, CfgAppPresenter.MyView.class, AppView.class,
				CfgAppPresenter.MyProxy.class);
	}
}
