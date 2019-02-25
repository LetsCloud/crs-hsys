/**
 * 
 */
package io.crs.hsys.server.entity.task;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;

import io.crs.hsys.server.entity.common.AccountChild;
import io.crs.hsys.shared.constans.TaskKind;

/**
 * @author robi
 *
 */
@Entity
public class TaskTodo extends AccountChild {
	private TaskKind kind;
	private Ref<TaskGroup> taskGroup;
	private String description;
	private Integer timeRequired;
	private Boolean active;

	public TaskTodo() {
	}

	public TaskKind getKind() {
		return kind;
	}

	public void setKind(TaskKind kind) {
		this.kind = kind;
	}

	public TaskGroup getTaskGroup() {
		if (taskGroup == null)
			return null;
		return taskGroup.get();
	}

	public void setTaskGroup(TaskGroup taskGroup) {
		if (taskGroup.getId() != null)
			this.taskGroup = Ref.create(taskGroup);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTimeRequired() {
		return timeRequired;
	}

	public void setTimeRequired(Integer timeRequired) {
		this.timeRequired = timeRequired;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "TaskTodo [kind=" + kind + ", description=" + description + ", timeRequired=" + timeRequired
				+ ", active=" + active + "]";
	}
}
