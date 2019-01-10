/**
 * 
 */
package io.crs.hsys.client.core.app;

import java.util.logging.Logger;

import gwt.material.design.client.pwa.serviceworker.ServiceEvent;
import gwt.material.design.client.pwa.serviceworker.ServiceWorkerManager;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;

import io.crs.hsys.client.core.firebase.messaging.MessagingManager;

/**
 * @author CR
 *
 */
public class AppServiceWorkerManager extends ServiceWorkerManager {
	private static Logger logger = Logger.getLogger(AppServiceWorkerManager.class.getName());

	private final MessagingManager fcmManager;
	private Boolean registered;

	public AppServiceWorkerManager(String resource, MessagingManager fcmManager) {
		super(resource);
		logger.info("AppServiceWorkerManager()");
		this.fcmManager = fcmManager;
		this.registered = false;
		// Polling Interval should be every 1 minute
		/* setPollingInterval(1000); */
	}

	@Override
	public boolean onRegistered(ServiceEvent event, ServiceWorkerRegistration registration) {
//		logger.info("onRegistered()");
		boolean result = super.onRegistered(event, registration);

		if (result) {
//			logger.info("Sikeres volt a ServiceWorker regisztrácója, ezért átadjuk a MessagingManager-nek.");
			fcmManager.useServiceWorker(registration);
			registered = true;
		}

		return result;
	}

	/*
	 * FCM
	 */

	public Boolean isRegistered() {
//		logger.info("isRegistered()=" + registered);
		return registered;
	}

}
