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

	@DefaultMessage("Chat Room")
	String mainMenuItemChatRoom();

	@DefaultMessage("Atendants")
	String mainMenuItemAtendants();

	@DefaultMessage("Assignment")
	String mainMenuItemAssignment();

	@DefaultMessage("Sales")
	String mainMenuGroupSales();

	@DefaultMessage("Reservation")
	String mainMenuItemReservation();

	@DefaultMessage("Roomplan")
	String mainMenuItemRoomplan();

	@DefaultMessage("Roomp Availability")
	String mainMenuItemRoomAvailability();

	@DefaultMessage("Housekeeping")
	String mainMenuGroupHousekeeping();

	@DefaultMessage("Room status")
	String mainMenuItemChangeStatus();

	@DefaultMessage("Minibar consumption")
	String mainMenuItemMinibarConsumption();

	@DefaultMessage("Profiles")
	String mainMenuGroupProfiles();

	@DefaultMessage("Contacts")
	String mainMenuItemContacts();

	@DefaultMessage("Configuration")
	String mainMenuGroupConfiguration();

	@DefaultMessage("Hotels")
	String mainMenuItemHotels();

	@DefaultMessage("Users & Roles")
	String mainMenuItemUsers();
	

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
