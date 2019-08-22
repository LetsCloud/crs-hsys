/**
 * 
 */
package io.crs.hsys.shared.dto.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.hotel.HotelChildDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class RoomStayDto extends HotelChildDto {

	/**
	 * Érkezés napja.
	 */
	private Date arrival;

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

	/**
	 * Szobatípus hivatkozás.
	 */
	private RoomTypeDto roomTypeDto;

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

	public RoomTypeDto getRoomType() {
		return roomTypeDto;
	}

	public void setRoomType(RoomTypeDto roomTypeDto) {
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

}
