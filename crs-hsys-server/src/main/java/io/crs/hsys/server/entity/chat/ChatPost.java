/**
 * 
 */
package io.crs.hsys.server.entity.chat;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Ref;

import io.crs.hsys.server.entity.common.AppUser;

/**
 * @author robi
 *
 */
public class ChatPost {
	private static final Logger logger = LoggerFactory.getLogger(ChatPost.class.getName());

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
		if (sender == null)
			return null;
		return sender.get();
	}

	public void setSender(AppUser sender) {
		logger.info("Chat().setSender()->sender=" + sender);
		if (sender.getId() != null)
			this.sender = Ref.create(sender);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
