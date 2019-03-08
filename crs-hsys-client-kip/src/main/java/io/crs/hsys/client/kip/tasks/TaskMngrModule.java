/**
 * 
 */
package io.crs.hsys.client.kip.tasks;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.task.editor.TaskEditorView;

/**
 * @author robi
 *
 */
public class TaskMngrModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(TaskMngrPresenter.class, TaskMngrPresenter.MyView.class, TaskMngrView.class,
				TaskMngrPresenter.MyProxy.class);
		bind(TaskWidget2.class);
		bind(TaskEditorView.class);
	}
}
