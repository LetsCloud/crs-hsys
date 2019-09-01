/**
 * 
 */
package io.crs.hsys.shared.dto.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.crs.hsys.shared.cnst.CityTaxMethod;
import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.common.CurrencyDto;
import io.crs.hsys.shared.dto.hotel.RateElementDto;
import io.crs.hsys.shared.dto.hotel.RateCodeDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class RoomRateDto implements Dto {

	/**
	 * Mettől érvényes az árkód.
	 */
	private Date fromDate;

	/**
	 * Meddig érvényes az árkód.
	 */
	private Date toDate;

	/**
	 * Árkód.
	 */
	private RateCodeDto ratePlan;

	/**
	 * Ár.
	 */
	private Double rate;

	/**
	 * Valutanem.
	 */
	private CurrencyDto currency;

	/**
	 * IFA kalkulálás módja.
	 */
	private CityTaxMethod cityTaxMethod;

	/**
	 * Árkód összetevők.
	 */
	private List<RateElementDto> rateElements = new ArrayList<RateElementDto>();

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public RateCodeDto getRatePlan() {
		return ratePlan;
	}

	public void setRatePlan(RateCodeDto ratePlan) {
		this.ratePlan = ratePlan;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public CurrencyDto getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDto currency) {
		this.currency = currency;
	}

	public CityTaxMethod getCityTaxMethod() {
		return cityTaxMethod;
	}

	public void setCityTaxMethod(CityTaxMethod cityTaxMethod) {
		this.cityTaxMethod = cityTaxMethod;
	}

	public List<RateElementDto> getRateElements() {
		return rateElements;
	}

	public void setRateElements(List<RateElementDto> rateElements) {
		this.rateElements = rateElements;
	}

}