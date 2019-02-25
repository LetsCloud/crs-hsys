/**
 * 
 */
package io.crs.hsys.client.kip.browser.taskgroup;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class TaskGroupBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

//		install(new RoomTypeEditorModule());

		bindPresenterWidget(HkTaskGroupBrowserPresenter.class, HkTaskGroupBrowserPresenter.MyView.class,
				HkTaskGroupBrowserView.class);

		bindPresenterWidget(MtTaskGroupBrowserPresenter.class, MtTaskGroupBrowserPresenter.MyView.class,
				MtTaskGroupBrowserView.class);

		bindPresenterWidget(AdminTaskGroupBrowserPresenter.class, AdminTaskGroupBrowserPresenter.MyView.class,
				AdminTaskGroupBrowserView.class);

		install(new GinFactoryModuleBuilder().build(TaskGroupBrowserFactory.class));
	}
}
