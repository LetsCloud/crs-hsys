/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.filter.assignment.AssignmentFilterPresenter;
import io.crs.hsys.client.kip.filter.assignment.AssignmentFilterView;
import io.crs.hsys.client.kip.filter.roomstatus.RoomStatusFilterPresenter2;
import io.crs.hsys.client.kip.filter.roomstatus.RoomStatusFilterView2;

/**
 * @author robi
 *
 */
public class KipFilterModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(AssignmentFilterPresenter.class, AssignmentFilterPresenter.MyView.class,
				AssignmentFilterView.class);
		bindPresenterWidget(RoomStatusFilterPresenter2.class, RoomStatusFilterPresenter2.MyView.class,
				RoomStatusFilterView2.class);

		install(new GinFactoryModuleBuilder().build(KipFilterPresenterFactory.class));
	}
}
