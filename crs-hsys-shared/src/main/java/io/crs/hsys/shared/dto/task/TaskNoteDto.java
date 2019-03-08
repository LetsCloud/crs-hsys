/**
 * 
 */
package io.crs.hsys.shared.dto.task;

import java.util.Date;

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
	private String note;

	public TaskNoteDto() {
	}

	public TaskNoteDto(Date created, AppUserDtor user, String note) {
		this.created =created;
		this.user =user;
		this.note =note;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
