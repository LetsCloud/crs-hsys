package io.crs.hsys.shared.api;

public class ApiPaths {
	public static final String PATH_WEBSAFEKEY = "/{" + ApiParameters.WEBSAFEKEY + "}";
	public static final String PATH_HOTEL_KEY = "/{" + ApiParameters.HOTEL_KEY + "}";
	public static final String REDUCED = "/reduced";
	public static final String UPLOAD = "/upload";

	public class AdminV1 {
		public static final String ADMIN = "/admin";
		public static final String LOGIN = "/login";
		public static final String CURRENTUSER = "/currentuser";
		public static final String IS_LOGGED_IN = "/isLoggedIn";
		public static final String LOGOUT = "/logout";
		public static final String LOGGEDOUT = "/loggedout";
		public static final String GLOBAL_CONFIG = "/globalConfig";
	}

	public class SpaV1 {
		public static final String ROOT = "/spa/v1";
		public static final String FILTER = "/filter";

		public static final String LOGIN = "/login";
		public static final String CURRENTUSER = "/currentuser";
		public static final String IS_LOGGED_IN = "/isLoggedIn";
		public static final String LOGOUT = "/logout";

		public static final String FCM = "/fcm";
		public static final String MESSAGE = "/message";

		public static final String ACCOUNT = "/account";

		public static final String USER_GROUP = "/userGroup";
		public static final String USER = "/user";
		public static final String INVITE = "/invite";
		public static final String SUBSCRIBE = "/subscribe";
		public static final String ROLE = "/role";

		public static final String CHAT = "/chat";
		public static final String ADD_POST = "/addPost";

		public static final String TASK = "/task";
		public static final String TASK_STATUS_CHANGE = "taskStatusChange";
		public static final String TASK_TYPE = "/taskType";
		public static final String TASK_GROUP = "/taskGroup";
		public static final String TASK_TODO = "/taskTodo";

		public static final String PROFILE_GROUP = "/profileGroup";
		public static final String RELATIONSHIP = "/relationship";
		public static final String PROFILE = "/profile";
		public static final String ORGANIZATION = "/organization";
		public static final String CONTACT = "/contact";

		public static final String CONTRACT_TYPE = "/contractType";
		public static final String PRODUCT_TYPE = "/productType";
		public static final String CONTRACT = "/contract";

		public static final String QUOTATION_STATUS = "/quotationStatus";
		public static final String QUOTATION = "/quotation";

		public static final String HOTEL = "/hotel";
		public static final String ROOMTYPE = "/roomtype";
		public static final String ROOM = "/room";
		public static final String OOO_ROOM = "/oooRoom";
		public static final String OOO_ROOMS_CREATE = "/oooRoomsCreate";
		public static final String ROOM_STATUS = "/roomStatus";
		public static final String AVAILABLE_ON_DATE = "/availableOnDate";
		public static final String ROOM_STATUS_CHANGE = "/roomStatusChange";
		public static final String MARKET_GROUP = "/marketGroup";
		public static final String RATE_CODE = "/rateCode";
		public static final String ROOM_RATE = "/roomRate";

		public static final String DATACUBE = "/dataCube";
		public static final String PERF1_QUERY = "/perf1Query";
		public static final String D3M6_QUERY = "/d3m6Query";
		public static final String M1_QUERY = "/m1Query";
		public static final String FORECAST = "/forecast";
		public static final String ACTUAL = "/actual";
	}

	public class ApiV1 {
		public static final String API = "/api/v1";
		public static final String HOTEL = "/hotel";
		public static final String ROOMTYPE = "/roomtype";
		public static final String ROOM = "/room";
		public static final String FORECAST_DATACUBE = "/forecastDataCube";
		public static final String DATACUBE_ACTUAL = "/dataCubeActual";

		public static final String DATACUBE = "/dataCube";
		public static final String M1_QUERY = "/m1Query";
		public static final String PICKUP = "/pickup";
	}

	public class User {
		public static final String ROOT = "/user";
		public static final String LOGIN = "/login";
		public static final String REGISTER = "/register";
	}
}
