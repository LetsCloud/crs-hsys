/**
 * 
 */
package io.crs.hsys.client.admin.meditor.firebase;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class FirebaseEditorModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(FirebaseEditorPresenter.class, FirebaseEditorPresenter.MyView.class,
				FirebaseEditorView.class);

		install(new GinFactoryModuleBuilder().build(FirebaseEditorFactory.class));
	}
}
