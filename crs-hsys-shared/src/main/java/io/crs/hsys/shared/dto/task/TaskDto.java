/**
 * 
 */
package io.crs.hsys.shared.dto.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.crs.hsys.shared.constans.TaskStatus;
import io.crs.hsys.shared.constans.TaskType;
import io.crs.hsys.shared.dto.common.AccountChildDto;
import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class TaskDto extends AccountChildDto {

	private TaskType type;
	
	private TaskStatus status;

	private String title;

	private AppUserDto reporter;

	private AppUserDto assignee;

	private AppUserDto inspector;

	private List<AppUserDto> assignies = new ArrayList<AppUserDto>();

	private Date created;

	private Date updated;

	private List<TaskAttrDto> attributes = new ArrayList<TaskAttrDto>();

	private List<TaskNoteDto> notes = new ArrayList<TaskNoteDto>();

	private String description;

	public TaskDto() {
	}

	public TaskDto(TaskType type, String title, Date created, Date updated, List<TaskAttrDto> attributes,
			String description) {
	}

	public TaskType getType() {
		return type;
	}

	public void setType(TaskType type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AppUserDto getReporter() {
		return reporter;
	}

	public void setReporter(AppUserDto reporter) {
		this.reporter = reporter;
	}

	public List<AppUserDto> getAssignies() {
		return assignies;
	}

	public void setAssignies(List<AppUserDto> assignies) {
		this.assignies = assignies;
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

	public List<TaskAttrDto> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<TaskAttrDto> attributes) {
		this.attributes = attributes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AppUserDto getAssignee() {
		return assignee;
	}

	public void setAssignee(AppUserDto assignee) {
		this.assignee = assignee;
	}

	public AppUserDto getInspector() {
		return inspector;
	}

	public void setInspector(AppUserDto inspector) {
		this.inspector = inspector;
	}

	public List<TaskNoteDto> getNotes() {
		return notes;
	}

	public void setNotes(List<TaskNoteDto> notes) {
		this.notes = notes;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

}
