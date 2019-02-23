/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import io.crs.hsys.client.kip.filter.assignment.AssignmentFilterPresenter;
import io.crs.hsys.client.kip.filter.roomstatus.RoomStatusFilterPresenter2;
import io.crs.hsys.client.kip.filter.taskgroup.TaskGroupFilterPresenter;

/**
 * @author robi
 *
 */
public interface KipFilterPresenterFactory {

	TaskGroupFilterPresenter createTaskGroupFilterPresenter();

	AssignmentFilterPresenter createAssignmentFilterPresenter();

	RoomStatusFilterPresenter2 createRoomStatusFilter();

}
