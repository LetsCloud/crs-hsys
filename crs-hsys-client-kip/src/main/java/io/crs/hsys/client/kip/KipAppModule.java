package io.crs.hsys.client.kip;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.core.app.AppView;
import io.crs.hsys.client.kip.KipAppPresenter.MyProxy;

public class KipAppModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(KipAppPresenter.class, KipAppPresenter.MyView.class, AppView.class,
				KipAppPresenter.MyProxy.class);
	}
}
