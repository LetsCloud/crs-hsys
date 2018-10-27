/**
 * 
 */
package io.crs.hsys.client.admin.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * @author CR
 *
 */
public interface AdminMessages extends Messages {

	/*
	 * MAIN MENU
	 */

	@DefaultMessage("Dashboard")
	String mainMenuItemDashboard();

	
	// Coommon	
	@DefaultMessage("System Config")
	String mainSubMenuSystemConfig();
	
	@DefaultMessage("Global Config")
	String menuItemGlobalConfig();
	
}
