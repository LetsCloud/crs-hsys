/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.model;

import java.util.Date;

/**
 * @author robi
 *
 */
public class HeaderData {
	private Integer unassined;
	private Date date;
	private Float occupancy;
	
	public HeaderData(Integer unassined, Date date, Float occupancy) {
		this.unassined = unassined;
		this.date = date;
		this.occupancy = occupancy;
	}

	public Integer getUnassined() {
		return unassined;
	}

	public void setUnassined(Integer unassined) {
		this.unassined = unassined;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(Float occupancy) {
		this.occupancy = occupancy;
	}
	
}
