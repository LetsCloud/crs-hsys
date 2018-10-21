/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import io.crs.hsys.shared.dto.BaseDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class HotelChildDto extends BaseDto {

	private HotelDtor hotel;

	public HotelDtor getHotel() {
		return hotel;
	}

	public void setHotel(HotelDtor hotel) {
		this.hotel = hotel;
	}

	@Override
	public String toString() {
		String ret = "HotelChildDto:{" + super.toString() + ", hotel=" + hotel + "}";
		return ret;
	}
}
