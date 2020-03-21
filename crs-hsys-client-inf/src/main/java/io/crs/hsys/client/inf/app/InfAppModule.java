package io.crs.hsys.client.inf.app;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.core.app.AppView;

public class InfAppModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(InfAppPresenter.class, InfAppPresenter.MyView.class, AppView.class,
				InfAppPresenter.MyProxy.class);
	}
}
