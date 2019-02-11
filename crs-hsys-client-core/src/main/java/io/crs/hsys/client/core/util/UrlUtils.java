/**
 * 
 */
package io.crs.hsys.client.core.util;

import com.google.gwt.core.client.GWT;

/**
 * @author robi
 *
 */
public class UrlUtils {

	public static String getBaseUrl() {
		String baseUrl = GWT.getHostPageBaseURL();
		int shortening = (baseUrl.endsWith("/")) ? 1 : 0;
		return baseUrl.substring(0, baseUrl.length() - shortening);
	}
	
	public static String getImageUrl() {
		return getBaseUrl() + "/image/";
	}
}
