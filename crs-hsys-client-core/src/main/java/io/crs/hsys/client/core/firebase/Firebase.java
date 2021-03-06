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
	 * Mivel a firebase névtérben található initializeApp metódus a firebase.app
	 * névtérben található App osztályt adja vissza, ésszerű azt a firebase.app.App
	 * osztályt leképező osztályban leképezni.
	 * 
	 * @param config non-null Object
	 * @return Firebase azaz firebase.app.App
	 */
	@JsMethod(namespace = "firebase")
	public static native Firebase initializeApp(Config config);

	/**
	 * A firebase.app.App osztály name tulajdonsága.
	 * 
	 * The (read-only) name for this app. The default app's name is "[DEFAULT]".
	 * 
	 * @return string
	 */
	@JsProperty
	public native String getName();

	/**
	 * A firebase.app.App osztály messaging metódusa.
	 * 
	 * Gets the Messaging service for the current app.
	 * 
	 * @return non-null firebase.messaging.Messaging
	 */
	public native Messaging messaging();
}
