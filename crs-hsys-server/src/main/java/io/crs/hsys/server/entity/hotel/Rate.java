/**
 * 
 */
package io.crs.hsys.server.entity.hotel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.crs.hsys.shared.cnst.RatePriceType;
import io.crs.hsys.shared.cnst.RateRestrictionType;
import io.crs.hsys.shared.dto.hotel.RateDto;

/**
 * @author CR
 *
 */
public class Rate {

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

	public Rate() {
	}

	public Rate(Date fromDate, Date toDate, Map<RateRestrictionType, Object> restrictions,
			Map<RatePriceType, Double> rates) {
		this();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.restrictions = restrictions;
		this.rates = rates;
	}

	public Rate(RateDto dto) {
		this(dto.getFromDate(), dto.getToDate(), dto.getRestrictions(), dto.getRates());
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
		return new Rate.Builder();
	}

	/**
	 * 
	 * @author CR
	 *
	 */
	public static class Builder {

		private Rate instance = new Rate();

		public Builder() {
		}

		public Rate build() {
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
