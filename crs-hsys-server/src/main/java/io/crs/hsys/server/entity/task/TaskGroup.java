/**
 * 
 */
package io.crs.hsys.server.entity.task;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.server.entity.common.AccountChild;
import io.crs.hsys.shared.cnst.TaskKind;

/**
 * @author CR
 *
 */
@Entity
public class TaskGroup extends AccountChild {
	private TaskKind kind;
	@Index
	private String code;
	private String description; 
	private Boolean active; 

	public TaskGroup() {
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

	@Override
	public String toString() {
		return "TaskGroup [kind=" + kind + ", code=" + code + ", description=" + description + ", active=" + active
				+ "]";
	}
	
}
