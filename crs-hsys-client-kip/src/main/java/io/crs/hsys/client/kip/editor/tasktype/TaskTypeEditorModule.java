/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

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
	}
}
