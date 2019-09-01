/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.shared.dto.Dto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RateByRoomTypeDto implements Dto {

	private RoomTypeDtor roomType;

	private List<RateDto> rates = new ArrayList<RateDto>();

	public RateByRoomTypeDto() {
	}

	public RoomTypeDtor getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomTypeDtor roomType) {
		this.roomType = roomType;
	}

	public List<RateDto> getRates() {
		return rates;
	}

	public void setRates(List<RateDto> rates) {
		this.rates = rates;
	}

}
