/**
 * 
 */
package io.crs.hsys.shared.dto.common;

import java.util.Date;

import io.crs.hsys.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class ExchangeRateDto implements Dto {

	private Date validFrom;

	private Float currencySelling;

	private Float currencyBuying;

	private Float foreignSelling;

	private Float foreignBuying;

	private Float middle;

	private Float internal;

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Float getCurrencySelling() {
		return currencySelling;
	}

	public void setCurrencySelling(Float currencySelling) {
		this.currencySelling = currencySelling;
	}

	public Float getCurrencyBuying() {
		return currencyBuying;
	}

	public void setCurrencyBuying(Float currencyBuying) {
		this.currencyBuying = currencyBuying;
	}

	public Float getForeignSelling() {
		return foreignSelling;
	}

	public void setForeignSelling(Float foreignSelling) {
		this.foreignSelling = foreignSelling;
	}

	public Float getForeignBuying() {
		return foreignBuying;
	}

	public void setForeignBuying(Float foreignBuying) {
		this.foreignBuying = foreignBuying;
	}

	public Float getMiddle() {
		return middle;
	}

	public void setMiddle(Float middle) {
		this.middle = middle;
	}

	public Float getInternal() {
		return internal;
	}

	public void setInternal(Float internal) {
		this.internal = internal;
	}
}
