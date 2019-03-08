/**
 * 
 */
package io.crs.hsys.shared.dto.task;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.common.AccountChildDto;
import io.crs.hsys.shared.dto.common.TranslationDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class TaskTypeDto extends AccountChildDto {
	private TaskKind kind;
	private String code;
	private String description;
	private List<TranslationDto> translations = new ArrayList<TranslationDto>();
	private TaskGroupDto taskGroup;
	private Integer timeRequired;
	private List<TaskTodoDto> todos = new ArrayList<TaskTodoDto>();
	private Boolean active;

	public TaskTypeDto() {
		super();
	}

	public TaskTypeDto(Builder<?> builder) {
		super(builder);
		kind = builder.kind;
		code = builder.code;
		description = builder.description;
		translations = builder.translations;
		taskGroup = builder.taskGroup;
		timeRequired = builder.timeRequired;
		todos = builder.todos;
		taskGroup = builder.taskGroup;
		active = builder.active;
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

	public List<TranslationDto> getTranslations() {
		return translations;
	}

	public void setTranslations(List<TranslationDto> translations) {
		this.translations = translations;
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

	public List<TaskTodoDto> getTodos() {
		return todos;
	}

	public void setTodos(List<TaskTodoDto> todos) {
		this.todos = todos;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public static abstract class Builder<T extends Builder<T>> extends AccountChildDto.Builder<T> {
		private TaskKind kind;
		private String code;
		private String description;
		private List<TranslationDto> translations = new ArrayList<TranslationDto>();
		private TaskGroupDto taskGroup;
		private Integer timeRequired;
		private List<TaskTodoDto> todos = new ArrayList<TaskTodoDto>();
		private Boolean active;

		public T kind(TaskKind kind) {
			this.kind = kind;
			return self();
		}

		public T code(String code) {
			this.code = code;
			return self();
		}

		public T description(String description) {
			this.description = description;
			return self();
		}

		public T translations(List<TranslationDto> translations) {
			this.translations = translations;
			return self();
		}

		public T taskGroup(TaskGroupDto taskGroup) {
			this.taskGroup = taskGroup;
			return self();
		}

		public T timeRequired(Integer timeRequired) {
			this.timeRequired = timeRequired;
			return self();
		}

		public T todos(List<TaskTodoDto> todos) {
			this.todos = todos;
			return self();
		}

		public T addTodo(TaskTodoDto todo) {
			this.todos.add(todo);
			return self();
		}

		public T active(Boolean active) {
			this.active = active;
			return self();
		}

		public TaskTypeDto build() {
			return new TaskTypeDto(this);
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
