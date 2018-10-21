/**
 * 
 */
package io.crs.hsys.client.core.firebase.messaging.js;

import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;
import io.crs.hsys.client.core.promise.xgwt.Promise;
import jsinterop.annotations.JsType;

/**
 * @author robi
 *
 */
@JsType(isNative = true, namespace = "firebase.messaging", name = "Messaging")
public class Messaging {

	public native Promise<Void, Error> deleteToken(String token);

	public native Promise<String, Error> getToken();

	public native Fnx.NoArg onMessage(Fnx.Arg callback);

	public native Fnx.NoArg onTokenRefresh(Fnx.NoArg callback);

	public native Promise<Void, Error> requestPermission();

	// public native Functions.EventFunc setBackgroundMessageHandler();

	public native void useServiceWorker(ServiceWorkerRegistration r);

}
