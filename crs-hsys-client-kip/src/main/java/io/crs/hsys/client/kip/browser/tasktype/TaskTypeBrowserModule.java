/**
 * 
 */
package io.crs.hsys.client.kip.browser.tasktype;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.editor.tasktype.TaskTypeEditorModule;

/**
 * @author robi
 *
 */
public class TaskTypeBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new TaskTypeEditorModule());

		bindPresenterWidget(HkTaskTypeBrowserPresenter.class, HkTaskTypeBrowserPresenter.MyView.class,
				HkTaskTypeBrowserView.class);

		bindPresenterWidget(MtTaskTypeBrowserPresenter.class, MtTaskTypeBrowserPresenter.MyView.class,
				MtTaskTypeBrowserView.class);

		install(new GinFactoryModuleBuilder().build(TaskTypeBrowserFactory.class));
	}
}
