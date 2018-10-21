/**
 * 
 */
package io.crs.hsys.client.cfg.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * @author CR
 *
 */
public interface FroMessages extends Messages {

	/*
	 * MAIN MENU
	 */

	@DefaultMessage("Reservation")
	String mainMenuItemDashboard();

	
	@DefaultMessage("Front Desk")
	String mainMenuGroupReception();

	@DefaultMessage("Reservation")
	String mainMenuItemReservation();

	@DefaultMessage("Roomplan")
	String mainMenuItemRoomplan();

	
	@DefaultMessage("Cashier")
	String mainMenuGroupCashier();

	@DefaultMessage("Billing")
	String mainMenuItemBilling();

	
	@DefaultMessage("Configuration")
	String mainMenuGroupConfiguration();
}
