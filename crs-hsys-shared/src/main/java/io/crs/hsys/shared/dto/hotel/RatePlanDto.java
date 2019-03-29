/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.shared.cnst.CityTaxMethod;
import io.crs.hsys.shared.cnst.RateCalcMethod;
import io.crs.hsys.shared.cnst.RateSubject;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class RatePlanDto extends HotelChildDto {

	/**
	 * Az árkód egyedi azonosítója.
	 */
	private String code;

	/**
	 * Az árkód megnevezése.
	 */
	private String description;

	/**
	 * Az árkód típusa: Szoba, vendég.
	 */
	private RateSubject subject;

	/**
	 * Éjszakák száma.
	 */
	private Integer nights;

	/**
	 * Fizetendő éjszakák száma.
	 */
	private Integer paidNights;

	/**
	 * IFA kalkulálás módja.
	 */
	private CityTaxMethod cityTaxMethod;

	/**
	 * Az összetevők kalkulálásának módja.
	 */
	private RateCalcMethod elementCalcMethod;

	/**
	 * Árkód érvényességek.
	 */
	private List<RateValidity> rateValidities = new ArrayList<RateValidity>();

	/**
	 * Árkód összetevők.
	 */
	private List<RateElementDto> rateElements = new ArrayList<RateElementDto>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RateSubject getSubject() {
		return subject;
	}

	public void setSubject(RateSubject subject) {
		this.subject = subject;
	}

	public Integer getNights() {
		return nights;
	}

	public void setNights(Integer nights) {
		this.nights = nights;
	}

	public Integer getPaidNights() {
		return paidNights;
	}

	public void setPaidNights(Integer paidNights) {
		this.paidNights = paidNights;
	}

	public CityTaxMethod getCityTaxMethod() {
		return cityTaxMethod;
	}

	public void setCityTaxMethod(CityTaxMethod cityTaxMethod) {
		this.cityTaxMethod = cityTaxMethod;
	}

	public RateCalcMethod getElementCalcMethod() {
		return elementCalcMethod;
	}

	public void setElementCalcMethod(RateCalcMethod elementCalcMethod) {
		this.elementCalcMethod = elementCalcMethod;
	}

	public List<RateValidity> getRateValidities() {
		return rateValidities;
	}

	public void setRateValidities(List<RateValidity> rateValidities) {
		this.rateValidities = rateValidities;
	}

	public List<RateElementDto> getRateElements() {
		return rateElements;
	}

	public void setRateElements(List<RateElementDto> rateElements) {
		this.rateElements = rateElements;
	}
}
