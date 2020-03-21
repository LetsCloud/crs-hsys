package io.crs.hsys.client.inf;

public class InfNameTokens {
	public static final String DASHBOARD = "/dashboard";
	public static final String ANALYTICS = "/analytics";
	public static final String PERF1 = "/perf1";
	
	// Sales
	public static final String RESERVATION = "/reservation";
	public static final String ROOM_PLAN = "/roomPlan";
	public static final String ROOM_AVAILABILITY = "/roomAvailability";
	public static final String ABOUT = "!about";

	// Dashboard
	public static final String DB_PERFORMANCE = "/dbPerformance";
	public static final String DB_REPORTS = "/dbReports";
	public static final String DB_DATA = "/dbData";

	// Housekeeping
	public static final String HK_CHANGE_STATUS = "/hkChangeStatus";
	public static final String HK_ATENDANTS = "/hkAtendants";
	public static final String HK_ASSIGNMENTS = "/hkAssignments";
	public static final String MINIBAR_CONSUMPTION = "/minibarConsumption";

	// Profiles
	public static final String CONTACTS = "!contacts";
	public static final String ORGANIZATIONS = "!organizations";
	public static final String PROPERTIES = "!properties";

	// Configuration
	public static final String USERS = "!users";
	public static final String USER_CONFIG = "/userconfig";
	public static final String ROLE_CONFIG = "/roleconfig";
	public static final String HOTEL_CONFIG = "/hotelconfig";
	public static final String SYSTEM_CONFIG = "!systemconfig";

	public static final String HOME = "home";

	public static final String CHARTS = "/charts";
	
	public static String getReservation() {
		return RESERVATION;
	}
	
	public static String getRoomPlan() {
		return ROOM_PLAN;
	}
	
	public static String getRoomAvailability() {
		return ROOM_AVAILABILITY;
	}
	
	
	public static String getAbout() {
		return ABOUT;
	}

	public static String getDashboard() {
		return DASHBOARD;
	}

	public static String getAnalytics() {
		return ANALYTICS;
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

	// Configuration
	public static String getHotelConfig() {
		return HOTEL_CONFIG;
	}

	public static String getUserConfig() {
		return USER_CONFIG;
	}

	public static String getRoleConfig() {
		return ROLE_CONFIG;
	}

	public static String getSystemConfig() {
		return SYSTEM_CONFIG;
	}
	
	public static String getHome() {
		return HOME;
	}

	public static String getCharts() {
		return CHARTS;
	}
}
