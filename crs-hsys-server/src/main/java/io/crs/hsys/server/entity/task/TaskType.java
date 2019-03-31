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
public class TaskType extends AccountChild {
	private TaskKind kind;
	@Index
	private String code;
	private String description;
	private List<Translation> translations = new ArrayList<Translation>();
	@Index
	private Ref<TaskGroup> taskGroup;
	private Integer timeRequired;
	private List<Ref<TaskTodo>> todos = new ArrayList<Ref<TaskTodo>>();
	private Boolean active;

	public TaskType() {
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

	public TaskGroup getTaskGroup() {
		if (taskGroup == null)
			return null;
		return taskGroup.get();
	}

	public void setTaskGroup(TaskGroup taskGroup) {
		if (taskGroup.getId() != null)
			this.taskGroup = Ref.create(taskGroup);
	}

	public Integer getTimeRequired() {
		return timeRequired;
	}

	public void setTimeRequired(Integer timeRequired) {
		this.timeRequired = timeRequired;
	}

	public List<TaskTodo> getTodos() {
		List<TaskTodo> list = new ArrayList<TaskTodo>();
		for (Ref<TaskTodo> ref : todos) {
			list.add(ref.get());
		}
		return list;
	}

	public void setTodos(List<TaskTodo> todos) {
		ArrayList<Ref<TaskTodo>> refs = new ArrayList<Ref<TaskTodo>>();
		for (TaskTodo tt : todos) {
			refs.add(Ref.create(tt));
		}
		this.todos = refs;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Translation> getTranslations() {
		return translations;
	}

	public void setTranslations(List<Translation> translations) {
		this.translations = translations;
	}

}
