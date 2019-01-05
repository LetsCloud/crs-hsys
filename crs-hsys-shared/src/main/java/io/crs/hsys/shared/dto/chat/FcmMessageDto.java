/**
 * 
 */
package io.crs.hsys.shared.dto.chat;

import java.io.Serializable;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class FcmMessageDto implements Serializable {
	private String to;
	private FcmNotificationDto notification;
	private FcmDataDto data;

	public FcmMessageDto(String token, FcmNotificationDto notification) {
		this.to = token;
		this.notification = notification;
	}

	public FcmMessageDto(String token, FcmDataDto data) {
		this.to = token;
		this.data = data;
	}

	public FcmMessageDto(String token, FcmNotificationDto notification, FcmDataDto data) {
		this(token, notification);
		this.data = data;
	}

	public FcmNotificationDto getNotification() {
		return notification;
	}

	public void setNotification(FcmNotificationDto notification) {
		this.notification = notification;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String token) {
		this.to = token;
	}

	public FcmDataDto getData() {
		return data;
	}

	public void setData(FcmDataDto data) {
		this.data = data;
	}

}
