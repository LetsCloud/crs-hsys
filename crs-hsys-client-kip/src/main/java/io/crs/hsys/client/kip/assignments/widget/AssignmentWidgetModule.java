/**
 * 
 */
package io.crs.hsys.client.kip.assignments.widget;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class AssignmentWidgetModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
        bindPresenterWidget(AssignmentWidgetPresenter.class, AssignmentWidgetPresenter.MyView.class, AssignmentWidgetView.class);

		install(new GinFactoryModuleBuilder().build(AssignmentWidgetFactory.class));
	}
}
