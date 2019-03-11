/**
 * 
 */
package io.crs.hsys.server.entity.task;

import java.util.Date;

import com.googlecode.objectify.Ref;

import io.crs.hsys.server.entity.common.AppUser;

/**
 * @author robi
 *
 */
public class TaskNote {
	private Date created;
	private Ref<AppUser> user;
	private String text;

	public TaskNote() {}

	public TaskNote(Date created, AppUser user, String text) {
		this.created = created;
		setUser(user);
		this.text = text;
	}
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public AppUser getUser() {
		if (user == null)
			return null;
		return user.get();
	}

	public void setUser(AppUser user) {
		if (user.getId() != null)
			this.user = Ref.create(user);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
