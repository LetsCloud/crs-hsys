/**
 * 
 */
package io.crs.hsys.client.kip.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * @author CR
 *
 */
public interface KipMessages extends Messages {

	/*
	 * MAIN MENU
	 */

	@DefaultMessage("Dashboard")
	String mainMenuItemDashboard();

	@DefaultMessage("Housekeeping Dashboard")
	String mainMenuItemHousekeepingDashboard();

	@DefaultMessage("Maintenance Dashboard")
	String mainMenuItemDashboardMaintenance();

	@DefaultMessage("Reception Dashboard")
	String mainMenuItemReceptionDashboard();

	@DefaultMessage("Chat Room")
	String mainMenuItemChatRoom();

	@DefaultMessage("Tasks")
	String mainMenuItemTasks();

	@DefaultMessage("Guest Rooms")
	String mainMenuItemGuestRooms();

	@DefaultMessage("OOO Rooms")
	String mainMenuItemGuestOoo();

	@DefaultMessage("Public Areas")
	String mainMenuItemPublicAreas();

	@DefaultMessage("Task Assignment")
	String mainMenuGroupTaskAssignment();

	@DefaultMessage("Room Tasks Assignment")
	String mainMenuItemRoomTasksAssignment();

	@DefaultMessage("Public Area Tasks Assignment")
	String mainMenuItemAreaTasksAssignment();

	@DefaultMessage("Maintenance Assignment")
	String mainMenuItemMaintenanceAssignment();

	@DefaultMessage("Minibar")
	String mainMenuGroupMinibar();

	@DefaultMessage("Minibar Consumption")
	String mainMenuItemConsumption();

	@DefaultMessage("Beállítások")
	String mainMenuGroupConfig();

	@DefaultMessage("Users and permissions")
	String mainMenuItemUsersConfig();

	@DefaultMessage("Hotel configuration")
	String mainMenuItemHotelConfig();

	@DefaultMessage("Housekeeping")
	String mainMenuItemHousekeepingConfig();

	@DefaultMessage("Maintenance")
	String mainMenuItemMaintenanceConfig();

	/*
	 * DASHBOARD
	 */
	@DefaultMessage("Maintenance")
	String dashboardSubtitle();

	@DefaultMessage("Maintenance")
	String dashboardMaintenanceSubtitle();

	@DefaultMessage("Maintenance")
	String dashboardReceptionSubtitle();

	/*
	 * CHAT ROOM
	 */

	@DefaultMessage("Chat Room")
	String chatRoomTitle();

	@DefaultMessage("All attendants")
	String chatRoomDescription();

	/*
	 * ATTENDANTS
	 */

	@DefaultMessage("Attendants")
	String attendantsTitle();

	@DefaultMessage("All attendants")
	String attendantsDescription();

	/*
	 * ASSIGNMENTS
	 */

	@DefaultMessage("Assignments")
	String assignmentsTitle();

	@DefaultMessage("All assignments")
	String assignmentsDescription();

	@DefaultMessage("Tasks assigned to {0}")
	String assignmentsTasksAssignedTo(String attendant);

	@DefaultMessage("AssignedTo")
	String assignmentsAssignedTo();

	@DefaultMessage("Inspector")
	String assignmentsInspector();

	@DefaultMessage("CleanType")
	String assignmentsCleanType();

	/*
	 * ROOM STATUS FILTER
	 */

	@DefaultMessage("Occupancy Statuses")
	String roomStatusFilterOccupancyStatusTitle();

	@DefaultMessage("Vacant")
	String roomStatusFilterOccupancyVacant();

	@DefaultMessage("Early Check-in")
	String roomStatusFilterOccupancyEarlyCheckIn();

	@DefaultMessage("Check-in")
	String roomStatusFilterOccupancyCheckIn();

	@DefaultMessage("Checked in")
	String roomStatusFilterOccupancyCheckedIn();

	@DefaultMessage("Stay Over")
	String roomStatusFilterOccupancyStayOver();

	@DefaultMessage("Checkout")
	String roomStatusFilterOccupancyCheckout();

	@DefaultMessage("Late Checkout")
	String roomStatusFilterOccupancyLateCheckout();

	@DefaultMessage("Checked out")
	String roomStatusFilterOccupancyCheckedOut();

	@DefaultMessage("Room Statuses")
	String roomStatusFilterRoomStatusTitle();

	@DefaultMessage("Dirty")
	String roomStatusFilterRoomDirty();

	@DefaultMessage("Clean")
	String roomStatusFilterRoomClean();

	@DefaultMessage("Inspected")
	String roomStatusFilterRoomInspected();

	@DefaultMessage("Out Of Order")
	String roomStatusFilterRoomOoo();

	@DefaultMessage("Out Of Service")
	String roomStatusFilterRoomOos();

	@DefaultMessage("Show Room")
	String roomStatusFilterRoomShow();

	@DefaultMessage("Room number")
	String roomStatusFilterRoomNumberLabel();

	@DefaultMessage("Type a room number")
	String roomStatusFilterRoomNumberPlaceholder();

	@DefaultMessage("Room:{0}")
	String roomStatusFilterRoomNumberChip(String roomNumber);

	@DefaultMessage("Room Type")
	String roomStatusFilterRoomTypeLabel();

	@DefaultMessage("Choose Room Type")
	String roomStatusFilterRoomTypePlaceholder();

	@DefaultMessage("All Types")
	String roomStatusFilterRoomTypeAll();

	@DefaultMessage("Floor")
	String roomStatusFilterRoomFloorLabel();

	@DefaultMessage("Type a floor code")
	String roomStatusFilterRoomFloorPlaceholder();

	@DefaultMessage("{0}. floor")
	String roomStatusFilterRoomFloorChip(String floor);

	/*
	 * Housekeeping Config
	 */
	@DefaultMessage("Housekeeping Configuration")
	String housekeepingConfigTitle();

	@DefaultMessage("The Housekeeping Configuration form is used to add and modify the Clean TaskGroup and Clean TaskTypes.")
	String housekeepingConfigDescription();

	@DefaultMessage("Task Groups")
	String hkTaskGroupBrowserTitle();

	@DefaultMessage("Task Todos")
	String hkTaskTodoBrowserTitle();

	@DefaultMessage("Task Types")
	String hkTaskTypeBrowserTitle();

	@DefaultMessage("Standard Room Cleaning Types")
	String hkConfigStandardTypesItem();

	@DefaultMessage("Public Area Cleaning Setup")
	String hkConfigPublicAreaCleaningSetup();
	
	/*
	 * Maintenance Config
	 */
	@DefaultMessage("Maintenance Configuration")
	String mtConfigTitle();

	@DefaultMessage("The Maintenance Configuration form is used to add and modify the Clean TaskGroup and Clean TaskTypes.")
	String mtConfigDescription();
	
	@DefaultMessage("Task Groups")
	String mtConfigTaskGroupItem();
	
	@DefaultMessage("Task Todos")
	String mtConfigTaskTodoItem();
	
	@DefaultMessage("Task Types")
	String mtConfigTaskTypeItem();
}
