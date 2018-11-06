/**
 * 
 */
package io.crs.hsys.shared.dto.reservation;

import java.util.Date;

import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.common.CurrencyDto;
import io.crs.hsys.shared.dto.common.ServiceDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class FixedChargeDto implements Dto {

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
	private ServiceDto service;

	/**
	 * Egységár.
	 */
	private Double price;

	/**
	 * Egységár valutaneme (hivatkozás).
	 */
	private CurrencyDto currency;

	/**
	 * Terhelendő mennyiség.
	 */
	private Integer qty;

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

	public ServiceDto getService() {
		return service;
	}

	public void setService(ServiceDto service) {
		this.service = service;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public CurrencyDto getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDto currency) {
		this.currency = currency;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

}
