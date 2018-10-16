/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import io.crs.hsys.shared.dto.common.AccountChildDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class HotelDtor extends AccountChildDto {

	/**
	 * Egyedi azonosító
	 */
	private String code;

	/**
	 * Név
	 */
	private String name;

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

	@Override
	public String toString() {
		return "HotelDtor:{code=" + code + ", name=" + name + ", " + super.toString() + "}";
	}
}
