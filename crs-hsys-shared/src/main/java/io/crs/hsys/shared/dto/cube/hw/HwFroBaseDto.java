/**
 * 
 */
package io.crs.hsys.shared.dto.cube.hw;

import com.fasterxml.jackson.annotation.JsonSetter;

import io.crs.hsys.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class HwFroBaseDto implements Dto {

	private String hotelId;

	private String SZOTIP;

	private String DATUMA;

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getSZOTIP() {
		return SZOTIP;
	}

	@JsonSetter("SZOTIP")
	public void setSZOTIP(String sZOTIP) {
		SZOTIP = sZOTIP;
	}

	public String getDATUMA() {
		return DATUMA;
	}

	@JsonSetter("DATUMA")
	public void setDATUMA(String dATUMA) {
		DATUMA = dATUMA;
	}

}
