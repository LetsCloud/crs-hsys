/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.Date;

import io.crs.hsys.shared.cnst.OooReturnWhen;
import io.crs.hsys.shared.cnst.RoomStatus;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class OooRoomDto extends HotelChildDto {

	private RoomDto room;
	private Date fromDate;
	private Date toDate;
	private RoomStatus returnAs;
	private OooReturnWhen returnWhen;
	private String remarks;

	public OooRoomDto() {
	}

	public RoomDto getRoom() {
		return room;
	}

	public void setRoom(RoomDto room) {
		this.room = room;
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

}
