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

	private List<TaskAttrDto> attributes = new ArrayList<TaskAttrDto>();

	private List<TaskCommentDto> notes = new ArrayList<TaskCommentDto>();

	public TaskDto() {
		super();
	}

	public TaskDto(Builder<?> builder) {
		super(builder);
		kind = builder.kind;
		type = builder.type;
		created = builder.created;
		updated = builder.updated;
		status = builder.status;
		title = builder.title;
		description = builder.description;
		reporter = builder.reporter;
		assignee = builder.assignee;
		attributes = builder.attributes;
		notes = builder.notes;
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

	public List<TaskCommentDto> getNotes() {
		return notes;
	}

	public void setNotes(List<TaskCommentDto> notes) {
		this.notes = notes;
	}

	/**
	 * 
	 * @author robi
	 *
	 * @param <T>
	 */
	public static abstract class Builder<T extends Builder<T>> extends AccountChildDto.Builder<T> {

		private TaskKind kind;
		private TaskTypeDto type;
		private Date created;
		private Date updated;
		private TaskStatus status;
		private String title;
		private String description;
		private AppUserDtor reporter;
		private AppUserDtor assignee;
		private List<TaskAttrDto> attributes = new ArrayList<TaskAttrDto>();
		private List<TaskCommentDto> notes = new ArrayList<TaskCommentDto>();

		public T kind(TaskKind kind) {
			this.kind = kind;
			return self();
		}

		public T type(TaskTypeDto type) {
			this.type = type;
			return self();
		}

		public T created(Date created) {
			this.created = created;
			return self();
		}

		public T status(TaskStatus status) {
			this.status = status;
			return self();
		}

		public T description(String description) {
			this.description = description;
			return self();
		}

		public T reporter(AppUserDtor reporter) {
			this.reporter = reporter;
			return self();
		}

		public T assignee(AppUserDtor assignee) {
			this.assignee = assignee;
			return self();
		}

		public TaskDto build() {
			return new TaskDto(this);
		}
	}

	/**
	 * 
	 * @author robi
	 *
	 */
	protected static class Builder2 extends Builder<Builder2> {
		@Override
		protected Builder2 self() {
			return this;
		}
	}

	public static Builder<?> builder() {
		return new Builder2();
	}

}
