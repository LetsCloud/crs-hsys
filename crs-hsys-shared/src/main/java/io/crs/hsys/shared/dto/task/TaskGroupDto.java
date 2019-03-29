/**
 * 
 */
package io.crs.hsys.shared.dto.task;

import io.crs.hsys.shared.cnst.TaskKind;
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

	public TaskGroupDto(Builder<?> builder) {
		super(builder);
		kind = builder.kind;
		code = builder.code;
		description = builder.description;
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

		public T active(Boolean active) {
			this.active = active;
			return self();
		}

		public TaskGroupDto build() {
			return new TaskGroupDto(this);
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
