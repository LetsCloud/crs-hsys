/**
 * 
 */
package io.crs.hsys.shared.dto.task;

import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.common.AccountChildDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class TaskGroupDto extends AccountChildDto {
	private TaskKind kind;
	private String code;
	private String description;
	private Boolean active;

	public TaskGroupDto() {
	}

	public TaskGroupDto(String code, String description) {
		this();
		this.code = code;
		this.description = description;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public static class Builder {

		private TaskKind kind;
		private String code;
		private String description;
		private Boolean active;

		public Builder() {
		}

		public Builder kind(TaskKind kind) {
			this.kind = kind;
			return this;
		}

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder active(Boolean active) {
			this.active = active;
			return this;
		}

		public TaskGroupDto build() {
			TaskGroupDto result = new TaskGroupDto();
			result.setKind(kind);
			result.setCode(code);
			result.setDescription(description);
			result.setActive(active);
			return result;
		}
	}

}
