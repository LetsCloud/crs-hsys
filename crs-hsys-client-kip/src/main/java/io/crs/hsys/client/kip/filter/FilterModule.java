/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.filter.assignment.AssignmentFilterPresenter;
import io.crs.hsys.client.kip.filter.assignment.AssignmentFilterView;

/**
 * @author robi
 *
 */
public class FilterModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(AssignmentFilterPresenter.class, AssignmentFilterPresenter.MyView.class,
				AssignmentFilterView.class);

		install(new GinFactoryModuleBuilder().build(FilterPresenterFactory.class));
	}
}
