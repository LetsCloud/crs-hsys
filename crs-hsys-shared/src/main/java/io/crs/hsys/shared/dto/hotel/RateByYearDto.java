/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.shared.dto.common.CurrencyDtor;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RateByYearDto extends HotelChildDto {

	/**
	 * Árkód.
	 */
	private RateCodeDtor rateCode;

	/**
	 * Év
	 */
	private Integer year;

	/**
	 * Valutanem.
	 */
	private CurrencyDtor currency;

	/**
	 * 
	 */
	private List<RateByRoomTypeDto> ratesByRoomType = new ArrayList<RateByRoomTypeDto>();

	public RateByYearDto() {
	}

	public RateCodeDtor getRateCode() {
		return rateCode;
	}

	public void setRateCode(RateCodeDtor rateCode) {
		this.rateCode = rateCode;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public CurrencyDtor getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDtor currency) {
		this.currency = currency;
	}

	public List<RateByRoomTypeDto> getRatesByRoomType() {
		return ratesByRoomType;
	}

	public void setRatesByRoomType(List<RateByRoomTypeDto> ratesByRoomType) {
		this.ratesByRoomType = ratesByRoomType;
	}

}
