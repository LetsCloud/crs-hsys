package io.crs.hsys.client.admin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.core.app.AppView;

public class AdminAppModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(AdminAppPresenter.class, AdminAppPresenter.MyView.class, AppView.class,
				AdminAppPresenter.MyProxy.class);
	}
}
