/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.common.CurrencyDto;
import io.crs.hsys.shared.dto.common.ServiceDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class HotelConfigDto implements Dto {

	/**
	 * Alapértelmezett valutanem.
	 */
	private CurrencyDto currency;

	/**
	 * Árfolyamnyereség szolgáltatás.
	 */
	private ServiceDto xrtGainService;

	/**
	 * Árfolyamvesztesség szolgáltatás.
	 */
	private ServiceDto xrtLossService;

	public CurrencyDto getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDto currency) {
		this.currency = currency;
	}

	public ServiceDto getXrtGainService() {
		return xrtGainService;
	}

	public void setXrtGainService(ServiceDto xrtGainService) {
		this.xrtGainService = xrtGainService;
	}

	public ServiceDto getXrtLossService() {
		return xrtLossService;
	}

	public void setXrtLossService(ServiceDto xrtLossService) {
		this.xrtLossService = xrtLossService;
	}
}
