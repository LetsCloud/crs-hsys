/**
 * 
 */
package io.crs.hsys.client.kip.task.creator;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class TaskCreatorModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(TaskCreatorPresenter.class, TaskCreatorPresenter.MyView.class, TaskCreatorView.class);

		install(new GinFactoryModuleBuilder().build(TaskCreatorFactory.class));
	}
}
