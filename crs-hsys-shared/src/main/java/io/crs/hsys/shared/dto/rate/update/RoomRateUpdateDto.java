/**
 * 
 */
package io.crs.hsys.shared.dto.rate.update;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.crs.hsys.shared.dto.hotel.HotelChildDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.dto.rate.RateCodeDtor;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RoomRateUpdateDto extends HotelChildDto {

	private RateCodeDtor rateCode;

	private List<RoomTypeDtor> roomTypes = new ArrayList<RoomTypeDtor>();

	private Map<Date, RoomRateOperationDto> roomRateOperations = new HashMap<Date, RoomRateOperationDto>();

	public RoomRateUpdateDto() {
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

	public Map<Date, RoomRateOperationDto> getRoomRateOperations() {
		return roomRateOperations;
	}

	public void setRoomRateOperations(Map<Date, RoomRateOperationDto> roomRateOperations) {
		this.roomRateOperations = roomRateOperations;
	}

}
