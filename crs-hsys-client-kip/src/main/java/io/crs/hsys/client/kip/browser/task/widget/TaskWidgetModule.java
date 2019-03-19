/**
 * 
 */
package io.crs.hsys.client.kip.browser.task.widget;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class TaskWidgetModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(TaskWidgetPresenter.class, TaskWidgetPresenter.MyView.class, TaskWidgetView.class);
		bind(TaskNoteWidget.class);

		install(new GinFactoryModuleBuilder().build(TaskWidgetFactory.class));
	}
}
