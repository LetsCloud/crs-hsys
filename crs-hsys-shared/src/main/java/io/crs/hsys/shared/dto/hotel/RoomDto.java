/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.crs.hsys.shared.constans.OccStatus;
import io.crs.hsys.shared.constans.RoomStatus;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class RoomDto extends HotelChildDto implements Comparable<RoomDto> {

	/**
	 * Egyedi szobaszám
	 */
	private String code;

	/**
	 * Emelet
	 */
	private String floor;

	/**
	 * Szoba leírása
	 */
	private String description;

	/**
	 * A szoba takarítási státusza.
	 */
	private RoomStatus roomStatus;

	/**
	 * Szobatípus hivatkozás
	 */
	private RoomTypeDtor roomType;

	/**
	 * Szoba nyitások és zárások
	 */
	private List<RoomAvailabilityDto> roomAvailabilities = new ArrayList<RoomAvailabilityDto>();

	public RoomDto() {
	}

	public RoomDto(String code, String floor, String description, RoomStatus roomStatus, RoomTypeDtor roomType,
			String guestNumber, String atendant, Integer cleaningTasks, Integer maintTasks, OccStatus occStatus,
			String currOccText, OccStatus nextOccStatus, String nextOccText) {
		this();
		this.code = code;
		this.floor = floor;
		this.description = description;
		this.roomStatus = roomStatus;
		this.roomType = roomType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

	public RoomTypeDtor getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomTypeDtor roomType) {
		this.roomType = roomType;
	}

	public List<RoomAvailabilityDto> getRoomAvailabilities() {
		return roomAvailabilities;
	}

	public void setRoomAvailabilities(List<RoomAvailabilityDto> roomAvailabilities) {

		this.roomAvailabilities = roomAvailabilities;
	}

	public void addRoomAvailability(RoomAvailabilityDto roomAvailability) {
		this.roomAvailabilities.add(roomAvailability);
	}

	@Override
	public String toString() {
		return "RoomDto [" + super.toString() + ", code=" + this.code + ", floor=" + this.floor + ", description="
				+ this.description + ", roomType=" + this.roomType + ", roomStatus=" + this.roomStatus
				+ ", roomAvailabilies=" + this.roomAvailabilities + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomDto other = (RoomDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public int compareTo(RoomDto other) {
		if (other == null)
			return 1;
		return code.compareTo(other.getCode());
	}

	@JsonIgnore
	public static Comparator<RoomDto> ORDER_BY_CODE = new Comparator<RoomDto>() {
		public int compare(RoomDto one, RoomDto other) {
			return one.getCode().compareTo(other.getCode());
		}
	};

	public static class Builder {

		private String code;
		private String floor;
		private String description;
		private RoomStatus roomStatus;
		private RoomTypeDtor roomType;

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder floor(String floor) {
			this.floor = floor;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder roomStatus(RoomStatus roomStatus) {
			this.roomStatus = roomStatus;
			return this;
		}

		public Builder roomType(RoomTypeDtor roomType) {
			this.roomType = roomType;
			return this;
		}

		public RoomDto build() {
			RoomDto dto = new RoomDto();
			dto.setCode(code);
			dto.setDescription(description);
			dto.setFloor(floor);
			dto.setRoomStatus(roomStatus);
			dto.setRoomType(roomType);
			return dto;
		}
	}
}
