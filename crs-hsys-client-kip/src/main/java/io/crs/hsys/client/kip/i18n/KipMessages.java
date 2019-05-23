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
	 * TASKS
	 */

	@DefaultMessage("Tasks")
	String tasksTitle();

	@DefaultMessage("Go for it ...")
	String tasksSubTitle();

	@DefaultMessage("Start")
	String taskBrowserStartLink();

	@DefaultMessage("Pause")
	String taskBrowserPauseLink();

	@DefaultMessage("Finish")
	String taskBrowserCompleteLink();

	@DefaultMessage("Reassign")
	String taskBrowserReassignLink();

	@DefaultMessage("Modify")
	String taskBrowserModifyLink();

	@DefaultMessage("Delete")
	String taskBrowserDeleteLink();

	@DefaultMessage("Back")
	String taskBrowserBackLink();


	/*
	 * TASKS FILTER
	 */

	@DefaultMessage("Task Statuses")
	String tasksFilterTaskStatusTitle();

	@DefaultMessage("Not Started")
	String tasksFilterStatusNotStarted();

	@DefaultMessage("In Progress")
	String tasksFilterStatusInProgress();

	@DefaultMessage("Deffered")
	String tasksFilterStatusDeffered();

	@DefaultMessage("Completed")
	String tasksFilterStatusCompleted();

	@DefaultMessage("Deleted")
	String tasksFilterStatusDeleted();

	
	/*
	 * TASK TYPE EDITOR
	 */
	@DefaultMessage("Create a Task")
	String taskEditorCreateTitle();

	@DefaultMessage("Create Tasks to manage workflow")
	String taskEditorCreateSubTitle();

	@DefaultMessage("Modify Task")
	String taskEditorModifyTitle();

	@DefaultMessage("")
	String taskEditorModifySubTitle();

	@DefaultMessage("Housekeeping")
	String taskEditorKindHousekeeping();

	@DefaultMessage("Maintenance")
	String taskEditorKindMaintenance();

	@DefaultMessage("Task Type")
	String taskEditorType();

	@DefaultMessage("Choose a Task Type")
	String taskEditorTypePlaceholder();

	@DefaultMessage("Description")
	String taskEditorDescription();

	@DefaultMessage("Assignee")
	String taskEditorAssignee();

	@DefaultMessage("Choose an Assignee")
	String taskEditorAssigneePlaceholder();

	@DefaultMessage("Room#")
	String taskEditorRoom();

	@DefaultMessage("Choose a Room")
	String taskEditorRoomPlaceholder();
	
	@DefaultMessage("Due date")
	String taskEditorDueDateGroup();
	
	@DefaultMessage("Date")
	String taskEditorDueDate();
	
	@DefaultMessage("Time")
	String taskEditorDueTime();
	
	@DefaultMessage("Todos")
	String taskEditorTodos();
	
	@DefaultMessage("Timeline")
	String taskEditorHistory();

	
	/*
	 * ROOM STATUS
	 */

	@DefaultMessage("Room Statuses")
	String roomStatusTitle();

	@DefaultMessage("Supervisor's favorite ...")
	String roomStatusSubTitle();

	@DefaultMessage("Unassigned")
	String roomStatusUnassigned();

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

	
	/*
	 * OOO ROOM BROWSER
	 */

	@DefaultMessage("Out Of Order Rooms")
	String oooRoomBrowserTitle();

	@DefaultMessage("Rooms kept under out of order are not sellable")
	String oooRoomBrowserSubTitle();

	@DefaultMessage("Room")
	String oooRoomBrowserRoomCol();

	@DefaultMessage("From")
	String oooRoomBrowserFromDateCol();

	@DefaultMessage("To")
	String oooRoomBrowserToDateCol();

	@DefaultMessage("Return When")
	String oooRoomBrowserReturnWhenCol();

	@DefaultMessage("Return As")
	String oooRoomBrowserReturnAsCol();

	@DefaultMessage("Remarks")
	String oooRoomBrowserRemarksCol();

	
	/*
	 * OOO ROOM BROWSER
	 */

	@DefaultMessage("OOO szoba felvétel")
	String oooRoomEditorCreateTitle();

	@DefaultMessage("Az üzemen kívül helyezett szobák nem foglalhatók")
	String oooRoomEditorCreateSubTitle();

	@DefaultMessage("OOO szoba módosítás")
	String oooRoomEditorEditTitle();

	@DefaultMessage("Az üzemen kívül helyezett szobák nem foglalhatók")
	String oooRoomEditorEditSubTitle();

	@DefaultMessage("Szobaszám")
	String oooRoomEditorRoomLabel();

	@DefaultMessage("Szobaszám")
	String oooRoomEditorRoomPlaceholder();

	@DefaultMessage("Mettől")
	String oooRoomEditorFromDateLabel();

	@DefaultMessage("Meddig")
	String oooRoomEditorToDateLabel();

	@DefaultMessage("Visszaadás időszaka")
	String oooRoomEditorReturnWhenLabel();

	@DefaultMessage("Válassz időszakot")
	String oooRoomEditorReturnWhenPlaceholder();

	@DefaultMessage("Visszaadás státusza")
	String oooRoomEditorReturnAsLabel();

	@DefaultMessage("Válassz státuszt")
	String oooRoomEditorReturnAsPlaceholder();

	@DefaultMessage("Megjegyzés")
	String oooRoomEditorRemarksLabel();

	@DefaultMessage("Szobaszámok választása")
	String oooRoomCreatorRoomLabel();

	@DefaultMessage("Válassz szobaszámokat")
	String oooRoomCreatorRoomPlaceholder();

	@DefaultMessage("Szoba státuszok választása")
	String oooRoomCreatorRoomStatusLabel();

	@DefaultMessage("Válassz státuszokat")
	String oooRoomCreatorRoomStautsPlaceholder();
}
