/**
 * 
 */
package io.crs.hsys.server.entity.reservation;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.server.entity.hotel.HotelChild;
import io.crs.hsys.shared.cnst.CityTaxMethod;
import io.crs.hsys.shared.cnst.RateCalcMethod;
import io.crs.hsys.shared.cnst.RateSubject;

/**
 * @author CR
 *
 */
@Entity
public class RatePlan extends HotelChild {

	/**
	 * Az árkód egyedi azonosítója.
	 */
	@Index
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
	private List<RateElement> rateElements = new ArrayList<RateElement>();

	public RatePlan() {
	}

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

	public List<RateElement> getRateElements() {
		return rateElements;
	}

	public void setRateElements(List<RateElement> rateElements) {
		this.rateElements = rateElements;
	}

	public static class Builder extends HotelChild.Builder<Builder> {

		private String code;

		private String description;

		private RateSubject subject;

		private Integer nights;

		private Integer paidNights;

		private CityTaxMethod cityTaxMethod;

		private RateCalcMethod elementCalcMethod;

		private List<RateValidity> rateValidities = new ArrayList<RateValidity>();

		private List<RateElement> rateElements = new ArrayList<RateElement>();

		public Builder() {
		}

		public RatePlan build() {
			return new RatePlan(this);
		}

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder subject(RateSubject subject) {
			this.subject = subject;
			return this;
		}

		public Builder nights(Integer nights) {
			this.nights = nights;
			return this;
		}

		public Builder paidNights(Integer paidNights) {
			this.paidNights = paidNights;
			return this;
		}

		public Builder cityTaxMethod(CityTaxMethod cityTaxMethod) {
			this.cityTaxMethod = cityTaxMethod;
			return this;
		}

		public Builder elementCalcMethod(RateCalcMethod elementCalcMethod) {
			this.elementCalcMethod = elementCalcMethod;
			return this;
		}

		public Builder rateValidities(List<RateValidity> rateValidities) {
			this.rateValidities = rateValidities;
			return this;
		}

		public Builder rateElements(List<RateElement> rateElements) {
			this.rateElements = rateElements;
			return this;
		}
	}

	protected RatePlan(Builder builder) {
		super(builder);
		this.cityTaxMethod = builder.cityTaxMethod;
		this.code = builder.code;
		this.description = builder.description;
		this.elementCalcMethod = builder.elementCalcMethod;
		this.nights = builder.nights;
		this.paidNights = builder.paidNights;
		this.rateElements = builder.rateElements;
		this.rateValidities = builder.rateValidities;
		this.subject = builder.subject;
	}

}
