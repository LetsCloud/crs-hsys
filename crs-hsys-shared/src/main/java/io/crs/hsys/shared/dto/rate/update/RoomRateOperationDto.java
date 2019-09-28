/**
 * 
 */
package io.crs.hsys.shared.dto.rate.update;

import io.crs.hsys.shared.cnst.RatePriceType;
import io.crs.hsys.shared.cnst.RateUpdateOperand;
import io.crs.hsys.shared.cnst.RateUpdateOperation;
import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.rate.RateRestrictionDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RoomRateOperationDto implements Dto {

	private RatePriceType type;

	private RateUpdateOperation operation;

	private Double value;

	private RateUpdateOperand operand;

	private RateRestrictionDto restriction;

	public RoomRateOperationDto() {
	}

	public RatePriceType getType() {
		return type;
	}

	public void setType(RatePriceType type) {
		this.type = type;
	}

	public RateUpdateOperation getOperation() {
		return operation;
	}

	public void setOperation(RateUpdateOperation operation) {
		this.operation = operation;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public RateUpdateOperand getOperand() {
		return operand;
	}

	public void setOperand(RateUpdateOperand operand) {
		this.operand = operand;
	}

	public RateRestrictionDto getRestriction() {
		return restriction;
	}

	public void setRestriction(RateRestrictionDto restriction) {
		this.restriction = restriction;
	}

}
