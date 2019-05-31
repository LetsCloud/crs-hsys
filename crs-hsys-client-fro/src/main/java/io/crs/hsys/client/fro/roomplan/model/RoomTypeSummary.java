/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author robi
 *
 */
public class RoomTypeSummary {
	
	private String type;
	
	private String description;
	
	private List<RoomTypeDaySum> daySummary = new ArrayList<RoomTypeDaySum>();

	public RoomTypeSummary(String type, String description) {
		super();
		this.type = type;
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RoomTypeDaySum> getDaySummary() {
		return daySummary;
	}

	public void setDaySummary(List<RoomTypeDaySum> daySummary) {
		this.daySummary = daySummary;
	}

}
