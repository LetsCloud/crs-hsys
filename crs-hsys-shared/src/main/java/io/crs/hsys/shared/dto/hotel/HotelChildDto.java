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

	protected HotelChildDto(Builder<?> builder) {
		super(builder);
		hotel = builder.hotel;
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

	/**
	 * 
	 * @author robi
	 *
	 * @param <T>
	 */
	public static abstract class Builder<T extends Builder<T>> extends BaseDto.Builder<T> {

		private HotelDtor hotel;

		public T hotel(HotelDtor hotel) {
			this.hotel = hotel;
			return self();
		}

		public HotelChildDto build() {
			return new HotelChildDto(this);
		}
	}

	/**
	 * 
	 * @author robi
	 *
	 */
	private static class Builder2 extends Builder<Builder2> {
		@Override
		protected Builder2 self() {
			return this;
		}
	}

	public static Builder<?> builder() {
		return new Builder2();
	}
}
