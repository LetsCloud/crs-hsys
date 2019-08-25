/**
 * 
 */
package io.crs.hsys.shared.dto.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class RoomStayDto implements Dto {

	/**
	 * Érkezés napja.
	 */
	private Date arrival;
	private Boolean eci = false;
	private Boolean lco = false;
	private Boolean fixed = false;
	private Boolean noPost = false;

	/**
	 * Elutazott.
	 */
	private Date departure;

	/**
	 * Átköltözött másik szobából.
	 */
	private Boolean movedInto;

	/**
	 * Átköltözött másik szobába.
	 */
	private Boolean movedOut;

	public Boolean getFixed() {
		return fixed;
	}

	public void setFixed(Boolean fixed) {
		this.fixed = fixed;
	}

	public Boolean getNoPost() {
		return noPost;
	}

	public void setNoPost(Boolean noPost) {
		this.noPost = noPost;
	}

	/**
	 * Szobatípus hivatkozás.
	 */
	private RoomTypeDtor roomTypeDto;

	/**
	 * Szobatípus hivatkozás.
	 */
	private Integer quantity;

	/**
	 * Szoba hivatkozás.
	 */
	private RoomDto room;

	/**
	 * Szobafoglaláshoz rendelt árkódok.
	 */
	private List<RateDto> rates;

	/**
	 * Szobafoglalás vendégtartózkodásai.
	 */
	private List<GuestStayDto> guestStays;

	private List<String> amenities = new ArrayList<String>();

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Boolean getMovedInto() {
		return movedInto;
	}

	public void setMovedInto(Boolean movedInto) {
		this.movedInto = movedInto;
	}

	public Boolean getMovedOut() {
		return movedOut;
	}

	public void setMovedOut(Boolean movedOut) {
		this.movedOut = movedOut;
	}

	public RoomTypeDtor getRoomType() {
		return roomTypeDto;
	}

	public void setRoomType(RoomTypeDtor roomTypeDto) {
		this.roomTypeDto = roomTypeDto;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public RoomDto getRoom() {
		return room;
	}

	public void setRoom(RoomDto roomDto) {
		this.room = roomDto;
	}

	public List<RateDto> getRates() {
		return rates;
	}

	public void setRates(List<RateDto> rateDtos) {
		this.rates = rateDtos;
	}

	public List<GuestStayDto> getGuestStays() {
		return guestStays;
	}

	public void setGuestStays(List<GuestStayDto> guestStayDtos) {
		this.guestStays = guestStayDtos;
	}

	public List<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}

	public Boolean getEci() {
		return eci;
	}

	public void setEci(Boolean eci) {
		this.eci = eci;
	}

	public Boolean getLco() {
		return lco;
	}

	public void setLco(Boolean lco) {
		this.lco = lco;
	}

	/**
	 * 
	 * @author CR
	 *
	 */
	public static class Builder {

		private RoomStayDto instance = new RoomStayDto();

		public Builder() {
		}

		public RoomStayDto build() {
			return instance;
		}

		public Builder arrival(Date arrival) {
			instance.setArrival(arrival);
			return this;
		}

		public Builder departure(Date departure) {
			instance.setDeparture(departure);
			return this;
		}

		public Builder eci(Boolean eci) {
			instance.setEci(eci);
			return this;
		}

		public Builder lco(Boolean lco) {
			instance.setLco(lco);
			return this;
		}


		public Builder fixed(Boolean fixed) {
			instance.setFixed(fixed);
			return this;
		}

		public Builder noPost(Boolean noPost) {
			instance.setNoPost(noPost);
			return this;
		}
		
		
		public Builder amenities(List<String> amenities) {
			instance.setAmenities(amenities);
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

		public Builder room(RoomDto room) {
			instance.setRoom(room);
			return this;
		}

		public Builder quantity(Integer quantity) {
			instance.setQuantity(quantity);
			return this;
		}

		public Builder roomType(RoomTypeDtor roomType) {
			instance.setRoomType(roomType);
			return this;
		}
	}

}
