/**
 * 
 */
package io.crs.hsys.shared.dto.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class CurrencyDto extends AccountChildDto {

	/**
	 * A valutanem egyedi azonosítója.
	 */
	private String code;

	/**
	 * A valutanem megnevezése
	 */
	private String description;

	/**
	 * Tizedes megjelenítése
	 */
	private Integer displayedDecimals;

	/**
	 * Kerekítés pontosság (0; 0,1 ; 0,01)
	 */
	private Float roundingPrecision;

	private Float rateMultiplier;

	private List<ExchangeRateDto> exchangeRates = new ArrayList<ExchangeRateDto>();

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

	public Integer getDisplayedDecimals() {
		return displayedDecimals;
	}

	public void setDisplayedDecimals(Integer displayedDecimals) {
		this.displayedDecimals = displayedDecimals;
	}

	public Float getRoundingPrecision() {
		return roundingPrecision;
	}

	public void setRoundingPrecision(Float roundingPrecision) {
		this.roundingPrecision = roundingPrecision;
	}

	public Float getRateMultiplier() {
		return rateMultiplier;
	}

	public void setRateMultiplier(Float rateMultiplier) {
		this.rateMultiplier = rateMultiplier;
	}

	public List<ExchangeRateDto> getExchangeRates() {
		return exchangeRates;
	}

	public void setExchangeRates(List<ExchangeRateDto> exchangeRates) {
		this.exchangeRates = exchangeRates;
	}

}
