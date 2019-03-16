/**
 * 
 */
package io.crs.hsys.client.kip.browser.task;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class TaskMngrModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(TaskMngrPresenter.class, TaskMngrPresenter.MyView.class, TaskMngrView.class,
				TaskMngrPresenter.MyProxy.class);
//		bind(TaskWidget.class);
	}
}
