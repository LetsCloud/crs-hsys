/**
 * 
 */
package io.crs.hsys.server.entity.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	private Date created;

	private Date updated;

	private Ref<AppUser> sender;

	private List<Ref<AppUser>> receivers = new ArrayList<Ref<AppUser>>();

	private String message;

	private Date closed;

	private List<ChatPost> posts = new ArrayList<ChatPost>();

	private String url;

	public Chat() {

	}

	/**
	 * Az entitás verziiószámát növelő trigger, amely az entitás mentése előtt fut
	 * le.
	 */
	@OnSave
	private void onSave() {
		if (getId() == null) {
			created = new Date();
		} else {
			updated = new Date();
		}
	}

	public AppUser getSender() {
		return sender.get();
	}

	public void setSender(AppUser sender) {
		this.sender = Ref.create(sender);
	}

	public List<AppUser> getReceivers() {
		return AppUser.ref2EntityList(receivers);
	}

	public void setReceivers(List<AppUser> receivers) {
		this.receivers = AppUser.entity2RefList(receivers);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ChatPost> getPosts() {
		return posts;
	}

	public void setPosts(List<ChatPost> posts) {
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
