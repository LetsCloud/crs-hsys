package io.crs.hsys.client.fro;

public class NameTokens {

	public static final String HOME = "/home";
	public static final String CALENDAR = "/calendar";

	// Reservation
	public static final String RESERVATION = "/reservation";
	public static final String CREATE_RESERVATION = "/createReservation";

	public static final String ROOM_PLAN = "/roomPlan";

	public static final String RATE_MANAGER = "/rataManager";
	public static final String RATE_UPDATER = "/rateUpdater";

	public static final String CHAT_ROOM = "/chatRoom";

	public static final String TASK_MNGR = "/taskMngr";
	
	public static final String GUEST_ROOMS2 = "/guestRooms2";
	
	public static final String HK_ATENDANTS = "/hkAtendants";
	public static final String RATE_CONFIGURATION = "/rateConfiguration";
	public static final String RATECODE_EDITOR = "/rateCodeEditor";
	public static final String MINIBAR_CONSUMPTION = "/minibarConsumption";


	// Profiles
	public static final String CONTACTS = "!contacts";
	public static final String ORGANIZATIONS = "!organizations";
	public static final String PROPERTIES = "!properties";

	public static String getHkAtendants() {
		return HK_ATENDANTS;
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
