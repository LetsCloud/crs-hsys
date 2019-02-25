/**
 * 
 */
package io.crs.hsys.client.kip.browser.tasktodo;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class TaskTodoBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		bindPresenterWidget(HkTaskTodoBrowserPresenter.class, HkTaskTodoBrowserPresenter.MyView.class,
				HkTaskTodoBrowserView.class);

		install(new GinFactoryModuleBuilder().build(TaskTodoBrowserFactory.class));
	}
}
