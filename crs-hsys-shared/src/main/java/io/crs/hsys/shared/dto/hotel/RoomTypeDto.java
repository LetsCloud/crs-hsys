/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.List;

import io.crs.hsys.shared.constans.InventoryType;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class RoomTypeDto extends HotelChildDto {

	private String code;

	private String name;

	private String description;

	private Integer floor;

	private Integer beds;

	private Integer xtrBeds;

	private Float cleaningFactor;

	private InventoryType inventoryType;

	private List<String> componentIds;

	private Boolean active;

	private Integer numberOfRooms = 0;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public List<String> getComponentIds() {
		return componentIds;
	}

	public void setComponentIds(List<String> componentIds) {
		this.componentIds = componentIds;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getBeds() {
		return beds;
	}

	public void setBeds(Integer beds) {
		this.beds = beds;
	}

	public Integer getXtrBeds() {
		return xtrBeds;
	}

	public void setXtrBeds(Integer xtrBeds) {
		this.xtrBeds = xtrBeds;
	}

	public Float getCleaningFactor() {
		return cleaningFactor;
	}

	public void setCleaningFactor(Float cleaningFactor) {
		this.cleaningFactor = cleaningFactor;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	@Override
	public String toString() {
		String ret = "RoomTypeDto:{" + super.toString() + ", code=" + code + ", name=" + name + ", description="
				+ description + ", salesType=" + inventoryType +"}";
		return ret;
	}

}
