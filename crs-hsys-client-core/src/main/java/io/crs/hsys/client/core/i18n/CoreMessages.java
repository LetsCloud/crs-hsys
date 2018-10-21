/**
 * 
 */
package io.crs.hsys.client.core.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * @author CR
 *
 */
public interface CoreMessages extends Messages {
	/*
	 * Common
	 */
	@DefaultMessage("Yes")
	String comYes();

	@DefaultMessage("No")
	String comNo();

	@DefaultMessage("OK")
	String comOk();

	@DefaultMessage("Save")
	String comSave();

	@DefaultMessage("Cancel")
	String comCancel();

	@DefaultMessage("Active")
	String comActive();

	@DefaultMessage("Inactive")
	String comInactive();

	@DefaultMessage("Required field")
	String comRequiredField();

	
	/*
	 * Server data exceptions
	 */
	@DefaultMessage("Login")
	String UNIQUE_INDEX_CONFLICT();

	
	/*
	 * LOGIN
	 */
	@DefaultMessage("Login")
	String loginCaption();

	@DefaultMessage("Account")
	String loginAccount();

	@DefaultMessage("Username")
	String loginUsername();

	@DefaultMessage("password")
	String loginPassword();

	@DefaultMessage("LOG IN")
	String loginSubmit();

	@DefaultMessage("Keep me logged in")
	String loginKeepMe();

	@DefaultMessage("Create an Account")
	String loginCreate();

	@DefaultMessage("Insufficient authentication!")
	String loginInsufficientAuthentication();

	@DefaultMessage("Username not found!")
	String loginUsernameNotFound();

	@DefaultMessage("Bad credentials!")
	String loginErrorBadCredentials();

	@DefaultMessage("The user is disabled!")
	String loginErrorDisabledUser();

	@DefaultMessage("Umknwon login problem!")
	String loginErrorUnknownProblem();

	
	/*
	 * REGISTER
	 */
	@DefaultMessage("Register")
	String regCaption();

	@DefaultMessage("Account name")
	String regAccountName();

	@DefaultMessage("Street and number")
	String regStreet();

	@DefaultMessage("City")
	String regCity();

	@DefaultMessage("Postcode")
	String regPostcode();

	@DefaultMessage("Country")
	String regCountry();

	@DefaultMessage("Username")
	String regUsername();

	@DefaultMessage("Your email address")
	String regEmail();

	@DefaultMessage("Repeat email")
	String regEmail2();

	@DefaultMessage("Password")
	String regPassword();

	@DefaultMessage("Repeat password")
	String regPassword2();

	@DefaultMessage("Submit")
	String regSubmit();

	
	/*
	 * SUCCESS
	 */
	@DefaultMessage("Registration Success - Congratulations!")
	String sucCaption();

	@DefaultMessage("You have successfully complited your registration.")
	String sucLine1();

	@DefaultMessage("Your account id: {0}")
	String sucLine2(String id);

	@DefaultMessage("Please check your email as we have sent you all your "
			+ "registration details and activate your account!")
	String sucLine3();


	/*
	 * ACTIVATE
	 */
	@DefaultMessage("Account activation")
	String actCaption();

	@DefaultMessage("Your account is now active!")
	String actLine1();

	@DefaultMessage("Clicking on the link below you can login and start your work:")
	String actLine2();

	
	/*
	 * SIDE PROFILE
	 */
	@DefaultMessage("Account setup")
	String accountMenuItemSetup();

	@DefaultMessage("System setup")
	String accountMenuItemSystem();

	@DefaultMessage("User profile")
	String userMenuItemProfile();

	@DefaultMessage("Switch user")
	String userMenuItemSwitch();

	@DefaultMessage("Log Out")
	String userMenuItemLogout();

	
	/*
	 * SIDE NAV
	 */
	@DefaultMessage("Configuration")
	String mainMenuGroupConfig();

	@DefaultMessage("Hotels")
	String mainMenuItemHotels();

	@DefaultMessage("Users")
	String mainMenuItemUsers();

	@DefaultMessage("Roles")
	String mainMenuItemRoles();

	
	/*
	 * BREADCRUMBS
	 */
	
	@DefaultMessage("Home")
	String breadcrumbsHome();
	
	@DefaultMessage("Users")
	String breadcrumbsUsersTable();
	
	@DefaultMessage("Editor")
	String breadcrumbsUserEditor();

	
	/*
	 * DASHBOARD
	 */
	@DefaultMessage("Dashboard")
	String pageDashboardTitle();

	
	/*
	 * SYSTEM CONFIG
	 */

