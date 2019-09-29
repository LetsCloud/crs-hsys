/**
 * 
 */
package io.crs.hsys.shared.dto.rate.update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.crs.hsys.shared.cnst.RatePriceType;
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

	private List<RoomRateOperationDto> roomRateOperations = new ArrayList<RoomRateOperationDto>();

	private RateRestrictionDto restriction;

	public RoomRateUpdateDto() {
		Arrays.asList(RatePriceType.values()).forEach(
				st -> roomRateOperations.add(new RoomRateOperationDto(st, 0d, RateUpdateOperation.SET_AMOUNT)));
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

	public List<RoomRateOperationDto> getRoomRateOperations() {
		return roomRateOperations;
	}

	public void setRoomRateOperations(List<RoomRateOperationDto> roomRateOperations) {
		this.roomRateOperations = roomRateOperations;
	}

	public RateRestrictionDto getRestriction() {
		return restriction;
	}

	public void setRestriction(RateRestrictionDto restriction) {
		this.restriction = restriction;
	}

}
