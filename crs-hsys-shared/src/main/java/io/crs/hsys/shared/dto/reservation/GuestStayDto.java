/**
 * 
 */
package io.crs.hsys.shared.dto.reservation;

import java.util.Date;
import java.util.List;

import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.profile.GuestDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class GuestStayDto implements Dto {

	/**
	 * Vendég érkezése.
	 */
	private Date arrival;

	/**
	 * Átköltözött másik szobából.
	 */
	private Boolean movedInto;

	/**
	 * Vendég távozása.
	 */
	private Date departure;

	/**
	 * Átköltözött másik szobába.
	 */
	private Boolean movedOut;

	/**
	 * Vendég profil kapcsolat.
	 */
	private GuestDto guest;

	/**
	 * A vendégtartózkodáshoz rendelet árkód.
	 */
	private List<RateDto> rates;

	/**
	 * Átköltözött másik szobából.
	 */
	private Boolean chekedIn;

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

	public GuestDto getGuest() {
		return guest;
	}

	public void setGuest(GuestDto guest) {
		this.guest = guest;
	}

	public Boolean getChekedIn() {
		return chekedIn;
	}

	public void setChekedIn(Boolean chekedIn) {
		this.chekedIn = chekedIn;
	}

	public List<RateDto> getRates() {
		return rates;
	}

	public void setRates(List<RateDto> rates) {
		this.rates = rates;
	}

}
