/**
 * 
 */
package io.crs.hsys.shared.dto.task;

import java.util.Date;

import io.crs.hsys.shared.constans.TaskNoteType;
import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.common.AppUserDtor;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class TaskNoteDto implements Dto {
	private Date created;
	private AppUserDtor user;
	private TaskNoteType type;
	private String text;

	public TaskNoteDto() {
	}

	public TaskNoteDto(Date created, AppUserDtor user, TaskNoteType type) {
		this();
		this.created = created;
		this.user = user;
		this.type = type;
	}

	public TaskNoteDto(Date created, AppUserDtor user, TaskNoteType type, String text) {
		this(created, user, type);
		this.text = text;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public AppUserDtor getUser() {
		return user;
	}

	public void setUser(AppUserDtor user) {
		this.user = user;
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
		return "TaskNoteDto [created=" + created + ", user=" + user + ", type=" + type + ", text=" + text + "]";
	}
}
