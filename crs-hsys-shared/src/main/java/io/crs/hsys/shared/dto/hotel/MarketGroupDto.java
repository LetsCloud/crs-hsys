/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class MarketGroupDto extends HotelChildDto {

	private String code;
	
	private String description;
	
	private Integer sequence;
	
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

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
