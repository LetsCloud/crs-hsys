/**
 * 
 */
package io.crs.hsys.server.entity.reservation;

import com.googlecode.objectify.Ref;

import io.crs.hsys.server.entity.common.Currency;
import io.crs.hsys.server.entity.common.Service;
import io.crs.hsys.shared.cnst.RateBase;
import io.crs.hsys.shared.cnst.RatePostingRhythm;

/**
 * Árkód összetevő.
 * 
 * @author CR
 *
 */
public class RateElement {

	/**
	 * Szolgáltatáskód hivatkozás.
	 */
	private Ref<Service> serviceRef;

	/**
	 * Egységár.
	 */
	private Double price;

	/**
	 * Egységár valutaneme (hivatkozás).
	 */
	private Ref<Currency> currencyRef;

	/**
	 * Terhelendő mennyiség.
	 */
	private Integer qty;

	/**
	 * Árkalkulálás módja.
	 */
	private RateBase rateBase;

	/**
	 * Terhelés ritmusa.
	 */
	private RatePostingRhythm postingRhythm;

	/**
	 * Igénybevétel következő nap.
	 */
	private Boolean useNextDay;

	/**
	 * Szállásba bújtatva.
	 */
	private Boolean arrangement;

	public Ref<Service> getServiceRef() {
		return serviceRef;
	}

	public void setServiceRef(Ref<Service> serviceRef) {
		this.serviceRef = serviceRef;
	}

	public Service getService() {
		return serviceRef.get();
	}

	public void setService(Service service) {
		this.serviceRef = Ref.create(service);
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public RateBase getRateBase() {
		return rateBase;
	}

	public void setRateBase(RateBase rateBase) {
		this.rateBase = rateBase;
	}

	public RatePostingRhythm getPostingRhythm() {
		return postingRhythm;
	}

	public void setPostingRhythm(RatePostingRhythm chargingRhythm) {
		this.postingRhythm = chargingRhythm;
	}

	public Boolean getUseNextDay() {
		return useNextDay;
	}

	public void setUseNextDay(Boolean useNextDay) {
		this.useNextDay = useNextDay;
	}

	public Boolean getArrangement() {
		return arrangement;
	}

	public void setArrangement(Boolean arrangement) {
		this.arrangement = arrangement;
	}
}
