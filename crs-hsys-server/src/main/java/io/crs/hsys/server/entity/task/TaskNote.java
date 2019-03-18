/**
 * 
 */
package io.crs.hsys.server.entity.task;

import java.util.Date;

import com.googlecode.objectify.Ref;

import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.shared.constans.TaskNoteType;

/**
 * @author robi
 *
 */
public class TaskNote {
	private Date created;
	private Ref<AppUser> user;
	private TaskNoteType type;
	private String text;

	public TaskNote() {
	}

	public TaskNote(Date created, AppUser user, TaskNoteType type) {
		this();
		this.created = created;
		setUser(user);
		this.type = type;
	}

	public TaskNote(Date created, AppUser user, TaskNoteType type, String text) {
		this(created, user, type);
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

	public TaskNoteType getType() {
		return type;
	}

	public void setType(TaskNoteType type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "TaskNote [created=" + created + ", user=" + user + ", type=" + type + ", text=" + text + "]";
	}

}
