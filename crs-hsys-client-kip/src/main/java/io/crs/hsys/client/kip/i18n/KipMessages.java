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

	
	@DefaultMessage("Dashboard")
	String mainMenuItemDashboardMaintenance();

	// *********
	// Chat Room
	// *********

	@DefaultMessage("Chat Room")
	String mainMenuItemChatRoom();

	// *********
	// Tasks
	// *********

	@DefaultMessage("Tasks")
	String mainMenuItemTasks();

	// ***********
	// Guest Rooms
	// ***********

	@DefaultMessage("Guest Rooms")
	String mainMenuItemGuestRooms();

	// ************
	// Public Areas
	// ************
	@DefaultMessage("Public Areas")
	String mainMenuGroupPublicAreas();

	// ************
	// Assignment
	// ************
	@DefaultMessage("Cleaning Assignment")
	String mainMenuGroupAssignment();

	@DefaultMessage("Room Cleaning Assignment")
	String mainMenuItemRoomAssignment();

	@DefaultMessage("Area Cleaning Assignment")
	String mainMenuItemAreaAssignment();

	// ************
	// Minibar
	// ************
	@DefaultMessage("Minibar")
	String mainMenuGroupMinibar();

	@DefaultMessage("Minibar Consumption")
	String mainMenuItemConsumption();

	// ************
	// Beállítások
	// ************
	@DefaultMessage("Beállítások")
	String mainMenuGroupConfig();

	@DefaultMessage("Housekeeping")
	String mainMenuItemHousekeepingConfig();

	// ************
	// Maintenance
	// ************

	@DefaultMessage("Maintenance")
	String mainMenuGroupMaintenance();

	
	/*
	 * DASHBOARD
	 */
	@DefaultMessage("Maintenance")
	String dashboardSubtitle();

	@DefaultMessage("Maintenance")
	String dashboardMaintenanceSubtitle();

	
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

}
