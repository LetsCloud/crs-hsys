/**
 * 
 */
package io.crs.hsys.client.core.firebase.messaging.js;

import io.crs.hsys.client.core.firebase.model.DataMessage;
import jsinterop.annotations.JsFunction;

/**
 * @author robi
 *
 */
public class Fnx {
	
	@FunctionalInterface
	@JsFunction
	public interface Arg {
		void apply(DataMessage val);
	}

	@FunctionalInterface
	@JsFunction
	public interface NoArg {
		void apply();
	}
}
