/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import io.crs.hsys.client.kip.filter.assignment.AssignmentFilterPresenter;
import io.crs.hsys.client.kip.filter.oooroom.OooRoomFilterPresenter;
import io.crs.hsys.client.kip.filter.roomstatus.RoomStatusFilterPresenter2;
import io.crs.hsys.client.kip.filter.taskgroup.TaskGroupFilterPresenter;
import io.crs.hsys.client.kip.filter.tasks.TasksFilterPresenter;
import io.crs.hsys.client.kip.filter.tasktodo.TaskTodoFilterPresenter;

/**
 * @author robi
 *
 */
public interface KipFilterPresenterFactory {

	TaskGroupFilterPresenter createTaskGroupFilter();

	TaskTodoFilterPresenter createTaskTodoFilter();

	TasksFilterPresenter createTasksFilter();

	AssignmentFilterPresenter createAssignmentFilter();

	RoomStatusFilterPresenter2 createRoomStatusFilter();

	OooRoomFilterPresenter createOooRoomFilter();

}
