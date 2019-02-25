/**
 * 
 */
package io.crs.hsys.client.kip.meditor.tasktodo;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class TaskTodoEditorModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(HkTaskTodoEditorPresenter.class, HkTaskTodoEditorPresenter.MyView.class,
				HkTaskTodoEditorView.class);

		install(new GinFactoryModuleBuilder().build(TaskTodoEditorFactory.class));
	}
}
