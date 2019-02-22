/**
 * 
 */
package io.crs.hsys.client.kip.meditor.taskgroup;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class TaskGroupEditorModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(HkTaskGroupEditorPresenter.class, HkTaskGroupEditorPresenter.MyView.class,
				HkTaskGroupEditorView.class);

		bindPresenterWidget(MtTaskGroupEditorPresenter.class, MtTaskGroupEditorPresenter.MyView.class,
				MtTaskGroupEditorView.class);

		install(new GinFactoryModuleBuilder().build(TaskGroupEditorFactory.class));
	}
}
