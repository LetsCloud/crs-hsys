/**
 * 
 */
package io.crs.hsys.shared.dto.rate.update;

import io.crs.hsys.shared.cnst.RatePriceType;
import io.crs.hsys.shared.cnst.RateUpdateOperation;
import io.crs.hsys.shared.dto.Dto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RoomRateOperationDto implements Dto {

	private RatePriceType type;

	private Double value;

	private RateUpdateOperation operation;

	public RoomRateOperationDto() {
	}

	public RoomRateOperationDto(RatePriceType type, Double value, RateUpdateOperation operation) {
		this();
		this.type = type;
		this.operation = operation;
		this.value = value;
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

	@Override
	public String toString() {
		return "RoomRateOperationDto [type=" + type + ", value=" + value + ", operation=" + operation + "]";
	}
	
}
