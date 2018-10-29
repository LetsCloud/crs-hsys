/**
 * 
 */
package io.crs.hsys.client.admin.browser.globalconfig;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.admin.meditor.globalconfig.GlobalConfigEditorModule;

/**
 * @author robi
 *
 */
public class GlobalConfigBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new GlobalConfigEditorModule());

		bindPresenterWidget(GlobalConfigBrowserPresenter.class, GlobalConfigBrowserPresenter.MyView.class,
				GlobalConfigBrowserView.class);

		install(new GinFactoryModuleBuilder().build(GlobalConfigBrowserFactory.class));
	}
}
