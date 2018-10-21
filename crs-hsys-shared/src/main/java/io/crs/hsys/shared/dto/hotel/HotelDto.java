/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.crs.hsys.shared.dto.common.AccountChildDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class HotelDto extends AccountChildDto {

	/**
	 * Egyedi azonosító
	 */
	private String code;

	/**
	 * Név
	 */
	private String name;

	/**
	 * Üzleti dátum
	 */
	private Date businessDate;

	/**
	 * A hotel ügyviteli konfigurációja
	 */
	private HotelConfigDto config;

	private List<CityTaxDto> cityTaxes = new ArrayList<CityTaxDto>();

	public HotelDto() {
	}

	public HotelDto(HotelDtor source) {
		this.setId(source.getId());
		this.setWebSafeKey(source.getWebSafeKey());
		this.setCode(source.getCode());
		this.setName(source.getName());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public HotelConfigDto getConfig() {
		return config;
	}

	public void setConfig(HotelConfigDto config) {
		this.config = config;
	}

	public List<CityTaxDto> getCityTaxes() {
		return cityTaxes;
	}

	public void setCityTaxes(List<CityTaxDto> cityTaxes) {
		this.cityTaxes = cityTaxes;
	}

	@Override
	public String toString() {
		String ret = "HotelDto:{code=" + code + ", name=" + name + ", " + super.toString() + "}";
		return ret;
	}
}
