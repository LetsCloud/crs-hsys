/**
 * 
 */
package io.crs.hsys.shared.exception;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RestApiException extends Exception {

	public RestApiException(Throwable e) {
		super(e);
	}
}
