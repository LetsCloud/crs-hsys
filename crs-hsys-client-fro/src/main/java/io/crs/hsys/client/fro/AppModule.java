package io.crs.hsys.client.fro;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.core.app.AppView;

public class AppModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(AppPresenter.class, AppPresenter.MyView.class, AppView.class,
				AppPresenter.MyProxy.class);
	}
}
