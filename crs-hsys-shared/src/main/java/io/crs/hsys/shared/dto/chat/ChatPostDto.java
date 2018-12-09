/**
 * 
 */
package io.crs.hsys.shared.dto.chat;

import java.util.Date;

import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class ChatPostDto implements Dto {

	private Date created;

	private AppUserDto sender;

	private String message;

	public ChatPostDto() {
	}

	public ChatPostDto(AppUserDto sender, String message) {
		this();
		this.sender = sender;
		this.message = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public AppUserDto getSender() {
		return sender;
	}

	public void setSender(AppUserDto sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		String ret = "ChatPostDto:{created=" + created + ", message=" + message + ", sender=" + sender + "}";
		return ret;
	}

}
