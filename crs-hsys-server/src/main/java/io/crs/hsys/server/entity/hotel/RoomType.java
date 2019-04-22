/**
 * 
 */
package io.crs.hsys.server.entity.hotel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.shared.cnst.InventoryType;

/**
 * @author CR
 *
 */
@Entity
public class RoomType extends HotelChild {
	private static final Logger logger = LoggerFactory.getLogger(RoomType.class.getName());

	@Index
	private String code;

	private String name;

	private String description;

	private Integer floor;

	private Integer beds;

	private Integer xtrBeds;

	private Float cleaningFactor;

	@Index
	private InventoryType inventoryType;

	private List<Ref<RoomType>> components;

	@Index
	private Boolean active;

	@Ignore
	private Integer numberOfRooms;

	public RoomType() {
		logger.info("RoomType()");
	}

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

	public List<Ref<RoomType>> getComponents() {
		return components;
	}

	public void setComponents(List<Ref<RoomType>> components) {
		this.components = components;
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
}
