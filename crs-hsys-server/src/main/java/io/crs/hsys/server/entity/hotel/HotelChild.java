/**
 * 
 */
package io.crs.hsys.server.entity.hotel;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Parent;

import io.crs.hsys.server.entity.BaseEntity;

/**
 * @author CR
 *
 */
public class HotelChild extends BaseEntity {
//	private static final Logger logger = LoggerFactory.getLogger(HotelChild.class.getName());

	@Parent
	private Ref<Hotel> hotelRef;

	/**
	 * Üres konstruktor az Objectify kedvéért.
	 */
	public HotelChild() {
//		logger.info("HotelChild()");
	}

	public Hotel getHotel() {
		return hotelRef.get();
	}

	public void setHotel(Hotel hotel) {
		if (hotel.getId() != null)
			this.hotelRef = Ref.create(hotel);
	}

	/**
	 * 
	 * @author CR
	 *
	 * @param <T>
	 */
	public static class Builder<T extends Builder<?>> {

		private Hotel hotel;

		public Builder() {
		}

		@SuppressWarnings("unchecked")
		public T hotel(Hotel hotel) {
			this.hotel = hotel;
			return (T) this;
		}

		public HotelChild build() {
			return new HotelChild(this);
		}
	}

	protected HotelChild(Builder<?> builder) {
		this.setHotel(builder.hotel);
	}

}
