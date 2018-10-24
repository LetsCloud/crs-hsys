/**
 * 
 */
package io.crs.hsys.client.cfg.editor.profile.organization;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class OrganizationEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(OrganizationEditorPresenter.class, OrganizationEditorPresenter.MyView.class,
				OrganizationEditorView.class);

		install(new GinFactoryModuleBuilder().build(OrganizationEditorFactory.class));
	}
}
