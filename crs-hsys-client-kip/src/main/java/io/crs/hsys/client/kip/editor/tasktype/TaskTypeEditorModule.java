/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.editor.tasktype.todo.AddTaskTodoPresenter;
import io.crs.hsys.client.kip.editor.tasktype.todo.AddTaskTodoView;
import io.crs.hsys.client.kip.editor.tasktype.todo.TaskTodoEditor;
import io.crs.hsys.client.kip.editor.tasktype.todo.TaskTodoListEditor;

/**
 * @author robi
 *
 */
public class TaskTypeEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(TaskTypeEditorPresenter.class, TaskTypeEditorPresenter.MyView.class, TaskTypeEditorView.class,
				TaskTypeEditorPresenter.MyProxy.class);
		bind(TaskTodoListEditor.class);
		bind(TaskTodoEditor.class);
		bindPresenterWidget(AddTaskTodoPresenter.class, AddTaskTodoPresenter.MyView.class, AddTaskTodoView.class);

		install(new GinFactoryModuleBuilder().build(TaskTypeEditorFactory.class));
	}
}
