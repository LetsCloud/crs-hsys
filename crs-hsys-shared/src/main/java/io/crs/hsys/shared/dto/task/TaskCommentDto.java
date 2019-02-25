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
public class TaskCommentDto implements Dto {

	private Date created;	
	private AppUserDto commenter;
	private String comment;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public AppUserDto getCommenter() {
		return commenter;
	}

	public void setCommenter(AppUserDto commenter) {
		this.commenter = commenter;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
