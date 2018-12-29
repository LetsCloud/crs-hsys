/**
 * 
 */
package io.crs.hsys.server.entity.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.OnSave;

import io.crs.hsys.server.entity.common.AccountChild;
import io.crs.hsys.server.entity.common.AppUser;

/**
 * @author robi
 *
 */
@Entity
public class Chat extends AccountChild {
	private static final Logger logger = LoggerFactory.getLogger(Chat.class.getName());

	private Date created;

	private Date updated;

	private Ref<AppUser> sender;

	private List<Ref<AppUser>> receivers = new ArrayList<Ref<AppUser>>();

	private String message;

	private Date closed;

	private List<ChatPost> posts = new ArrayList<ChatPost>();

	private String url;

	@OnSave
	void maintainPosts() {
		for (ChatPost p : this.posts) {
			if (p.getCreated() == null)
				p.setCreated(new Date());
		}
	}

	public Chat() {
		logger.info("Chat()");
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

	public List<AppUser> getReceivers() {
		return AppUser.ref2EntityList(receivers);
	}

	public void setReceivers(List<AppUser> receivers) {
		logger.info("Chat().setReceivers()");
		this.receivers = AppUser.entity2RefList(receivers);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		logger.info("Chat().setMessage()");
		this.message = message;
	}

	public List<ChatPost> getPosts() {
		return posts;
	}

	public void setPosts(List<ChatPost> posts) {
		logger.info("Chat().setPosts()");
		this.posts = posts;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		logger.info("Chat().setCreated()");
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		logger.info("Chat().setUpdated()");
		this.updated = updated;
	}

	public Date getClosed() {
		return closed;
	}

	public void setClosed(Date closed) {
		logger.info("Chat().setClosed()");
		this.closed = closed;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		logger.info("Chat().setUrl()");
		this.url = url;
	}
}
