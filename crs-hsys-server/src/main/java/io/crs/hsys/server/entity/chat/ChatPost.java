/**
 * 
 */
package io.crs.hsys.server.entity.chat;

import java.util.Date;

import com.googlecode.objectify.Ref;

import io.crs.hsys.server.entity.common.AppUser;

/**
 * @author robi
 *
 */
public class ChatPost {

	private Date created;

	private Ref<AppUser> sender;

	private String message;

	public ChatPost() {
	}

	public ChatPost(AppUser sender, String message, Date created) {
		this();
		setSender(sender);
		this.message = message;
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public AppUser getSender() {
		return sender.get();
	}

	public void setSender(AppUser sender) {
		this.sender = Ref.create(sender);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
