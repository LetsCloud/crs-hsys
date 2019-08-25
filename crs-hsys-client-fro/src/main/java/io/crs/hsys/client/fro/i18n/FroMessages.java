/**
 * 
 */
package io.crs.hsys.client.fro.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * @author CR
 *
 */
public interface FroMessages extends Messages {

	/*
	 * MAIN MENU
	 */

	@DefaultMessage("Dashboard")
	String mainMenuItemDashboard();

	@DefaultMessage("Reservation")
	String mainMenuItemReservation();

	
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
	// Configuration
	// ************
	@DefaultMessage("Configuration")
	String mainMenuGroupConfiguration();

	@DefaultMessage("Hotel Configuration")
	String mainMenuItemHotelConfiguration();

	@DefaultMessage("Rate Configuration")
	String mainMenuItemRateConfiguration();


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
	 * RATE CONFIGURATION
	 */

	@DefaultMessage("Rate Configuration")
	String rateConfigTitle();

	@DefaultMessage("Rate Grpoups, Rate Codes and Rate Manager")
	String rateConfigDescription();

	
	@DefaultMessage("Rate Codes")
	String rateCodeBrowserTitle();
	
	
}
