/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.crs.hsys.shared.constans.FoRoomStatus;
import io.crs.hsys.shared.constans.RoomStatus;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class RoomDto extends HotelChildDto {

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
	 * Foglalt e a szoba
	 */
	private Boolean occupied;

	/**
	 * A szoba front office státusza.
	 */
	private FoRoomStatus foRoomStatus;

	/**
	 * Szobatípus hivatkozás
	 */
	private RoomTypeDtor roomType;

	/**
	 * Szoba nyitások és zárások
	 */
	private List<RoomAvailabilityDto> roomAvailabilities = new ArrayList<RoomAvailabilityDto>();

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

	public Boolean getOccupied() {
		return occupied;
	}

	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	public FoRoomStatus getFoRoomStatus() {
		return foRoomStatus;
	}

	public void setFoRoomStatus(FoRoomStatus foRoomStatus) {
		this.foRoomStatus = foRoomStatus;
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
				+ this.description + ", roomType=" + this.roomType + ", roomStatus=" + this.roomStatus + ", occupied="
				+ this.occupied + ", foRoomStatus=" + this.foRoomStatus + ", roomAvailabilies="
				+ this.roomAvailabilities + "]";
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
		private Boolean occupied;
		private FoRoomStatus foRoomStatus;
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
		
		public Builder occupied(Boolean occupied) {
			this.occupied = occupied;
			return this;
		}
		
		public Builder foRoomStatus(FoRoomStatus foRoomStatus) {
			this.foRoomStatus = foRoomStatus;
			return this;
		}
		
		public Builder roomType(RoomTypeDtor roomType) {
			this.roomType = roomType;
			return this;
		}
		
		public RoomDto build() {
			RoomDto dto = new  RoomDto();
			dto.setCode(code);
			dto.setDescription(description);
			dto.setFloor(floor);
			dto.setFoRoomStatus(foRoomStatus);
			dto.setOccupied(occupied);
			dto.setRoomStatus(roomStatus);
			dto.setRoomType(roomType);
			return dto;
		}
	}	
}
