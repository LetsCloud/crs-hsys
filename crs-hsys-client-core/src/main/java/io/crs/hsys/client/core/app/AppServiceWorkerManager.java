/**
 * 
 */
package io.crs.hsys.client.core.app;

import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;

import gwt.material.design.client.pwa.serviceworker.ServiceEvent;
import gwt.material.design.client.pwa.serviceworker.ServiceWorkerManager;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;
import gwt.material.design.client.ui.MaterialToast;

import io.crs.hsys.client.core.firebase.messaging.MessagingManager;
import io.crs.hsys.client.core.firebase.messaging.js.Fnx;
import io.crs.hsys.client.core.promise.xgwt.Fn;
import io.crs.hsys.shared.api.FcmResource;

/**
 * @author CR
 *
 */
public class AppServiceWorkerManager extends ServiceWorkerManager {
	private static Logger logger = Logger.getLogger(AppServiceWorkerManager.class.getName());

	private final EventBus eventBus;
	private final MessagingManager fcmManager;
	private final RestDispatch dispatch;
	private final FcmResource fcmService;
	private String endpoint, auth, key;

	public AppServiceWorkerManager(String resource, EventBus eventBus, MessagingManager fcmManager,
			RestDispatch dispatch, FcmResource fcmService) {
		super(resource);
		logger.info("AppServiceWorkerManager()");
		this.eventBus = eventBus;
		this.fcmManager = fcmManager;
		this.dispatch = dispatch;
		this.fcmService = fcmService;
		// Polling Interval should be every 1 minute
		/* setPollingInterval(1000); */
	}

	@Override
	public boolean onRegistered(ServiceEvent event, ServiceWorkerRegistration registration) {
		logger.info("AppServiceWorkerManager().onRegistered()");
		boolean result = super.onRegistered(event, registration);

		if (result) {
			logger.info("AppServiceWorkerManager().onRegistered()->success");
			fcmManager.useServiceWorker(registration);
		}

		return result;
	}

	/*
	 * FCM
	 */

	/**
	 * 
	 * @param callback
	 */
	public void requestFcbPermission(Fn.NoArg callback) {
		logger.info("requestFcbPermission()");
		fcmManager.requestPermission(callback);
	}

	/**
	 * 
	 * @param callback
	 */
	public void getFcbToken(Fn.Arg<String> callback) {
		logger.info("getFcbToken()");
		fcmManager.getToken(callback);
	}

	/**
	 * 
	 * @param callback
	 */
	public void onFcmTokenRefresh(Fn.Arg<String> callback) {
		logger.info("onFcmTokenRefresh()");
		fcmManager.onTokenRefresh(callback);
	}

	/**
	 * 
	 * @param callback
	 */
	public void onFcmMessage(Fnx.Arg callback) {
		logger.info("onFcmMessage()");
		fcmManager.onMessage(callback);
	}

	/**
	 * 
	 * @param iidToken
	 */
	public void fcmSubscribe(String iidToken) {
		logger.info("fcmSubscribe()->iidToken=" + iidToken);
		String userAgent = b64decode(getUserAgent());
		logger.info("fcmSubscribe()->userAgent=" + userAgent);
		dispatch.execute(fcmService.subscribe(iidToken, userAgent), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void response) {
				MaterialToast.fireToast("Sussecfull subscription!");
			}

			@Override
			public void onFailure(Throwable throwable) {
				MaterialToast.fireToast(throwable.getMessage());
			}
		});
	}

	/**
	 * 
	 * @return
	 */
	public static native String getUserAgent() /*-{
												return $wnd.navigator.userAgent.toLowerCase();
												}-*/;

	private static native String b64decode(String a) /*-{
														return window.atob(a);
														}-*/;

}
