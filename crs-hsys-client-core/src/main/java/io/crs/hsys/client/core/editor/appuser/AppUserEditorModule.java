package io.crs.hsys.client.core.editor.appuser;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class AppUserEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(AppUserEditorPresenter.class, AppUserEditorPresenter.MyView.class, AppUserEditorView.class,
				AppUserEditorPresenter.MyProxy.class);
	}
}
