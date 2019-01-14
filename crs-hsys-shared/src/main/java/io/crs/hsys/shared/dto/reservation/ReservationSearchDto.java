/**
 * 
 */
package io.crs.hsys.shared.dto.reservation;

import java.util.Date;

import io.crs.hsys.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class ReservationSearchDto extends BaseDto {

	private String name;
	private Date arrival;
	private Date departure;
	private Integer numOfRooms;
	private Integer numOfGuests;
	private String customer;

	public ReservationSearchDto() {
	}

	public ReservationSearchDto(Builder<?> builder) {
		super(builder);
		name = builder.name;
		arrival = builder.arrival;
		departure = builder.departure;
		numOfRooms = builder.numOfRooms;
		numOfGuests = builder.numOfGuests;
		customer = builder.customer;
	}

	public ReservationSearchDto(BaseDto base) {
		this.setId(base.getId());
		this.setWebSafeKey(base.getWebSafeKey());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Integer getNumOfRooms() {
		return numOfRooms;
	}

	public void setNumOfRooms(Integer numOfRooms) {
		this.numOfRooms = numOfRooms;
	}

	public Integer getNumOfGuests() {
		return numOfGuests;
	}

	public void setNumOfGuests(Integer numOfGuests) {
		this.numOfGuests = numOfGuests;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public static abstract class Builder<T extends Builder<T>> extends BaseDto.Builder<T> {

		private String name;
		private Date arrival;
		private Date departure;
		private Integer numOfRooms;
		private Integer numOfGuests;
		private String customer;

		public T name(String name) {
			this.name = name;
			return self();
		}

		public T arrival(Date arrival) {
			this.arrival = arrival;
			return self();
		}

		public T departure(Date departure) {
			this.departure = departure;
			return self();
		}

		public T numOfRooms(Integer numOfRooms) {
			this.numOfRooms = numOfRooms;
			return self();
		}

		public T numOfGuests(Integer numOfGuests) {
			this.numOfGuests = numOfGuests;
			return self();
		}

		public T customer(String customer) {
			this.customer = customer;
			return self();
		}

		public ReservationSearchDto build() {
			return new ReservationSearchDto(this);
		}
	}

	/**
	 * 
	 * @author robi
	 *
	 */
	protected static class Builder2 extends Builder<Builder2> {
		@Override
		protected Builder2 self() {
			return this;
		}
	}

	public static Builder<?> builder() {
		return new Builder2();
	}
}
