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
	Fnx.NoArg unsubscribe;

	public MessagingManager() {
	}

	public MessagingManager(Firebase firebase) {
		logger.info("MessagingManager()");
		this.firebase = firebase;
	}
	
	public void setFirebase(Firebase firebase) {
		this.firebase = firebase;
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
			callback.call(token);
		}).katch(error -> {
			logger.info("getMessaging().getToken().katch()" + error.toString());
		});
	}

	@Override
	public void useServiceWorker(ServiceWorkerRegistration r) {
		firebase.messaging().useServiceWorker(r);
	}

	@Override
	public void requestPermission(Fn.NoArg callback) {
		getMessaging().requestPermission().then(() -> {
			callback.call();
		});
	}

	@Override
	public void onTokenRefresh(Fn.Arg<String> callback) {
		getMessaging().onTokenRefresh(() -> {
			getToken(callback);
		});
	}

	@Override
	public void onMessage(Fnx.Arg callback) {
		unsubscribe = getMessaging().onMessage(callback);
	}
}
