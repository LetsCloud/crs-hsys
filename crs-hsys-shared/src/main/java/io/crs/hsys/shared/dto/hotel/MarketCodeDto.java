/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class MarketCodeDto extends HotelChildDto {

	private String code;
	
	private String description;

	private MarketGroupDto group;
	
	private Integer sequence;
	
	private String color;
	
	private Boolean active;

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

	public MarketGroupDto getGroup() {
		return group;
	}

	public void setGroup(MarketGroupDto group) {
		this.group = group;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
