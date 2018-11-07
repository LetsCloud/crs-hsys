/**
 * 
 */
package io.crs.hsys.client.kip.assignments.editor;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class AssignmentEditorModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindSingletonPresenterWidget(AssignmentEditorPresenter.class, AssignmentEditorPresenter.MyView.class,
				AssignmentEditorView.class);

		install(new GinFactoryModuleBuilder().build(AssignmentEditorFactory.class));
	}
}
