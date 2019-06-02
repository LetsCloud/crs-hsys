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
public class RoomTypeRowData {
	
	private String type;
	
	private String description;
	
	private int numberOfRooms;

	private List<RoomCellData> rooms = new ArrayList<RoomCellData>();
	
	private List<RoomTypeDayCellData> daySummary = new ArrayList<RoomTypeDayCellData>();

	public RoomTypeRowData(String type, String description, int numberOfRooms, List<RoomCellData> rooms) {
		this.type = type;
		this.description = description;
		this.numberOfRooms = numberOfRooms;
		this.rooms = rooms;
	}

	public RoomTypeRowData(String type, String description, int numberOfRooms, List<RoomCellData> rooms, List<RoomTypeDayCellData> daySummary) {
		this(type, description, numberOfRooms, rooms);
		this.daySummary = daySummary;
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

	public List<RoomTypeDayCellData> getDaySummary() {
		return daySummary;
	}

	public void setDaySummary(List<RoomTypeDayCellData> daySummary) {
		this.daySummary = daySummary;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public List<RoomCellData> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomCellData> rooms) {
		this.rooms = rooms;
	}
	
}
