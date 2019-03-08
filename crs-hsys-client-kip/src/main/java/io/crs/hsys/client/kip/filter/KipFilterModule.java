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
import io.crs.hsys.client.kip.filter.taskgroup.TaskGroupFilterPresenter;
import io.crs.hsys.client.kip.filter.taskgroup.TaskGroupFilterView;
import io.crs.hsys.client.kip.filter.tasks.TasksFilterPresenter;
import io.crs.hsys.client.kip.filter.tasks.TasksFilterView;
import io.crs.hsys.client.kip.filter.tasktodo.TaskTodoFilterPresenter;
import io.crs.hsys.client.kip.filter.tasktodo.TaskTodoFilterView;

/**
 * @author robi
 *
 */
public class KipFilterModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(TaskGroupFilterPresenter.class, TaskGroupFilterPresenter.MyView.class,
				TaskGroupFilterView.class);

		bindPresenterWidget(TaskTodoFilterPresenter.class, TaskTodoFilterPresenter.MyView.class,
				TaskTodoFilterView.class);

		bindPresenterWidget(TasksFilterPresenter.class, TasksFilterPresenter.MyView.class, TasksFilterView.class);

		bindPresenterWidget(AssignmentFilterPresenter.class, AssignmentFilterPresenter.MyView.class,
				AssignmentFilterView.class);

		bindPresenterWidget(RoomStatusFilterPresenter2.class, RoomStatusFilterPresenter2.MyView.class,
				RoomStatusFilterView2.class);

		install(new GinFactoryModuleBuilder().build(KipFilterPresenterFactory.class));
	}
}
