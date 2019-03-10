/**
 * 
 */
package io.crs.hsys.client.kip.tasks.editor;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class TaskEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(TaskEditorPresenter.class, TaskEditorPresenter.MyView.class, TaskEditorView.class,
				TaskEditorPresenter.MyProxy.class);
	}
}
