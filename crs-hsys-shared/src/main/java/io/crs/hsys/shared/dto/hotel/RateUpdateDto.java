/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.common.CurrencyDtor;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RateUpdateDto implements Dto {

	private RateCodeDtor rateCode;

	private CurrencyDtor currency;

	private List<RoomTypeDtor> roomTypes = new ArrayList<RoomTypeDtor>();

	private List<RateDto> rates = new ArrayList<RateDto>();

	public RateUpdateDto() {
	}

	public RateCodeDtor getRateCode() {
		return rateCode;
	}

	public void setRateCode(RateCodeDtor rateCode) {
		this.rateCode = rateCode;
	}

	public CurrencyDtor getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDtor currency) {
		this.currency = currency;
	}

	public List<RoomTypeDtor> getRoomTypes() {
		return roomTypes;
	}

	public void setRoomTypes(List<RoomTypeDtor> roomTypes) {
		this.roomTypes = roomTypes;
	}

	public List<RateDto> getRates() {
		return rates;
	}

	public void setRates(List<RateDto> rates) {
		this.rates = rates;
	}
}
