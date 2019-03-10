/**
 * 
 */
package io.crs.hsys.server.entity.task;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;

import io.crs.hsys.server.entity.common.AccountChild;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.task.TaskGroupDto;

/**
 * @author robi
 *
 */
@Entity
public class Task extends AccountChild {
	private TaskKind kind;
	private String code;
	private String description;
	private TaskGroupDto taskGroup;
	private Integer timeRequired;
	private List<Ref<TaskTodo>> todos = new ArrayList<Ref<TaskTodo>>();
	private Boolean active; 

	public Task() {
	}

	public TaskKind getKind() {
		return kind;
	}

	public void setKind(TaskKind kind) {
		this.kind = kind;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskGroupDto getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(TaskGroupDto taskGroup) {
		this.taskGroup = taskGroup;
	}

	public Integer getTimeRequired() {
		return timeRequired;
	}

	public void setTimeRequired(Integer timeRequired) {
		this.timeRequired = timeRequired;
	}

	public List<Ref<TaskTodo>> getTodos() {
		return todos;
	}

	public void setTodos(List<Ref<TaskTodo>> todos) {
		this.todos = todos;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
