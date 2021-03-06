package io.crs.hsys.client.core.security;

import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.annotations.DefaultGatekeeper;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import io.crs.hsys.client.core.CoreNameTokens;

@DefaultGatekeeper
public class LoggedInGatekeeper implements Gatekeeper {
	private static Logger logger = Logger.getLogger(LoggedInGatekeeper.class.getName());

	public static final String PLACE_TO_GO = "placeToGo";

	private final PlaceManager placeManager;
	private final CurrentUser currentUser;

	@Inject
	LoggedInGatekeeper(PlaceManager placeManager, CurrentUser currentUser) {
		logger.info("LoggedInGatekeeper()");
		this.placeManager = placeManager;
		this.currentUser = currentUser;
	}

	@Override
	public boolean canReveal() {
//		if (!currentUser.isLoggedIn())
//			goToLogin();
//		return currentUser.isLoggedIn();
		return true;
	}
/*
	private void goToLogin() {
		StringBuilder sb = new StringBuilder();
		sb.append(placeManager.getCurrentPlaceRequest().getNameToken());
		
		Set<String> params = placeManager.getCurrentPlaceRequest().getParameterNames();
		for (String param : params) {
			sb.append("?");
			sb.append(param);
			sb.append("=");
			sb.append(placeManager.getCurrentPlaceRequest().getParameter(param, null));
		}
		
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.LOGIN)
				.with(PLACE_TO_GO, sb.toString()).build();
		placeManager.revealPlace(placeRequest);
	}
*/
}
