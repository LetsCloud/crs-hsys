/**
 * 
 */
package io.crs.hsys.client.admin.config.system;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.admin.browser.globalconfig.GlobalConfigBrowserModule;

/**
 * @author robi
 *
 */
public class SystemConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new GlobalConfigBrowserModule());

		bindPresenter(SystemConfigPresenter.class, SystemConfigPresenter.MyView.class, SystemConfigView.class,
				SystemConfigPresenter.MyProxy.class);
	}
}
