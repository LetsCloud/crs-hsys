/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.crs.hsys.shared.cnst.RatePriceType;
import io.crs.hsys.shared.cnst.RateRestrictionType;
import io.crs.hsys.shared.dto.Dto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RateDto implements Dto {

	/**
	 * Mettől érvényes az árkód.
	 */
	private Date fromDate;

	/**
	 * Meddig érvényes az árkód.
	 */
	private Date toDate;

	private Map<RateRestrictionType, Object> restrictions = new HashMap<RateRestrictionType, Object>();

	/**
	 * Árak.
	 */
	private Map<RatePriceType, Double> rates = new HashMap<RatePriceType, Double>();

	public RateDto() {
	}

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

	public Map<RateRestrictionType, Object> getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(Map<RateRestrictionType, Object> restrictions) {
		this.restrictions = restrictions;
	}

	public Map<RatePriceType, Double> getRates() {
		return rates;
	}

	public void setRates(Map<RatePriceType, Double> rates) {
		this.rates = rates;
	}

	public static Builder builder() {
		return new RateDto.Builder();
	}

	/**
	 * 
	 * @author CR
	 *
	 */
	public static class Builder {

		private RateDto instance = new RateDto();

		public Builder() {
		}

		public RateDto build() {
			return instance;
		}

		public Builder fromDate(Date fromDate) {
			instance.setFromDate(fromDate);
			return this;
		}

		public Builder toDate(Date toDate) {
			instance.setToDate(toDate);
			return this;
		}

		public Builder rates(Map<RatePriceType, Double> rates) {
			instance.setRates(rates);
			return this;
		}
	}
}
