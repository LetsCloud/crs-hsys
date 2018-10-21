/**
 * 
 */
package io.crs.hsys.server.security;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import io.crs.hsys.server.entity.common.AppUser;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {
	private final String appUrl;
	private final Locale locale;
	private final AppUser user;

	public OnRegistrationCompleteEvent(AppUser user, Locale locale, String appUrl) {
		super(user);

		this.user = user;
		this.locale = locale;
		this.appUrl = appUrl;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public AppUser getUser() {
		return user;
	}

}