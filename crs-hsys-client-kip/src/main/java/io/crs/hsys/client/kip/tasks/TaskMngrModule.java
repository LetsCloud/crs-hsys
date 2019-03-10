/**
 * 
 */
package io.crs.hsys.client.kip.tasks;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.tasks.widget.TaskWidget2;

/**
 * @author robi
 *
 */
public class TaskMngrModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(TaskMngrPresenter.class, TaskMngrPresenter.MyView.class, TaskMngrView.class,
				TaskMngrPresenter.MyProxy.class);
		bind(TaskWidget.class);
		bind(TaskWidget2.class);
	}
}
