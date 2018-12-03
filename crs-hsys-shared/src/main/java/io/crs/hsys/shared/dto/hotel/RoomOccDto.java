/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import io.crs.hsys.shared.constans.OccStatus;
import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.hk.GuestNumber;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RoomOccDto implements Dto {

	private OccStatus status;

	private GuestNumber guestNumber;

	private String notice;

	public RoomOccDto() {
	}

	public RoomOccDto(OccStatus status, GuestNumber guestNumber, String notice) {
		this();
		this.status = status;
		this.guestNumber = guestNumber;
		this.notice = notice;
	}

	public OccStatus getStatus() {
		return status;
	}

	public void setStatus(OccStatus status) {
		this.status = status;
	}

	public GuestNumber getGuestNumber() {
		return guestNumber;
	}

	public void setGuestNumber(GuestNumber guestNumber) {
		this.guestNumber = guestNumber;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

}
