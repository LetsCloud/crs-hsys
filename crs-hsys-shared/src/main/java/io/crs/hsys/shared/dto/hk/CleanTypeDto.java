/**
 * 
 */
package io.crs.hsys.shared.dto.hk;

import io.crs.hsys.shared.dto.hotel.HotelChildDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class CleanTypeDto extends HotelChildDto {

	private String code;

	private String description;

	private Integer time;

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

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
}
