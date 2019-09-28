/**
 * 
 */
package io.crs.hsys.shared.dto.rate.query;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.crs.hsys.shared.cnst.RatePriceType;
import io.crs.hsys.shared.dto.common.CurrencyDtor;
import io.crs.hsys.shared.dto.rate.RateRestrictionDto;

/**
 * @author robi
 *
 */
public class RateByDateDto {

	/**
	 * Meddig érvényes az árkód.
	 */
	private Date date;

	private CurrencyDtor currency;

	private RateRestrictionDto restriction;

	/**
	 * Árak.
	 */
	private Map<RatePriceType, Double> rates = new HashMap<RatePriceType, Double>();

	public RateByDateDto() {
	}

	public RateByDateDto(Date date, CurrencyDtor currency, Map<RatePriceType, Double> rates,
			RateRestrictionDto restriction) {
		this();
		this.date = date;
		this.currency = currency;
		this.rates = rates;
		this.restriction = restriction;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CurrencyDtor getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDtor currency) {
		this.currency = currency;
	}

	public Map<RatePriceType, Double> getRates() {
		return rates;
	}

	public void setRates(Map<RatePriceType, Double> rates) {
		this.rates = rates;
	}

	public RateRestrictionDto getRestriction() {
		return restriction;
	}

	public void setRestriction(RateRestrictionDto restriction) {
		this.restriction = restriction;
	}

}
