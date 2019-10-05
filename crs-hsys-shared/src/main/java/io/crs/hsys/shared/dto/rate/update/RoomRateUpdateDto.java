/**
 * 
 */
package io.crs.hsys.shared.dto.rate.update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.crs.hsys.shared.cnst.RatePriceType;
import io.crs.hsys.shared.cnst.RateRestrictionType;
import io.crs.hsys.shared.cnst.RateUpdateOperation;
import io.crs.hsys.shared.dto.hotel.HotelChildDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.dto.rate.RateCodeDtor;
import io.crs.hsys.shared.dto.rate.RateRestrictionDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RoomRateUpdateDto extends HotelChildDto {

	private RateCodeDtor rateCode;

	private List<RoomTypeDtor> roomTypes = new ArrayList<RoomTypeDtor>();

	private List<RoomRateOperationDto> operations = new ArrayList<RoomRateOperationDto>();

	private List<RateRestrictionDto> restrictions = new ArrayList<RateRestrictionDto>();

	public RoomRateUpdateDto() {
		Arrays.asList(RatePriceType.values())
				.forEach(st -> operations.add(new RoomRateOperationDto(st, 0d, RateUpdateOperation.SET_AMOUNT)));

		Arrays.asList(RateRestrictionType.values()).forEach(st -> restrictions.add(new RateRestrictionDto(st, 0)));
	}

	public RateCodeDtor getRateCode() {
		return rateCode;
	}

	public void setRateCode(RateCodeDtor rateCode) {
		this.rateCode = rateCode;
	}

	public List<RoomTypeDtor> getRoomTypes() {
		return roomTypes;
	}

	public void setRoomTypes(List<RoomTypeDtor> roomTypes) {
		this.roomTypes = roomTypes;
	}

	public List<RoomRateOperationDto> getOperations() {
		return operations;
	}

	public void setOperations(List<RoomRateOperationDto> operations) {
		this.operations = operations;
	}

	public List<RateRestrictionDto> getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(List<RateRestrictionDto> restrictions) {
		this.restrictions = restrictions;
	}

}