	@DefaultMessage("System configuration")
	String systemConfigTitle();

	@DefaultMessage("Basic settings that determine the operation of the system")
	String systemConfigDescription();

	
	/*
	 * USER GROUP BROWSER
	 */

	@DefaultMessage("User Groups")
	String userGroupBrowserTitle();

	@DefaultMessage("Name")
	String userGroupBrowserName();

	
	/*
	 * USER_GROUP EDITOR
	 */

	@DefaultMessage("Create User Group")
	String userGroupEditorCreateTitle();

	@DefaultMessage("Modify User Group")
	String userGroupEditorModifyTitle();

	@DefaultMessage("Name")
	String userGroupEditorName();
	
	
	/*
	 * USER BROWSER
	 */

	@DefaultMessage("Users")
	String userBrowserTitle();

	@DefaultMessage("Username")
	String usersBrowserUsername();

	@DefaultMessage("Email")
	String usersTableEmail();

	
	/*
	 * USER EDITOR
	 */

	@DefaultMessage("Create User Profile")
	String userEditorCreateTitle();

	@DefaultMessage("Modify User Profile")
	String userEditorModifyTitle();

	@DefaultMessage("Name")
	String userEditorName();

	@DefaultMessage("Title")
	String userEditorTitle();

	@DefaultMessage("Email")
	String userEditorEmail();

	@DefaultMessage("Code")
	String userEditorCode();

	@DefaultMessage("Username")
	String userEditorUsername();

	@DefaultMessage("Enabled")
	String userEditorEnabled();
	
	@DefaultMessage("Admin user")
	String userEditorIsAdmin();
	
	@DefaultMessage("Permitted hotels")
	String userEditorAvailableHotels();
	
	@DefaultMessage("Chhoose a hotel")
	String userEditorChooseHotel();
	
	@DefaultMessage("Deafult hotel")
	String userEditorDefaultHotel();
	
	@DefaultMessage("Chat groups")
	String userEditorChatGroups();
	
	@DefaultMessage("Choose a group")
	String userEditorChooseGroup();
	
	@DefaultMessage("Password")
	String userEditorPassword();

	
	/*
	 * PROFILE CONFIG
	 */

	@DefaultMessage("Profile configuration")
	String profileConfigTitle();

	@DefaultMessage("Manage your organization's profile or contact information")
	String profileConfigDescription();

	
	/*
	 * RELATIONSHIP BROWSER
	 */
	@DefaultMessage("Relationships")
	String relationshipBrowserTitle();

	@DefaultMessage("Forward")
	String relationshipBrowserColForward();

	@DefaultMessage("Reverse")
	String relationshipBrowserColReverse();

	@DefaultMessage("Active")
	String relationshipBrowserColActive();

	
	/*
	 * RELATIONSHIPP EDITOR
	 */

	@DefaultMessage("Create Relationship Group")
	String relationshipCreateTitle();

	@DefaultMessage("Edit Relationship Group")
	String relationshipEditTitle();

	@DefaultMessage("Forward")
	String relationshipFldForward();

	@DefaultMessage("Reverse")
	String relationshipFldReverse();

	
	/*
	 * PROFILE GROUP BROWSER
	 */

	@DefaultMessage("Profile Groups")
	String profileGroupBrowserTitle();

	@DefaultMessage("Code")
	String profileGroupBrowserCode();

	@DefaultMessage("Description")
	String profileGroupBrowserDescription();

	@DefaultMessage("Active")
	String profileGroupBrowserActive();

	
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

	
	/*
	 * ORGANIZATION CONFIG
	 */
	@DefaultMessage("Organizations")
	String organizationConfigTitle();
	
	@DefaultMessage("Organizations ...")
	String organizationConfigDescription();
	
	
	/*
	 * ORGANIZATION BROWSER
	 */

	@DefaultMessage("Organizations")
	String organizationBrowserTitle();

	@DefaultMessage("Code")
	String organizationBrowserColCode();

	@DefaultMessage("Name")
	String organizationBrowserColName();

	
	/*
	 * PROFILE FILTER
	 */
	
	@DefaultMessage("Code filter")
	String profileFilterCodeLabel();
	
	@DefaultMessage("Code~")
	String profileFilterCode();
	
	@DefaultMessage("Name filter")
	String profileFilterNameLabel();
	
	@DefaultMessage("Name~")
	String profileFilterName();
	
	@DefaultMessage("ProfileGroup filter")
	String profileFilterProfileGroupLabel();
	
	@DefaultMessage("Group~")
	String profileFilterProfileGroup();

	
	/*
	 * ORGANIZATION CREATOR
	 */
	@DefaultMessage("Create Organization")
	String organizationCreatorTitle();

