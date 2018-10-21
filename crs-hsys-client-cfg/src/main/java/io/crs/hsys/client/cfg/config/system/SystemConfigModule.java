/**
 * 
 */
package io.crs.hsys.client.cfg.config.system;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.cfg.browser.appuser.AppUserBrowserModule;
import io.crs.hsys.client.cfg.browser.usergroup.UserGroupBrowserModule;

/**
 * @author CR
 *
 */
public class SystemConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new UserGroupBrowserModule());
		install(new AppUserBrowserModule());

		bindPresenter(SystemConfigPresenter.class, SystemConfigPresenter.MyView.class, SystemConfigView.class,
				SystemConfigPresenter.MyProxy.class);
	}
}
