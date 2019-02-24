/**
 * 
 */
package io.crs.hsys.client.kip.config.hk;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.browser.taskgroup.TaskGroupBrowserModule;
import io.crs.hsys.client.kip.browser.tasktodo.TaskTodoBrowserModule;
import io.crs.hsys.client.kip.browser.tasktype.TaskTypeBrowserModule;
import io.crs.hsys.client.kip.meditor.taskgroup.TaskGroupEditorModule;
import io.crs.hsys.client.kip.meditor.tasktodo.TaskTodoEditorModule;

/**
 * @author robi
 *
 */
public class HkConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new TaskGroupBrowserModule());
		install(new TaskGroupEditorModule());
		install(new TaskTodoBrowserModule());
		install(new TaskTodoEditorModule());
		install(new TaskTypeBrowserModule());

		bindPresenter(HkConfigPresenter.class, HkConfigPresenter.MyView.class, HkConfigView.class,
				HkConfigPresenter.MyProxy.class);
	}
}