	@DefaultMessage("Common data...")
	String organizationCreatorDescription();

	
	/*
	 * ORGANIZATION EDITOR
	 */
	@DefaultMessage("Edit Organization Profile")
	String organizationEditorTitle();

	@DefaultMessage("General Data")
	String organizationEditorDescription();

	@DefaultMessage("Organization name")
	String organizationEditorName();

	@DefaultMessage("Code")
	String organizationEditorCode();

	@DefaultMessage("Profile Group")
	String organizationEditorProfileGroup();

	@DefaultMessage("Communication")
	String organizationEditorCommunication();
	
	@DefaultMessage("Addresses")
	String organizationEditorAdresses();
	
	@DefaultMessage("Web Presence")
	String organizationEditorWebPresence();

	
	/*
	 * CONTACT BROWSER
	 */
	@DefaultMessage("Contacts")
	String contactBrowserTitle();

	@DefaultMessage("Name")
	String contactBrowserColName();

	
	/*
	 * CONTACT CREATOR
	 */
	@DefaultMessage("Create Organization")
	String contactCreatorTitle();

	@DefaultMessage("Common data...")
	String contactCreatorDescription();

	
	/*
	 * CONTACT DISPLAY
	 */
	@DefaultMessage("Contact Profile View")
	String contactDisplayTitle();

	@DefaultMessage("Contact information ...")
	String contactDisplayDescription();

	
	/*
	 * CONTACT EDITOR
	 */
	@DefaultMessage("Edit Contact Profile")
	String contactEditorTitle();

	@DefaultMessage("General Data")
	String contactEditorDescription();

	@DefaultMessage("Contact name")
	String contactEditorName();

	@DefaultMessage("Profile Group")
	String contactEditorProfileGroup();

	@DefaultMessage("Links")
	String contactEditorLink();
	
	@DefaultMessage("Communication")
	String contactEditorCommunication();
	
	@DefaultMessage("Addresses")
	String contactEditorAdresses();
	
	@DefaultMessage("Web Presence")
	String contactEditorWebPresence();

	@DefaultMessage("Profile operations")
	String contactDisplayFAB();

	@DefaultMessage("Delete Profile")
	String contactDisplayDelete();

	
	/*
	 * HOTEL CONFIG
	 */
	@DefaultMessage("Hotel Configuration")
	String hotelConfigTitle();

	@DefaultMessage("Hotel Configuration...")
	String hotelConfigDescription();

	
	/*
	 * COMMUNICATION EDITOR
	 */
	@DefaultMessage("Communication Mode")
	String communicationEditorLabel();

	@DefaultMessage("Primary")
	String communicationEditorPrimary();
	
	/*
	 * ADDRESS EDITOR
	 */

	@DefaultMessage("Address Type")
	String addressEditorLabel();

	@DefaultMessage("Choose an Address Type")
	String addressEditorLabelPlaceholder();

	@DefaultMessage("Primary")
	String addressEditorPrimary();
	
	@DefaultMessage("Street and number")
	String addressEditorStreet();
	
	@DefaultMessage("Country")
	String addressEditorCountry();
	
	@DefaultMessage("Choose a Coountry")
	String addressEditorCountryPlaceholder();
	
	@DefaultMessage("Postal code")
	String addressEditorPostcode();
	
	@DefaultMessage("Choose an Postal code")
	String addressEditorPostcodePlaceholder();
	
	@DefaultMessage("City")
	String addressEditorCity();
	
	
	/*
	 * HOTEL BROWSER
	 */
	
	@DefaultMessage("Hotels Data")
	String hotelBrowserTitle();
	
	@DefaultMessage("Code")
	String hotelsTableCode();

	@DefaultMessage("Name")
	String hotelsTableName();

	
	/*
	 * HOTEL EDITOR
	 */

	@DefaultMessage("Create a Hotel")
	String hotelEditorCreateTitle();

	@DefaultMessage("Modify Hotel Data")
	String hotelEditorModifyTitle();

	@DefaultMessage("Code")
	String hotelEditorCode();

	@DefaultMessage("Name")
	String hotelEditorName();

	
	/*
	 * ROOMTYPE BROWSER
	 */
	
	@DefaultMessage("Room Types")
	String roomTypeBrowserTitle();
	
	@DefaultMessage("Choose a hotel")
	String roomTypesTableHotelsPlaceholder();
	
	@DefaultMessage("Selected hotel")
	String roomTypesTableHotelsLabel();
	
