/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.widget;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the calculated layout description of all <code>Booking</code>s in
 * single day part of a room row in a <code>PeriodView</code>.
 * <p>
 * </p>
 * <strong>Note:</strong> A <code>DayCellLayoutDescription</code> is not aware
 * of <em>multi-day</em> <code>Booking</code>s that might span the day
 * represented by this description.
 *
 * @author Carlos D. Morales
 */
public class DayCellLayoutDescription {
	/**
	 * The list of <em>simple</em> bookings (not multiday, not all-day) in this
	 * single day.
	 */
	private List<Booking> bookings = new ArrayList<Booking>();

	/**
	 * The index of the represented day in the corresponding parent week.
	 */
	private int dayIndex = -1;

	public DayCellLayoutDescription(int dayIndex) {
		this.dayIndex = dayIndex;
	}

	public List<Booking> getAppointments() {
		return bookings;
	}

	public int getTotalAppointmentCount() {
		return bookings.size();
	}

	public void addBooking(Booking booking) {
		if (!booking.isMultiDay()) {
			bookings.add(booking);
		} else {
			throw new IllegalArgumentException("Attempted to add a multiday appointment to a single day description");
		}
	}

	public int getDayIndex() {
		return dayIndex;
	}
}