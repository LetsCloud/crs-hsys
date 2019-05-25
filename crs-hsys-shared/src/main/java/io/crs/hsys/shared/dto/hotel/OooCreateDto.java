/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.Date;
import java.util.List;

import io.crs.hsys.shared.cnst.OooReturnWhen;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.dto.common.AppUserDtor;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class OooCreateDto extends HotelChildDto {

	private List<RoomDto> rooms;
	private List<RoomTypeDtor> roomTypes;
	private String fromRoom;
	private String toRoom;
	private String floor;
	private List<RoomStatus> roomStatuses;
	private Date fromDate;
	private Date toDate;
	private RoomStatus returnAs;
	private OooReturnWhen returnWhen;
	private String remarks;
	private AppUserDtor createdBy;

	public OooCreateDto() {
	}

	public List<RoomDto> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomDto> rooms) {
		this.rooms = rooms;
	}

	public String getFromRoom() {
		return fromRoom;
	}

	public void setFromRoom(String fromRoom) {
		this.fromRoom = fromRoom;
	}

	public String getToRoom() {
		return toRoom;
	}

	public void setToRoom(String toRoom) {
		this.toRoom = toRoom;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public List<RoomStatus> getRoomStatuses() {
		return roomStatuses;
	}

	public void setRoomStatuses(List<RoomStatus> roomStatuses) {
		this.roomStatuses = roomStatuses;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public RoomStatus getReturnAs() {
		return returnAs;
	}

	public void setReturnAs(RoomStatus returnAs) {
		this.returnAs = returnAs;
	}

	public OooReturnWhen getReturnWhen() {
		return returnWhen;
	}

	public void setReturnWhen(OooReturnWhen returnWhen) {
		this.returnWhen = returnWhen;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<RoomTypeDtor> getRoomTypes() {
		return roomTypes;
	}

	public void setRoomTypes(List<RoomTypeDtor> roomTypes) {
		this.roomTypes = roomTypes;
	}

	public AppUserDtor getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(AppUserDtor createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "OooCreateDto [rooms=" + rooms + ", roomTypes=" + roomTypes + ", fromRoom=" + fromRoom + ", toRoom="
				+ toRoom + ", floor=" + floor + ", roomStatuses=" + roomStatuses + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", returnAs=" + returnAs + ", returnWhen=" + returnWhen + ", remarks="
				+ remarks + ", createdBy=" + createdBy + "]";
	}

}
