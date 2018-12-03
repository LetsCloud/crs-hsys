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

	public HotelChildDto() {
	}

	public HotelChildDto(BaseDto base) {
		this.setId(base.getId());
		this.setWebSafeKey(base.getWebSafeKey());
	}

	public void setHotel(HotelDtor hotel) {
		this.hotel = hotel;
	}

	@Override
	public String toString() {
		String ret = "HotelChildDto:{" + super.toString() + ", hotel=" + hotel + "}";
		return ret;
	}

	public static class Builder extends BaseDto.Builder {

		private HotelDtor hotel;

		public Builder hotel(HotelDtor hotel) {
			this.hotel = hotel;
			return this;
		}

		public HotelChildDto build() {
			HotelChildDto dto = new HotelChildDto(super.build());
			dto.setHotel(hotel);
			return dto;
		}
	}
}
