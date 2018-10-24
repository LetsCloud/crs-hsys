/**
 * 
 */
package io.crs.hsys.server.entity.profile;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.server.entity.hotel.HotelChild;

/**
 * @author robi
 *
 */
@Entity
public class VipLevel extends HotelChild {
//	private static final Logger logger = LoggerFactory.getLogger(RoomType.class.getName());

	@Index
	private String code;

	private String description;

	private Integer sequence;

	private String color;

	private Boolean active;
	
	public VipLevel() {
//		logger.info("RoomType()");
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
