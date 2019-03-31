/**
 * 
 */
package io.crs.hsys.server.entity.task;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.server.entity.common.AccountChild;
import io.crs.hsys.shared.cnst.TaskKind;

/**
 * @author robi
 *
 */
@Entity
public class TaskTodo extends AccountChild {
	private TaskKind kind;
	@Index
	private Ref<TaskGroup> taskGroup;
	@Index
	private String description;
	private List<Translation> translations = new ArrayList<Translation>();	
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

	public List<Translation> getTranslations() {
		return translations;
	}

	public void setTranslations(List<Translation> translations) {
		this.translations = translations;
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
