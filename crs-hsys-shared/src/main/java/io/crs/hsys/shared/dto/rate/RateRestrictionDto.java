/**
 * 
 */
package io.crs.hsys.shared.dto.rate;

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

	private Boolean applied;

	public RateRestrictionDto() {
		this.applied = false;
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

	public Boolean getApplied() {
		return applied;
	}

	public void setApplied(Boolean applied) {
		this.applied = applied;
	}

	@Override
	public String toString() {
		return "RateRestrictionDto [type=" + type + ", value=" + value + ", applied=" + applied + "]";
	}

}
