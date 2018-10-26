/**
 * 
 */
package io.crs.hsys.server.entity.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.server.entity.hotel.Room;
import io.crs.hsys.server.entity.hotel.RoomType;

/**
 * Szobafoglalás.
 * 
 * @author CR
 *
 */
public class RoomStay {

	public static final String FLD_ARRIVAL = "arrival";
	public static final String FLD_DEPARTURE = "departure";

	/**
	 * Érkezés napja.
	 */
	@Index
	private Date arrival;

	/**
	 * Elutazott.
	 */
	@Index
	private Date departure;

	/**
	 * Átköltözött másik szobából.
	 */
	private Boolean movedInto;

	/**
	 * Átköltözött másik szobába.
	 */
	private Boolean movedOut;

	/**
	 * Szobatípus hivatkozás.
	 */
	private Ref<RoomType> roomTypeRef;

	/**
	 * Szobatípus hivatkozás.
	 */
	private Integer quantity;

	/**
	 * Szoba hivatkozás.
	 */
	private Ref<Room> roomRef;

	/**
	 * Szobafoglaláshoz rendelt árkódok.
	 */
	private List<Rate> rates;

	/**
	 * Szobafoglalás vendégtartózkodásai.
	 */
	private List<GuestStay> guestStays = new ArrayList<GuestStay>();

	private List<String> amenities = new ArrayList<String>();

	public RoomStay() {
		movedInto = false;
		movedOut = false;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public Boolean getMovedInto() {
		return movedInto;
	}

	public void setMovedInto(Boolean movedInto) {
		this.movedInto = movedInto;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Boolean getMovedOut() {
		return movedOut;
	}

	public void setMovedOut(Boolean movedOut) {
		this.movedOut = movedOut;
	}

	public Ref<RoomType> getRoomTypeRef() {
		return roomTypeRef;
	}

	public void setRoomTypeRef(Ref<RoomType> roomTypeRef) {
		this.roomTypeRef = roomTypeRef;
	}

	public RoomType getRoomType() {
		return roomTypeRef.get();
	}

	public void setRoomType(RoomType roomType) {
		this.roomTypeRef = Ref.create(roomType);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Ref<Room> getRoomRef() {
		return roomRef;
	}

	public void setRoomRef(Ref<Room> roomRef) {
		this.roomRef = roomRef;
	}

	public Room getRoom() {
		return roomRef.get();
	}

	public void setRoom(Room room) {
		this.roomRef = Ref.create(room);
		this.setRoomType(room.getRoomType());
	}

	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}

	public List<GuestStay> getGuestStays() {
		return guestStays;
	}

	public void setGuestStays(List<GuestStay> guestStays) {
		this.guestStays = guestStays;
	}

	public List<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}

	public static Builder builder() {
		return new RoomStay.Builder();
	}

	/**
	 * 
	 * @author CR
	 *
	 */
	public static class Builder {

		private RoomStay instance = new RoomStay();

		public Builder() {
		}

		public RoomStay build() {
			return instance;
		}

		public Builder amenities(List<String> amenities) {
			instance.setAmenities(amenities);
			return this;
		}

		public Builder arrival(Date arrival) {
			instance.setArrival(arrival);
			return this;
		}

		public Builder departure(Date departure) {
			instance.setDeparture(departure);
			return this;
		}

		public Builder guestStays(List<GuestStay> guestStays) {
			instance.setGuestStays(guestStays);
			return this;
		}

		public Builder addGuestStay(GuestStay guestStay) {
			instance.guestStays.add(guestStay);
			return this;
		}

		public Builder movedInto(Boolean movedInto) {
			instance.setMovedInto(movedInto);
			return this;
		}

		public Builder movedOut(Boolean movedOut) {
			instance.setMovedOut(movedOut);
			return this;
		}

		public Builder quantity(Integer quantity) {
			instance.setQuantity(quantity);
			return this;
		}

		public Builder roomType(RoomType roomType) {
			instance.setRoomType(roomType);
			return this;
		}

		public Builder room(Room room) {
			instance.setRoom(room);
			return this;
		}

		public Builder rates(List<Rate> rates) {
			instance.setRates(rates);
			return this;
		}
	}
}
