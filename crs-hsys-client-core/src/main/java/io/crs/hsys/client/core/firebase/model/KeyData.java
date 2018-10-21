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
public class KeyData {

	@JsProperty
	public String icon;

	@JsProperty
	public String title;

	@JsProperty
	public String body;

	@JsProperty
	public String click_action;

	@JsOverlay
	public final String getIcon() {
		return icon;
	}

	@JsOverlay
	public final String getTitle() {
		return title;
	}

	@JsOverlay
	public final String getBody() {
		return body;
	}

	@JsOverlay
	public final String getAction() {
		return click_action;
	}

}
