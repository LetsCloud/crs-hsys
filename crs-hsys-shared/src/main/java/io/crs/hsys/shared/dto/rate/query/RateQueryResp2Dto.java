package io.crs.hsys.shared.dto.rate.query;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.dto.rate.RateCodeDtor;

public class RateQueryResp2Dto {
	private RateCodeDtor rateCode;
	private RoomTypeDtor roomType;
	private List<RateByDateDto> ratesByDate = new ArrayList<RateByDateDto>();

	public RateQueryResp2Dto() {
	}

	public RateQueryResp2Dto(RateCodeDtor rateCode, RoomTypeDtor roomType, List<RateByDateDto> ratesByDate) {
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
