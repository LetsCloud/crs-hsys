/**
 * 
 */
package io.crs.hsys.client.core.meditor.usergroup;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class UserGroupEditorModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(UserGroupEditorPresenter.class, UserGroupEditorPresenter.MyView.class,
				UserGroupEditorView.class);

		install(new GinFactoryModuleBuilder().build(UserGroupEditorFactory.class));
	}
}
