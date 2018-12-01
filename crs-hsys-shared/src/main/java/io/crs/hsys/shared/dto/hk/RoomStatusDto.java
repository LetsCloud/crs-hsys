/**
 * 
 */
package io.crs.hsys.shared.dto.hk;

import java.util.List;

import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public class RoomStatusDto {

	private RoomDto room;

	private List<TaskDto> tasks;

	public RoomDto getRoom() {
		return room;
	}

	public void setRoom(RoomDto room) {
		this.room = room;
	}

	public List<TaskDto> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskDto> tasks) {
		this.tasks = tasks;
	}

	public static class Builder {

		private RoomDto room;
		private List<TaskDto> tasks;

		public Builder() {
		}

		public Builder room(RoomDto room) {
			this.room = room;
			return this;
		}

		public Builder tasks(List<TaskDto> tasks) {
			this.tasks = tasks;
			return this;
		}

		public RoomStatusDto build() {
			RoomStatusDto result = new RoomStatusDto();
			result.setRoom(room);
			result.setTasks(tasks);
			return result;
		}
	}

}
