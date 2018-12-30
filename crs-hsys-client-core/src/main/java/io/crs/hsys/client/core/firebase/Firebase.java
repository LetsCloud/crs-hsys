/**
 * 
 */
package io.crs.hsys.client.core.firebase;

import io.crs.hsys.client.core.firebase.messaging.js.Messaging;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * firebase.app.App
 * 
 * A Firebase App holds the initialization information for a collection of
 * services. Do not call this constructor directly. Instead, use
 * firebase.initializeApp() to create an app.
 * 
 * @author robi
 *
 */
@JsType(isNative = true, namespace = "firebase.app", name = "App")
public class Firebase {

	/**
	 * The (read-only) configuration options for this app. These are the original
	 * parameters given in firebase.initializeApp().
	 * 
	 * @param config non-null Object
	 * @return
	 */
	@JsMethod(namespace = "firebase")
	public static native Firebase initializeApp(Config config);

	/**
	 * name
	 * 
	 * The (read-only) name for this app. The default app's name is "[DEFAULT]".
	 * 
	 * @return string
	 */
	@JsProperty
	public native String getName();

	/**
	 * messaging
	 * 
	 * Gets the Messaging service for the current app.
	 * 
	 * @return non-null firebase.messaging.Messaging
	 */
	public native Messaging messaging();
}
