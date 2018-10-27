package io.crs.hsys.shared.api;

public class ApiPaths {
    public static final String PATH_WEBSAFEKEY = "/{" + ApiParameters.WEBSAFEKEY + "}";
    public static final String PATH_HOTEL_KEY = "/{" + ApiParameters.HOTEL_KEY + "}";
    public static final String REDUCED = "/reduced";
	
	public class SpaV1 {
		public static final String ROOT = "/spa/v1";
		public static final String FILTER = "/filter";

		public static final String LOGIN = "/login";
		public static final String CURRENTUSER = "/currentuser";
		public static final String IS_LOGGED_IN = "/isLoggedIn";
		public static final String LOGOUT = "/logout";
		
		public static final String FCM = "/fcm";
		public static final String SUBSCRIBE = "/subscribe";
		public static final String MESSAGE = "/message";
		
		public static final String GLOBAL_CONFIG = "/globalConfig";
		public static final String ACCOUNT = "/account";
		
		public static final String USER = "/user";
		public static final String INVITE = "/invite";
		public static final String USER_GROUP = "/userGroup";
		public static final String ROLE = "/role";

		public static final String CHAT = "/chat";
		public static final String ADD_POST = "/addPost";

		public static final String TASK = "/task";

		public static final String PROFILE_GROUP = "/profileGroup";
		public static final String RELATIONSHIP = "/relationship";
		public static final String PROFILE = "/profile";
		public static final String ORGANIZATION = "/organization";
		public static final String CONTACT = "/contact";

		public static final String HOTEL = "/hotel";
		public static final String ROOMTYPE = "/roomtype";
		public static final String ROOM = "/room";
		public static final String AVAILABLE_ON_DATE = "/availableOnDate";
		public static final String STATUS_CHANGE = "/statusChange";
		public static final String MARKET_GROUP = "/marketGroup";

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
