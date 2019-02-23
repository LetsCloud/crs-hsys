/**
 * 
 */
package io.crs.hsys.shared.dto.task;

import java.util.Date;

import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class TaskNoteDto implements Dto {

	private Date created;
	
	private AppUserDto who;
	
	private String note;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public AppUserDto getWho() {
		return who;
	}

	public void setWho(AppUserDto who) {
		this.who = who;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
