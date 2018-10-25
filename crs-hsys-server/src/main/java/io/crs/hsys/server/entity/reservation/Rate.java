/**
 * 
 */
package io.crs.hsys.server.entity.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Ref;

import io.crs.hsys.server.entity.common.Currency;
import io.crs.hsys.shared.constans.CityTaxMethod;

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

	/**
	 * Árkód.
	 */
	private Ref<RatePlan> ratePlanRef;

	/**
	 * Ár.
	 */
	private Double rate;

	/**
	 * Valutanem.
	 */
	private Ref<Currency> currencyRef;

	/**
	 * IFA kalkulálás módja.
	 */
	private CityTaxMethod cityTaxMethod;

	/**
	 * Árkód összetevők.
	 */
	private List<RateElement> rateElements = new ArrayList<RateElement>();

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

	public Ref<RatePlan> getRatePlanRef() {
		return ratePlanRef;
	}

	public void setRatePlanRef(Ref<RatePlan> ratePlanRef) {
		this.ratePlanRef = ratePlanRef;
	}

	public RatePlan getRatePlan() {
		return ratePlanRef.get();
	}

	public void setRatePlan(RatePlan ratePlan) {
		this.ratePlanRef = Ref.create(ratePlan);
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Ref<Currency> getCurrencyRef() {
		return currencyRef;
	}

	public void setCurrencyRef(Ref<Currency> currencyRef) {
		this.currencyRef = currencyRef;
	}

	public Currency getCurrency() {
		return currencyRef.get();
	}

	public void setCurrency(Currency currency) {
		this.currencyRef = Ref.create(currency);
	}

	public CityTaxMethod getCityTaxMethod() {
		return cityTaxMethod;
	}

	public void setCityTaxMethod(CityTaxMethod cityTaxMethod) {
		this.cityTaxMethod = cityTaxMethod;
	}

	public List<RateElement> getRateElements() {
		return rateElements;
	}

	public void setRateElements(List<RateElement> rateElements) {
		this.rateElements = rateElements;
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

		public Builder cityTaxMethod(CityTaxMethod cityTaxMethod) {
			instance.setCityTaxMethod(cityTaxMethod);
			return this;
		}

		public Builder currency(Currency currency) {
			instance.setCurrency(currency);
			return this;
		}

		public Builder fromDate(Date fromDate) {
			instance.setFromDate(fromDate);
			return this;
		}

		public Builder rate(Double rate) {
			instance.setRate(rate);
			return this;
		}

		public Builder rateElements(List<RateElement> rateElements) {
			instance.setRateElements(rateElements);
			return this;
		}

		public Builder ratePlan(RatePlan ratePlan) {
			instance.setRatePlan(ratePlan);
			return this;
		}

		public Builder toDate(Date toDate) {
			instance.setToDate(toDate);
			return this;
		}
	}
}
