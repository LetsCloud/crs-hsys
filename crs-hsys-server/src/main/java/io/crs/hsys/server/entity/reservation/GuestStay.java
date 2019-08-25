/**
 * 
 */
package io.crs.hsys.server.entity.reservation;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.server.entity.hotel.Rate;
import io.crs.hsys.server.entity.profile.Guest;

/**
 * Vendég tartózokdás.
 * 
 * @author CR
 *
 */
public class GuestStay {

	/**
	 * Vendég érkezése.
	 */
	@Index
	private Date arrival;

	/**
	 * Átköltözött másik szobából.
	 */
	private Boolean movedInto;

	/**
	 * Vendég távozása.
	 */
	@Index
	private Date departure;

	/**
	 * Átköltözött másik szobába.
	 */
	private Boolean movedOut;

	/**
	 * Vendég profil kapcsolat.
	 */
	@Index
	private Ref<Guest> guestRef;

	/**
	 * A vendégtartózkodáshoz rendelet árkód.
	 */
	private List<Rate> rates;

	/**
	 * Átköltözött másik szobából.
	 */
	private Boolean chekedIn;

	public GuestStay() {
		movedInto = false;
		movedOut = false;
		chekedIn = false;
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

	public Ref<Guest> getGuestRef() {
		return guestRef;
	}

	public void setGuestRef(Ref<Guest> guestRef) {
		this.guestRef = guestRef;
	}

	public Guest getGuest() {
		return guestRef.get();
	}

	public void setGuest(Guest guest) {
		this.guestRef = Ref.create(guest);
	}

	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}

	public Boolean getChekedIn() {
		return chekedIn;
	}

	public void setChekedIn(Boolean chekedIn) {
		this.chekedIn = chekedIn;
	}

	public static Builder builder() {
		return new GuestStay.Builder();
	}

	/**
	 * 
	 * @author CR
	 *
	 */
	public static class Builder {
		
		private GuestStay instance = new GuestStay();

		public Builder() {
		}

		public GuestStay build() {
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

		public Builder chekedIn(Boolean chekedIn) {
			instance.chekedIn = chekedIn;
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

		public Builder guest(Guest guest) {
			instance.setGuest(guest);
			return this;
		}

		public Builder rates(List<Rate> rates) {
			instance.setRates(rates);
			return this;
		}
	}

}