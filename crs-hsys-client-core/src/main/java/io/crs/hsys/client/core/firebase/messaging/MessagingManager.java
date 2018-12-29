/**
 * 
 */
package io.crs.hsys.client.core.firebase.messaging;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;

import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;
import io.crs.hsys.client.core.firebase.Firebase;
import io.crs.hsys.client.core.firebase.messaging.js.Fnx;
import io.crs.hsys.client.core.firebase.messaging.js.Messaging;
import io.crs.hsys.client.core.promise.xgwt.Fn;

/**
 * @author robi
 *
 */
public class MessagingManager implements HasMessagingFeatures {
	private static Logger logger = Logger.getLogger(MessagingManager.class.getName());

	private Firebase firebase;
	private Boolean registered;
	
	public Firebase getFirebase() {
		return firebase;
	}

	Fnx.NoArg unsubscribe;

	public MessagingManager() {
		logger.info("MessagingManager()");
		registered = false;
	}

	public MessagingManager(Firebase firebase) {
		this();
		logger.info("MessagingManager(firebase)");
		setFirebase(firebase);
	}

	public void setFirebase(Firebase firebase) {
//		logger.info("setFirebase()");
		this.firebase = firebase;
		registered = true;
	}

	public Boolean isRegistered() {
		logger.info("isRegistered()=" + registered);
		return registered;
	}

	public void setRegistered(Boolean registered) {
		this.registered = registered;
	}

	@Override
	public Messaging getMessaging() {
		if (firebase != null) {
			return firebase.messaging();
		} else {
			GWT.log("Firebase is not yet registered", new IllegalStateException());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getToken(Fn.Arg<String> callback) {
		getMessaging().getToken().then(object -> {
			String token = (String) object;
			logger.info("getToken()->token=" + token);
			callback.call(token);
		}).katch(error -> {
			logger.info("getToken().katch()->" + error.toString());
		});
	}

	@Override
	public void useServiceWorker(ServiceWorkerRegistration serviceWorker) {
//		logger.info("useServiceWorker()");
		firebase.messaging().useServiceWorker(serviceWorker);
	}

	@Override
	public void requestPermission(Fn.NoArg callback) {
		logger.info("requestPermission()");
		getMessaging().requestPermission().then(() -> {
			callback.call();
		});
	}

	@Override
	public void onTokenRefresh(Fn.Arg<String> callback) {
		logger.info("onTokenRefresh()");
		getMessaging().onTokenRefresh(() -> {
			getToken(callback);
		});
	}

	@Override
	public void onMessage(Fnx.Arg callback) {
		logger.info("onMessage()");
		unsubscribe = getMessaging().onMessage(callback);
	}
}
