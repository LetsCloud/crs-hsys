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

}
