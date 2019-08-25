/**
 * 
 */
package io.crs.hsys.client.fro.browser.ratecode;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.fro.editor.ratecode.RateCodeEditorModule;

/**
 * @author robi
 *
 */
public class RateCodeBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new RateCodeEditorModule());

		bindPresenterWidget(RateCodeBrowserPresenter.class, RateCodeBrowserPresenter.MyView.class,
				RateCodeBrowserView.class);

		install(new GinFactoryModuleBuilder().build(RateCodeBrowserFactory.class));
	}
}
