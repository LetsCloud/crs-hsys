/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import io.crs.hsys.client.kip.filter.assignment.AssignmentFilterPresenter;
import io.crs.hsys.client.kip.filter.roomstatus.RoomStatusFilterPresenter2;

/**
 * @author robi
 *
 */
public interface FilterPresenterFactory {

	AssignmentFilterPresenter createAssignmentFilterPresenter();
	RoomStatusFilterPresenter2 createRoomStatusFilter();

}
