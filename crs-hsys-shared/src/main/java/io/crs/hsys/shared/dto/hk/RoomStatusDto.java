/**
 * 
 */
package io.crs.hsys.shared.dto.hk;

import java.util.List;

import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomOccDto;
import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public class RoomStatusDto {

	private RoomDto room;

	/**
	 * 
	 */
	private RoomOccDto currOccStatus;

	/**
	 * 
	 */
	private RoomOccDto nextOccStatus;

	private List<TaskDto> tasks;

	protected RoomStatusDto(Builder<?> builder) {
		room = builder.room;
		currOccStatus = builder.currOccStatus;
		nextOccStatus = builder.nextOccStatus;
		tasks = builder.tasks;
	}

	public RoomDto getRoom() {
		return room;
	}

	public void setRoom(RoomDto room) {
		this.room = room;
	}

	public RoomOccDto getCurrOccStatus() {
		return currOccStatus;
	}

	public void setCurrOccStatus(RoomOccDto currOccStatus) {
		this.currOccStatus = currOccStatus;
	}

	public RoomOccDto getNextOccStatus() {
		return nextOccStatus;
	}

	public void setNextOccStatus(RoomOccDto nextOccStatus) {
		this.nextOccStatus = nextOccStatus;
	}

	public List<TaskDto> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskDto> tasks) {
		this.tasks = tasks;
	}

	public static abstract class Builder<T extends Builder<T>> {

		protected abstract T self();

		private RoomDto room;
		private RoomOccDto currOccStatus;
		private RoomOccDto nextOccStatus;
		private List<TaskDto> tasks;

		public T room(RoomDto room) {
			this.room = room;
			return self();
		}

		public T currOccStatus(RoomOccDto currOccStatus) {
			this.currOccStatus = currOccStatus;
			return self();
		}

		public T nextOccStatus(RoomOccDto nextOccStatus) {
			this.nextOccStatus = nextOccStatus;
			return self();
		}

		public T tasks(List<TaskDto> tasks) {
			this.tasks = tasks;
			return self();
		}

		public RoomStatusDto build() {
			return new RoomStatusDto(this);
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
