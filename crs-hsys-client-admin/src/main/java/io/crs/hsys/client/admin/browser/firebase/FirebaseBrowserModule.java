/**
 * 
 */
package io.crs.hsys.client.admin.browser.firebase;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.admin.meditor.firebase.FirebaseEditorModule;

/**
 * @author robi
 *
 */
public class FirebaseBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new FirebaseEditorModule());

		bindPresenterWidget(FirebaseBrowserPresenter.class, FirebaseBrowserPresenter.MyView.class,
				FirebaseBrowserView.class);

		install(new GinFactoryModuleBuilder().build(FirebaseBrowserFactory.class));
	}
}
