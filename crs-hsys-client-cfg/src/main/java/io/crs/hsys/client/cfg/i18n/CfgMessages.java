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

	@DefaultMessage("Document properties")
	String menuItemDocConfig();

	
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
	
	

	
	/*
	 * PROFILE GROUP EDITOR
	 */

	@DefaultMessage("Create Profile Group")
	String profileGroupCreateTitle();

	@DefaultMessage("Edit Profile Group")
	String profileGroupEditTitle();

	@DefaultMessage("Code")
	String profileGroupCode();

	@DefaultMessage("Description")
	String profileGroupDescription();

	@DefaultMessage("Profile Type")
	String profileGroupType();

	@DefaultMessage("Active")
	String profileGroupActive();
	
}
