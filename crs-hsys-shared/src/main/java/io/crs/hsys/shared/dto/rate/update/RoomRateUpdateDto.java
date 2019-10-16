/**
 * 
 */
package io.crs.hsys.shared.dto.rate.update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

	private Date fromDate;
	
	private Date toDate;
	
	private Boolean day1 = true;
	private Boolean day2 = true;
	private Boolean day3 = true;
	private Boolean day4 = true;
	private Boolean day5 = true;
	private Boolean day6 = true;
	private Boolean day7 = true;
	
	private List<RoomRateOperationDto> operations = new ArrayList<RoomRateOperationDto>();

	private List<RateRestrictionDto> restrictions = new ArrayList<RateRestrictionDto>();

	public RoomRateUpdateDto() {
		Arrays.asList(RatePriceType.values())
				.forEach(st -> operations.add(new RoomRateOperationDto(st, 0d, RateUpdateOperation.NO_CHANGE)));

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

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromtDate) {
		this.fromDate = fromtDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Boolean getDay1() {
		return day1;
	}

	public void setDay1(Boolean day1) {
		this.day1 = day1;
	}

	public Boolean getDay2() {
		return day2;
	}

	public void setDay2(Boolean day2) {
		this.day2 = day2;
	}

	public Boolean getDay3() {
		return day3;
	}

	public void setDay3(Boolean day3) {
		this.day3 = day3;
	}

	public Boolean getDay4() {
		return day4;
	}

	public void setDay4(Boolean day4) {
		this.day4 = day4;
	}

	public Boolean getDay5() {
		return day5;
	}

	public void setDay5(Boolean day5) {
		this.day5 = day5;
	}

	public Boolean getDay6() {
		return day6;
	}

	public void setDay6(Boolean day6) {
		this.day6 = day6;
	}

	public Boolean getDay7() {
		return day7;
	}

	public void setDay7(Boolean day7) {
		this.day7 = day7;
	}

	@Override
	public String toString() {
		return "RoomRateUpdateDto [rateCode=" + rateCode + ", roomTypes=" + roomTypes + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", day1=" + day1 + ", day2=" + day2 + ", day3=" + day3 + ", day4=" + day4
				+ ", day5=" + day5 + ", day6=" + day6 + ", day7=" + day7 + ", operations=" + operations
				+ ", restrictions=" + restrictions + "]";
	}

}
