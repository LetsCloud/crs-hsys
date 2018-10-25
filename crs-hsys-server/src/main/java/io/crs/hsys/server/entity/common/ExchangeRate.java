/**
 * 
 */
package io.crs.hsys.server.entity.common;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import io.crs.hsys.shared.dto.common.ExchangeRateDto;

/**
 * @author CR
 *
 */
public class ExchangeRate {

	private Date validFrom;

	private Float currencySelling;

	private Float currencyBuying;

	private Float foreignSelling;

	private Float foreignBuying;

	private Float middle;

	private Float internal;

	public ExchangeRate() {
	}

	public ExchangeRate(ExchangeRateDto dto) {
		super();
		this.setCurrencyBuying(dto.getCurrencyBuying());
		this.setCurrencySelling(dto.getCurrencySelling());
		this.setForeignBuying(dto.getForeignBuying());
		this.setForeignSelling(dto.getForeignSelling());
		this.setInternal(dto.getInternal());
		this.setMiddle(dto.getMiddle());
		this.setValidFrom(dto.getValidFrom());
	}

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

	public static Builder builder() {
		return new ExchangeRate.Builder();
	}

	public static class Builder {

		private ExchangeRate instance = new ExchangeRate();

		public Builder() {
		}

		public ExchangeRate build() {
			return instance;
		}

		public Builder currencyBuying(Float currencyBuying) {
			instance.currencyBuying = currencyBuying;
			return this;
		}

		public Builder currencySelling(Float currencySelling) {
			instance.currencySelling = currencySelling;
			return this;
		}

		public Builder foreignBuying(Float foreignBuying) {
			instance.foreignBuying = foreignBuying;
			return this;
		}

		public Builder foreignSelling(Float foreignSelling) {
			instance.foreignSelling = foreignSelling;
			return this;
		}

		public Builder internal(Float internal) {
			instance.internal = internal;
			return this;
		}

		public Builder middle(Float middle) {
			instance.middle = middle;
			return this;
		}

		public Builder validFrom(Date validFrom) {
			instance.validFrom = validFrom;
			return this;
		}
	}

	/**
	 * DTO létrehozása entitás alapján
	 * 
	 * @param entity
	 * @return
	 */
	public static ExchangeRateDto createDto(ExchangeRate entity) {
		ExchangeRateDto dto = new ExchangeRateDto();
		dto = entity.updateDto(dto);
		return dto;
	}

	/**
	 * DTO módosítása entitás alapján
	 */
	public ExchangeRateDto updateDto(ExchangeRateDto dto) {
		dto.setCurrencyBuying(this.getCurrencyBuying());
		dto.setCurrencySelling(this.getCurrencySelling());
		dto.setForeignBuying(this.getForeignBuying());
		dto.setForeignSelling(this.getForeignSelling());
		dto.setInternal(this.getInternal());
		dto.setMiddle(this.getMiddle());
		dto.setValidFrom(this.getValidFrom());
		return dto;
	}

	/**
	 * DTO lista létrehozása entitás listából
	 * 
	 * @param entities
	 * @return
	 */
	public static List<ExchangeRateDto> createDtos(List<ExchangeRate> entities) {
		List<ExchangeRateDto> dtos = new ArrayList<ExchangeRateDto>();

		for (ExchangeRate entity : entities)
			dtos.add(createDto(entity));

		return dtos;
	}

	/**
	 * Entitás lista létrehozása DTO listából
	 * 
	 * @param dtos
	 * @return
	 */
	public static List<ExchangeRate> createList(List<ExchangeRateDto> dtos) {
		List<ExchangeRate> entities = new ArrayList<ExchangeRate>();

		for (ExchangeRateDto dto : dtos)
			entities.add(new ExchangeRate(dto));

		return entities;
	}

	/**
	 * Érvényesség dátumára rendező Comparatort ad vissza
	 * 
	 */
	public static Comparator<ExchangeRate> ORDER_BY_VALIDFROM = new Comparator<ExchangeRate>() {
		public int compare(ExchangeRate one, ExchangeRate other) {
			return one.getValidFrom().compareTo(other.getValidFrom());
		}
	};

	/**
	 * A átadott árfolyam listából a megadott napon érvény árfolyamokat adja
	 * vissza
	 * 
	 * @param exchangeRates
	 * @param date
	 * @return
	 */
	public static ExchangeRate getValidCityTax(List<ExchangeRate> exchangeRates, final Date date) {
		return null;
	}

}
