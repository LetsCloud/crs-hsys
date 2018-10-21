/**
 * 
 */
package io.crs.hsys.client.core.firebase;

import io.crs.hsys.client.core.firebase.messaging.js.Messaging;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * @author robi
 *
 */
@JsType(isNative = true, namespace = "firebase.app", name = "App")
public class Firebase {

    @JsMethod(namespace = "firebase")
    public static native Firebase initializeApp(Config config);

    @JsProperty
    public native String getName();

    public native Messaging messaging();
}
