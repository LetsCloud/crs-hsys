package io.crs.hsys.client.kip;

public class KipNameTokens {

	public static final String HOME = "/home";
	public static final String CHAT_ROOM = "/chatRoom";
	public static final String TASK_MANAGER = "/taskManager";
	public static final String GUEST_ROOMS = "/guestRooms";
	public static final String ROOM_CONTROL = "/roomControl";
	public static final String OOO_ROOMS = "/oooRooms";
	public static final String PUBLIC_AREAS = "/publicAreas";

	public static final String ROOM_TASK_ASSIGNMENT = "/roomTaskAssignment";
	public static final String AREA_TASK_ASSIGNMENT = "/areaTaskAssignment";
	public static final String MAINTENANCE_TASK_ASSIGNMENT = "/maintenanceTaskAssignment";

	public static final String MINIBAR_CONSUMPTION = "/minibarConsumption";

	public static final String USER_CONFIG = "/userConfig";
	public static final String HOTEL_CONFIG = "/hotelConfig";
	public static final String HOUSEKEEPING_CONFIG = "/housekeepingConfig";
	public static final String HK_TASK_TYPE_EDITOR = "/hkTaskTypeEditor";
	public static final String MAINTENANCE_CONFIG = "/maintenanceConfig";

	// Housekeeping
	public static String getHkChangeStatus() {
		return GUEST_ROOMS;
	}

	public static String getHkAtendants() {
		return ROOM_TASK_ASSIGNMENT;
	}

	public static String getHkAssignment() {
		return AREA_TASK_ASSIGNMENT;
	}

	public static String getMinibarConsumption() {
		return MINIBAR_CONSUMPTION;
	}
}
