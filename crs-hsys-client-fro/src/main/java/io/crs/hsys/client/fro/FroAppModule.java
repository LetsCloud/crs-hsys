package io.crs.hsys.client.fro;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.core.app.AppView;

public class FroAppModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(FroAppPresenter.class, FroAppPresenter.MyView.class, AppView.class,
				FroAppPresenter.MyProxy.class);
	}
}
