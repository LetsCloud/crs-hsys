/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.Date;

import io.crs.hsys.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class RoomAvailabilityDto implements Dto {

	private Boolean available;

	private Date date;

	public RoomAvailabilityDto() {}

	public RoomAvailabilityDto(Boolean available, Date date) {
		this();
		this.available = available;
		this.date = date;
	}
	
	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
