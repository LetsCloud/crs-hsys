package io.crs.hsys.client.fro;

public class NameTokens {

	public static final String HOME = "/home";

	// Reservation
	public static final String RESERVATION = "/reservation";
	public static final String CREATE_RESERVATION = "/createReservation";

	public static final String CHAT_ROOM = "/chatRoom";

	public static final String TASK_MNGR = "/taskMngr";
	
	public static final String GUEST_ROOMS2 = "/guestRooms2";
	
	public static final String HK_ATENDANTS = "/hkAtendants";
	public static final String HK_ASSIGNMENTS = "/hkAssignments";
	public static final String MINIBAR_CONSUMPTION = "/minibarConsumption";


	// Profiles
	public static final String CONTACTS = "!contacts";
	public static final String ORGANIZATIONS = "!organizations";
	public static final String PROPERTIES = "!properties";

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
