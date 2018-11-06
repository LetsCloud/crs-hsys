/**
 * 
 */
package io.crs.hsys.shared.dto.reservation;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.shared.constans.ReservationStatus;
import io.crs.hsys.shared.dto.common.CurrencyDto;
import io.crs.hsys.shared.dto.hotel.HotelChildDto;
import io.crs.hsys.shared.dto.hotel.MarketCodeDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class ReservationDto extends HotelChildDto {

	/**
	 * Foglalás státusza.
	 */
	private ReservationStatus status;

	private MarketCodeDto market;
	
	/**
	 * Valutanem.
	 */
	private CurrencyDto currency;

	/**
	 * Szobafoglalások listája.
	 */
	private List<RoomStayDto> roomStays = new ArrayList<RoomStayDto>();

	/**
	 * Terhelés előjegyzések listája.
	 */
	private List<FixedChargeDto> fixedCharges = new ArrayList<FixedChargeDto>();

	/**
	 * Profil kapcsolatok.
	 */
	private List<ProfileLinkDto> profileLinks = new ArrayList<ProfileLinkDto>();

	/**
	 * Csoport foglalás esetén a kapcsolódó szobafoglalások.
	 */
	private List<ReservationDto> reservations = new ArrayList<ReservationDto>();

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public CurrencyDto getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDto currencyDto) {
		this.currency = currencyDto;
	}

	public List<RoomStayDto> getRoomStays() {
		return roomStays;
	}

	public void setRoomStays(List<RoomStayDto> roomStayDtos) {
		this.roomStays = roomStayDtos;
	}

	public List<FixedChargeDto> getFixedCharges() {
		return fixedCharges;
	}

	public void setFixedCharges(List<FixedChargeDto> fixedChargeDtos) {
		this.fixedCharges = fixedChargeDtos;
	}

	public List<ReservationDto> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationDto> reservationDtos) {
		this.reservations = reservationDtos;
	}

	public List<ProfileLinkDto> getProfileLinks() {
		return profileLinks;
	}

	public void setProfileLinks(List<ProfileLinkDto> profileLinkDtos) {
		this.profileLinks = profileLinkDtos;
	}

}
