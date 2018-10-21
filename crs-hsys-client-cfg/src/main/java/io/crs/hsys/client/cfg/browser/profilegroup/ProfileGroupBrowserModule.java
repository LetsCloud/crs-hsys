/**
 * 
 */
package io.crs.hsys.client.cfg.browser.profilegroup;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.cfg.meditor.profilegroup.ProfileGroupEditorModule;

/**
 * @author robi
 *
 */
public class ProfileGroupBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new ProfileGroupEditorModule());

		bindPresenterWidget(ProfileGroupBrowserPresenter.class, ProfileGroupBrowserPresenter.MyView.class,
				ProfileGroupBrowserView.class);

		install(new GinFactoryModuleBuilder().build(ProfileGroupBrowserFactory.class));
	}
}
