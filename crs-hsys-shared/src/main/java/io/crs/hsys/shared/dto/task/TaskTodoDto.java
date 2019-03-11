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
public class TaskTodoDto extends AccountChildDto {
	private TaskKind kind;
	private TaskGroupDto taskGroup;
	private String description;
	private List<TranslationDto> translations = new ArrayList<TranslationDto>();
	private Integer timeRequired;
	private Boolean active; 

	public TaskTodoDto() {
	}

	public TaskTodoDto(Builder<?> builder) {
		super(builder);
		kind = builder.kind;
		description = builder.description;
		translations = builder.translations;
		taskGroup = builder.taskGroup;
		timeRequired = builder.timeRequired;
		taskGroup = builder.taskGroup;
		active = builder.active;
	}

	public TaskKind getKind() {
		return kind;
	}

	public void setKind(TaskKind kind) {
		this.kind = kind;
	}

	public String getDescription() {
		return description;
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

	public static abstract class Builder<T extends Builder<T>> extends AccountChildDto.Builder<T> {
		private TaskKind kind;
		private TaskGroupDto taskGroup;
		private String description;
		private List<TranslationDto> translations = new ArrayList<TranslationDto>();
		private Integer timeRequired;
		private Boolean active; 

		public T kind(TaskKind kind) {
			this.kind = kind;
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

		public T addTranslation(TranslationDto translation) {
			this.translations.add(translation);
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

		public T active(Boolean active) {
			this.active = active;
			return self();
		}

		public TaskTodoDto build() {
			return new TaskTodoDto(this);
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
