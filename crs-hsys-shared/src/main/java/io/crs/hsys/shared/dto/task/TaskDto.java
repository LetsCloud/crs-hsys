/**
 * 
 */
package io.crs.hsys.shared.dto.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.crs.hsys.shared.constans.TaskStatus;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.common.AccountChildDto;
import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.dto.common.AppUserDtor;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class TaskDto extends AccountChildDto {

	private TaskKind kind;
	
	private TaskTypeDto type;

	private Date created;

	private Date updated;
	
	private TaskStatus status;

	private String title;

	private String description;

	private AppUserDtor reporter;

	private AppUserDtor assignee;

	private AppUserDtor inspector;

	private List<AppUserDtor> assignies = new ArrayList<AppUserDtor>();

	private List<TaskAttrDto> attributes = new ArrayList<TaskAttrDto>();

	private List<TaskNoteDto> notes = new ArrayList<TaskNoteDto>();

	public TaskDto() {
	}

	public TaskDto(TaskKind type, String title, Date created, Date updated, List<TaskAttrDto> attributes,
			String description) {
	}

	public TaskKind getKind() {
		return kind;
	}

	public void setKind(TaskKind kind) {
		this.kind = kind;
	}

	public TaskTypeDto getType() {
		return type;
	}

	public void setType(TaskTypeDto type) {
		this.type = type;
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

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
	
	public AppUserDtor getReporter() {
		return reporter;
	}

	public void setReporter(AppUserDtor reporter) {
		this.reporter = reporter;
	}

	public List<AppUserDtor> getAssignies() {
		return assignies;
	}

	public void setAssignies(List<AppUserDtor> assignies) {
		this.assignies = assignies;
	}

	public List<TaskAttrDto> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<TaskAttrDto> attributes) {
		this.attributes = attributes;
	}

	public AppUserDtor getAssignee() {
		return assignee;
	}

	public void setAssignee(AppUserDtor assignee) {
		this.assignee = assignee;
	}

	public AppUserDtor getInspector() {
		return inspector;
	}

	public void setInspector(AppUserDtor inspector) {
		this.inspector = inspector;
	}

	public List<TaskNoteDto> getNotes() {
		return notes;
	}

	public void setNotes(List<TaskNoteDto> notes) {
		this.notes = notes;
	}

	public static class Builder {

		private TaskKind kind;
		private TaskTypeDto type;
		private Date created;
		private Date updated;
		private TaskStatus status;
		private String title;
		private String description;
		private AppUserDtor reporter;
		private AppUserDtor assignee;
		private AppUserDtor inspector;
		private List<AppUserDto> assignies = new ArrayList<AppUserDto>();
		private List<TaskAttrDto> attributes = new ArrayList<TaskAttrDto>();
		private List<TaskNoteDto> notes = new ArrayList<TaskNoteDto>();

		public Builder() {
		}

		public Builder kind(TaskKind kind) {
			this.kind = kind;
			return this;
		}

		public Builder type(TaskTypeDto type) {
			this.type = type;
			return this;
		}

		public Builder created(Date created) {
			this.created = created;
			return this;
		}

		public Builder status(TaskStatus status) {
			this.status = status;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder assignee(AppUserDtor assignee) {
			this.assignee = assignee;
			return this;
		}

		public TaskDto build() {
			TaskDto result = new TaskDto();
			result.setKind(kind);
			result.setType(type);
			result.setCreated(created);
			result.setUpdated(updated);
			result.setStatus(status);
			result.setDescription(description);
			result.setAssignee(assignee);
			return result;
		}
	}

}
