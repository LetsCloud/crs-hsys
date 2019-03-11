/**
 * 
 */
package io.crs.hsys.server.entity.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;

import io.crs.hsys.server.entity.common.AccountChild;
import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.entity.hotel.Room;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.constans.TaskStatus;

/**
 * @author robi
 *
 */
@Entity
public class Task extends AccountChild {
	private TaskKind kind;
	private Ref<TaskType> type;
	private Date created;
	private Date updated;
	private TaskStatus status;
	private String description;
	private Ref<AppUser> reporter;
	private Ref<AppUser> assignee;
	private Ref<Room> room;
	private Date dueDate;
	private List<TaskNote> notes = new ArrayList<TaskNote>();

	public Task() {
	}

	public TaskKind getKind() {
		return kind;
	}

	public void setKind(TaskKind kind) {
		this.kind = kind;
	}

	public Ref<TaskType> getType() {
		return type;
	}

	public void setType(Ref<TaskType> type) {
		this.type = type;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AppUser getReporter() {
		if (reporter == null)
			return null;
		return reporter.get();
	}

	public void setReporter(AppUser reporter) {
		if (reporter.getId() != null)
			this.reporter = Ref.create(reporter);
	}

	public AppUser getAssignee() {
		if (assignee == null)
			return null;
		return assignee.get();
	}

	public void setAssignee(AppUser assignee) {
		if (assignee.getId() != null)
			this.assignee = Ref.create(assignee);
	}

	public Room getRoom() {
		if (room == null)
			return null;
		return room.get();
	}

	public void setRoom(Room room) {
		if (room.getId() != null)
			this.room = Ref.create(room);
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public List<TaskNote> getNotes() {
		return notes;
	}

	public void setNotes(List<TaskNote> notes) {
		this.notes = notes;
	}

	public void addNote(TaskNote note) {
		this.notes.add(note);
	}

}
