/**
 * 
 */
package io.crs.hsys.server.model;

import io.crs.hsys.shared.dto.chat.NotificationDto;

/**
 * @author robi
 *
 */
public class FcmMessage {
	private NotificationDto notification;
	private String to;

	public FcmMessage(NotificationDto notification, String to) {
		this.notification = notification;
		this.to = to;
	}

	public NotificationDto getNotification() {
		return notification;
	}

	public void setNotification(NotificationDto notification) {
		this.notification = notification;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
