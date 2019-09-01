/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.Date;
import java.util.List;

/**
 * @author robi
 *
 */
public class RateQueryDto {

	private Date fromDate;
	private Integer days;
	private List<String> rateCodes;
	private List<String> roomTypes;

	public RateQueryDto() {
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public List<String> getRateCodes() {
		return rateCodes;
	}

	public void setRateCodes(List<String> rateCodes) {
		this.rateCodes = rateCodes;
	}

	public List<String> getRoomTypes() {
		return roomTypes;
	}

	public void setRoomTypes(List<String> roomTypes) {
		this.roomTypes = roomTypes;
	}

}
