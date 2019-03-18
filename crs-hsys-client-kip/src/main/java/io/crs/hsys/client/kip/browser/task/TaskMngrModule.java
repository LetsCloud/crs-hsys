/**
 * 
 */
package io.crs.hsys.client.kip.browser.task;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.browser.task.widget.TaskBodyWidget;
import io.crs.hsys.client.kip.browser.task.widget.TaskHeaderWidget;
import io.crs.hsys.client.kip.browser.task.widget.TaskNoteWidget;

/**
 * @author robi
 *
 */
public class TaskMngrModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(TaskMngrPresenter.class, TaskMngrPresenter.MyView.class, TaskMngrView.class,
				TaskMngrPresenter.MyProxy.class);
		bind(TaskHeaderWidget.class);
		bind(TaskBodyWidget.class);
		bind(TaskNoteWidget.class);
	}
}
