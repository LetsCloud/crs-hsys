/**
 * 
 */
package io.crs.hsys.client.core.firebase.model;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * @author robi
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class DataMessage {

	@JsProperty
	public String collapse_key;

	@JsProperty
	public String from;
	
	@JsProperty
	public KeyData data;

	@JsOverlay
	public final String getCollapse_key() {
		return collapse_key;
	}

	@JsOverlay
	public final String getFrom() {
		return from;
	}

	@JsOverlay
	public final KeyData getData() {
		return data;
	}
}
