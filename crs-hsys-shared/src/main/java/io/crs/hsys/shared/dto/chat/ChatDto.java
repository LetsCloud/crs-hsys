/**
 * 
 */
package io.crs.hsys.shared.dto.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.crs.hsys.shared.dto.common.AccountChildDto;
import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class ChatDto extends AccountChildDto {

	private AppUserDto sender;

	private List<AppUserDto> receivers = new ArrayList<AppUserDto>();

	private String message;

	private List<ChatPostDto> posts = new ArrayList<ChatPostDto>();

	private Date created;

	private Date updated;

	private Date closed;

	private String url;

	public ChatDto() {
	}

	public AppUserDto getSender() {
		return sender;
	}

	public void setSender(AppUserDto sender) {
		this.sender = sender;
	}

	public List<AppUserDto> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<AppUserDto> receivers) {
		this.receivers = receivers;
	}

	public void addReceiver(AppUserDto receiver) {
		this.receivers.add(receiver);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ChatPostDto> getPosts() {
		return posts;
	}

	public void setPosts(List<ChatPostDto> posts) {
		this.posts = posts;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getClosed() {
		return closed;
	}

	public void setClosed(Date closed) {
		this.closed = closed;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
