package io.crs.hsys.client.kip;

public class KipNameTokens {

	// Sales
	public static final String RESERVATION = "/reservation";
	public static final String ROOM_PLAN = "/roomPlan";
	public static final String ROOM_AVAILABILITY = "/roomAvailability";

	// Housekeeping
	public static final String CHAT_ROOM = "/chatRoom";
	public static final String HK_CHANGE_STATUS = "/hkChangeStatus";
	public static final String HK_ATENDANTS = "/hkAtendants";
	public static final String HK_ASSIGNMENTS = "/hkAssignments";
	public static final String MINIBAR_CONSUMPTION = "/minibarConsumption";

	public static final String TASK_MNGR = "/taskMngr";

	// Profiles
	public static final String CONTACTS = "!contacts";
	public static final String ORGANIZATIONS = "!organizations";
	public static final String PROPERTIES = "!properties";

	public static String getReservation() {
		return RESERVATION;
	}

	public static String getRoomPlan() {
		return ROOM_PLAN;
	}

	public static String getRoomAvailability() {
		return ROOM_AVAILABILITY;
	}

	// Housekeeping
	public static String getHkChangeStatus() {
		return HK_CHANGE_STATUS;
	}

	public static String getHkAtendants() {
		return HK_ATENDANTS;
	}

	public static String getHkAssignment() {
		return HK_ASSIGNMENTS;
	}

	public static String getMinibarConsumption() {
		return MINIBAR_CONSUMPTION;
	}

	// Profiles
	public static String getContacts() {
		return CONTACTS;
	}

	public static String getOrganizations() {
		return ORGANIZATIONS;
	}

	public static String getProperties() {
		return PROPERTIES;
	}
}
