/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author robi
 *
 */
public class RateLineDto {
	private RateCodeDtor rateCode;
	private RoomTypeDtor roomType;
	private List<RateByDateDto> ratesByDate = new ArrayList<RateByDateDto>();

	public RateLineDto() {
	}

	public RateLineDto(RateCodeDtor rateCode, RoomTypeDtor roomType, List<RateByDateDto> ratesByDate) {
		this();
		this.rateCode = rateCode;
		this.roomType = roomType;
		this.ratesByDate = ratesByDate;
	}

	public RateCodeDtor getRateCode() {
		return rateCode;
	}

	public void setRateCode(RateCodeDtor rateCode) {
		this.rateCode = rateCode;
	}

	public RoomTypeDtor getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomTypeDtor roomType) {
		this.roomType = roomType;
	}

	public List<RateByDateDto> getRatesByDate() {
		return ratesByDate;
	}

	public void setRatesByDate(List<RateByDateDto> ratesByDate) {
		this.ratesByDate = ratesByDate;
	}

}