	@DefaultMessage("Only active types")
	String roomTypesTableOnlyActive();
	
	@DefaultMessage("Code")
	String roomTypesTableCode();

	@DefaultMessage("Name")
	String roomTypesTableName();

	@DefaultMessage("InventoryType")
	String roomTypesTableInventoryType();

	@DefaultMessage("Number Of Rooms")
	String roomTypesTableNumberOfRooms();

	@DefaultMessage("Active")
	String roomTypesTableActive();

	@DefaultMessage("Inactive")
	String roomTypesTableInactive();

	
	/*
	 * ROOMTYPE FILTER
	 */
	
	@DefaultMessage("Choose inventory type")
	String roomTypeFilterInventoryTypePlaceholder();
	
	@DefaultMessage("Selected inventory type")
	String roomTypeFilterInventoryTypeLabel();

	
	/*
	 * ROOMTYPE EDITOR
	 */

	@DefaultMessage("Create room type")
	String roomTypeEditorCreateTitle();

	@DefaultMessage("Modify room type")
	String roomTypeEditorModifyTitle();

	@DefaultMessage("Active")
	String roomTypeEditorActive();

	@DefaultMessage("Code")
	String roomTypeEditorCode();

	@DefaultMessage("Name")
	String roomTypeEditorName();

	@DefaultMessage("Description")
	String roomTypeEditorDescription();

	@DefaultMessage("Channel manager/Booking engine")
	String roomTypeEditorChmGroup();

	@DefaultMessage("Inventory Type")
	String roomTypeEditorInventoryType();

	@DefaultMessage("Choose inventory type")
	String roomTypeEditorPlaceholderInventoryType();

	@DefaultMessage("Housekeeping")
	String roomTypeEditorHkGroup();

	@DefaultMessage("Number of beds")
	String roomTypeEditorNumOfBeds();

	@DefaultMessage("Number of extra beds")
	String roomTypeEditorNumOfXtrBeds();

	@DefaultMessage("Cleaning factor")
	String roomTypeEditorCleaningFactor();
	
	/*
	 * ROOMS TABLE
	 */
	
	@DefaultMessage("Rooms")
	String roomBrowserTitle();
	
	@DefaultMessage("Code")
	String roomsTableCode();
	
	@DefaultMessage("Floor")
	String roomsTableFloor();

	@DefaultMessage("Type")
	String roomsTableType();

	
	/*
	 * ROOM FILTER
	 */
	
	@DefaultMessage("Selected floor")
	String roomFilterFloorLabel();
	
	@DefaultMessage("Choose floor")
	String roomFilterFloorPlaceholder();
	
	@DefaultMessage("Floor=")
	String roomFilterFloor();
	
	@DefaultMessage("Selected Room Types")
	String roomFilterRoomTypesLabel();
	
	@DefaultMessage("Choose Room Type")
	String roomFilterRoomTypesPlaceholder();

	
	/*
	 * ROOM EDITOR
	 */

	@DefaultMessage("Create room")
	String roomEditorCreateTitle();

	@DefaultMessage("Modify room")
	String roomEditorModifyTitle();

	@DefaultMessage("Code")
	String roomEditorCode();

	@DefaultMessage("Floor")
	String roomTypeEditorFloor();
	
	@DefaultMessage("Choose Room Type")
	String roomEditorChooseRoomtype();

	@DefaultMessage("Room Type")
	String roomEditorRoomtype();
	
	@DefaultMessage("Description")
	String roomEditorDescription();
	
	@DefaultMessage("Availability")
	String roomEditorAvailability();
	
	@DefaultMessage("From Date")
	String roomEditorAvailabilityFromDate();
	
	@DefaultMessage("Add")
	String roomEditorAvailabilityAdd();
	
	@DefaultMessage("Remove")
	String roomEditorAvailabilityRemove();

	
	/*
	 * MARKET GROUP BROWSER
	 */

	@DefaultMessage("Market Groups")
	String marketGroupBrowserTitle();
	
	
	/*
	 * SEND MESSAGE
	 */

	@DefaultMessage("Type a message")
	String sendMessagePlaceHolder();

	
	/*
	 * CREATE CHAT
	 */

	@DefaultMessage("Create a Chat")
	String createChatHeader();

	@DefaultMessage("Feel free to communicate")
	String createChatSubHeader();

	@DefaultMessage("Invited groups and users")
	String createChatInvited();
	
	@DefaultMessage("Choose group or user")
	String createChatToInvite();
	
	@DefaultMessage("Groups")
	String createChatGroupGroup();

	@DefaultMessage("Users")
	String createChatUserGroup();

}
