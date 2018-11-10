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
	// Maintenance
	// ************
	
	@DefaultMessage("Maintenance")
	String mainMenuGroupMaintenance();

	

	
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
	
	
}
