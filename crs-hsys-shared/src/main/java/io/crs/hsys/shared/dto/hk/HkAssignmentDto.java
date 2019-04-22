/**
 * 
 */
package io.crs.hsys.shared.dto.hk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.crs.hsys.shared.cnst.AssignmentStatus;
import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.dto.hotel.HotelChildDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.reservation.ReservationDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class HkAssignmentDto extends HotelChildDto {

	private Date businessDate;

	private AssignmentStatus status;

	private RoomDto roomDto;
	
	private AppUserDto attendantDto;
	
	private AppUserDto inspectorDto;
	
	private CleanTypeDto cleanTypeDto;
	
	private String notice;
	
	private List<ReservationDto> reservationDtos = new ArrayList<ReservationDto>();

	public RoomDto getRoomDto() {
		return roomDto;
	}

	public void setRoomDto(RoomDto roomDto) {
		this.roomDto = roomDto;
	}

	public AppUserDto getAttendantDto() {
		return attendantDto;
	}

	public void setAttendantDto(AppUserDto attendantDto) {
		this.attendantDto = attendantDto;
	}

	public AppUserDto getInspectorDto() {
		return inspectorDto;
	}

	public void setInspectorDto(AppUserDto inspectorDto) {
		this.inspectorDto = inspectorDto;
	}

	public CleanTypeDto getCleanTypeDto() {
		return cleanTypeDto;
	}

	public void setCleanTypeDto(CleanTypeDto cleanTypeDto) {
		this.cleanTypeDto = cleanTypeDto;
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public AssignmentStatus getStatus() {
		return status;
	}

	public void setStatus(AssignmentStatus status) {
		this.status = status;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public List<ReservationDto> getReservationDtos() {
		return reservationDtos;
	}

	public void setReservationDtos(List<ReservationDto> reservationDtos) {
		this.reservationDtos = reservationDtos;
	}

	public void addReservationDto(ReservationDto reservationDto) {
		this.reservationDtos.add(reservationDto);
	}
	
}
