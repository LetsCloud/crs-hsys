/**
 * 
 */
package io.crs.hsys.server.entity.reservation;

import java.util.Date;

import com.googlecode.objectify.Ref;

import io.crs.hsys.server.entity.common.Currency;
import io.crs.hsys.server.entity.common.Service;

/**
 * @author CR
 *
 */
public class FixedCharge {

	/**
	 * Mettől terheljen.
	 */
	private Date fromDate;

	/**
	 * Meddig terheljen.
	 */
	private Date toDate;

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

	public FixedCharge() {
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

}