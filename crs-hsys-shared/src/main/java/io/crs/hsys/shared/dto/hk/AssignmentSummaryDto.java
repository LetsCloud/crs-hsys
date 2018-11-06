/**
 * 
 */
package io.crs.hsys.shared.dto.hk;

import java.util.HashMap;
import java.util.Map;

import io.crs.hsys.shared.constans.RoomStatus;
import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class AssignmentSummaryDto implements Dto {

	private AppUserDto attendantDto;

	private Map<RoomStatus, Integer> roomSummary;

	public AssignmentSummaryDto() {
	}

	public AssignmentSummaryDto(AppUserDto attendantDto, Map<RoomStatus, Integer> roomSummary) {
		this();
		this.attendantDto = attendantDto;
		this.roomSummary = roomSummary;
	}

	public AssignmentSummaryDto(AppUserDto attendantDto, RoomStatus roomStatus, Integer count) {
		this(attendantDto, null);

		Map<RoomStatus, Integer> rs = new HashMap<RoomStatus, Integer>();
		rs.put(roomStatus, count);
		setRoomSummary(rs);
	}

	public AppUserDto getAttendantDto() {
		return attendantDto;
	}

	public void setAttendantDto(AppUserDto attendant) {
		this.attendantDto = attendant;
	}

	public Map<RoomStatus, Integer> getRoomSummary() {
		return roomSummary;
	}

	public void setRoomSummary(Map<RoomStatus, Integer> roomSummary) {
		this.roomSummary = roomSummary;
	}

	public void addRoom(RoomStatus roomStatus) {
		Map<RoomStatus, Integer> roomSummary = getRoomSummary();
		Integer count = roomSummary.get(roomStatus);
		if (count == null) {
			roomSummary.put(roomStatus, 1);
		} else {
			roomSummary.remove(roomStatus);
			roomSummary.put(roomStatus, count++);
		}

	}
}
