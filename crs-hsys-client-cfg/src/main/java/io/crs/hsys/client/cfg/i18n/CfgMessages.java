/**
 * 
 */
package io.crs.hsys.client.cfg.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * @author CR
 *
 */
public interface CfgMessages extends Messages {

	/*
	 * MAIN MENU
	 */

	@DefaultMessage("Dashboard")
	String mainMenuItemDashboard();

	
	// Coommon	
	@DefaultMessage("Common")
	String mainMenuCommonConfig();
	
	@DefaultMessage("Users & permissions")
	String menuItemUserConfig();

	@DefaultMessage("Profiles")
	String menuItemProfileConfig();

	
	// Front Office
	@DefaultMessage("Front Office Config")
	String mainMenuFroConfig();

	@DefaultMessage("Hotel Config")
	String menuItemHotelConfig();
	

	// Housekeeping
	@DefaultMessage("Housekeeping Config")
	String mainMenuKipConfig();
	

	// Management Information
	@DefaultMessage("Management Information")
	String mainMenuInfConfig();
}