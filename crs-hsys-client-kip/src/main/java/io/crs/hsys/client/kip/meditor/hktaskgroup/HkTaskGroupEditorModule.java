/**
 * 
 */
package io.crs.hsys.client.kip.meditor.hktaskgroup;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class HkTaskGroupEditorModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(HkTaskGroupEditorPresenter.class, HkTaskGroupEditorPresenter.MyView.class,
				HkTaskGroupEditorView.class);

		install(new GinFactoryModuleBuilder().build(HkTaskGroupEditorFactory.class));
	}
}
