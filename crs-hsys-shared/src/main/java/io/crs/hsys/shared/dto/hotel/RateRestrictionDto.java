/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import io.crs.hsys.shared.cnst.RateRestrictionType;
import io.crs.hsys.shared.dto.Dto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RateRestrictionDto implements Dto {

	private RateRestrictionType type;

	private Integer value;

	public RateRestrictionDto() {
		this.type = RateRestrictionType.NORST;
	}

	public RateRestrictionDto(RateRestrictionType type) {
		this();
		this.type = type;
	}

	public RateRestrictionDto(RateRestrictionType type, Integer value) {
		this(type);
		this.value = value;
	}

	public RateRestrictionType getType() {
		return type;
	}

	public void setType(RateRestrictionType type) {
		this.type = type;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
