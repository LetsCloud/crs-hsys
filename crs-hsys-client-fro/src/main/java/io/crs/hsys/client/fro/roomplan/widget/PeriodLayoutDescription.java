/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.widget;

import java.util.Date;
import java.util.List;

/**
 * Describes the layout for all bookings in all the rooms displayed in a
 * <code>RoomPlanView</code>. This class is responsible for the distribution of
 * the bookings over the multiple rooms they possibly span.
 *
 * @author Carlos D. Morales
 */
public class PeriodLayoutDescription {

	private Date roomPlanFirstDay = null;

	private Date roomPlanLastDay = null;

	private RoomLayoutDescription[] rooms = new RoomLayoutDescription[6];

	public PeriodLayoutDescription(Date roomPlanFirstDay, int monthViewRequiredRows, List<Booking> bookings,
			int maxLayer) {
		this.roomPlanFirstDay = roomPlanFirstDay;
		this.roomPlanLastDay = calculateLastDate(roomPlanFirstDay, monthViewRequiredRows);
		placeBookingss(bookings, maxLayer);
	}

	public PeriodLayoutDescription(Date roomPlanFirstDay, int monthViewRequiredRows, List<Booking> bookings) {
		this(roomPlanFirstDay, monthViewRequiredRows, bookings, Integer.MAX_VALUE);
	}

	private void initRoom(int roomIndex, int maxLayer) {
		if (rooms[roomIndex] == null) {
			rooms[roomIndex] = new RoomLayoutDescription(roomPlanFirstDay, roomPlanLastDay, maxLayer);
		}
	}

	private void placeBookingss(List<Booking> bookings, int maxLayer) {

		for (Booking booking : bookings) {
			if (outOfPeriod(booking, roomPlanFirstDay, roomPlanLastDay)) {
				int startWeek = calculateWeekFor(booking.getStart(), roomPlanFirstDay);

				/* Place appointments only in this month */
				if (startWeek >= 0 && startWeek < rooms.length) {
					initRoom(startWeek, maxLayer);
					if (booking.isMultiDay() || booking.isAllDay()) {
						positionMultidayAppointment(startWeek, booking, maxLayer);
					} else {
						rooms[startWeek].addBooking(booking);
					}
				}
			}
		}
	}

	private boolean isMultiWeekAppointment(int startWeek, int endWeek) {
		return startWeek != endWeek;
	}

	private void positionMultidayAppointment(int startWeek, Booking appointment, int maxLayer) {
		int endWeek = calculateWeekFor(appointment.getEnd(), roomPlanFirstDay);

		initRoom(endWeek, maxLayer);
		if (isMultiWeekAppointment(startWeek, endWeek)) {
			distributeOverWeeks(startWeek, endWeek, appointment, maxLayer);
		} else {
			rooms[startWeek].addMultiDayAppointment(appointment);
		}
	}

	private void distributeOverWeeks(int startWeek, int endWeek, Booking appointment, int maxLayer) {
		rooms[startWeek].addMultiWeekAppointment(appointment, AppointmentWidgetParts.FIRST_WEEK);
		for (int week = startWeek + 1; week < endWeek; week++) {
			initRoom(week, maxLayer);
			rooms[week].addMultiWeekAppointment(appointment, AppointmentWidgetParts.IN_BETWEEN);
		}
		if (startWeek < endWeek) {
			initRoom(endWeek, maxLayer);
			rooms[endWeek].addMultiWeekAppointment(appointment, AppointmentWidgetParts.LAST_WEEK);
		}
	}

	private boolean outOfPeriod(Booking booking, Date roomPlanFirstDate, Date roomPlanLastDate) {
		return !(booking.getStart().before(roomPlanFirstDate) && booking.getEnd().before(roomPlanFirstDate)
				|| booking.getStart().after(roomPlanLastDate) && booking.getEnd().after(roomPlanLastDate));
	}

	private int calculateWeekFor(Date testDate, Date roomPlanFirstDate) {
		// fix for issue 66. differenceInDays returns abs value, causing problems
		if (testDate.before(roomPlanFirstDate))
			return 0;

		int week = (int) Math.floor(DateUtils.differenceInDays(testDate, roomPlanFirstDate) / 7d);

		return Math.min(week, rooms.length - 1);
	}

	@SuppressWarnings("deprecation")
	private Date calculateLastDate(final Date startDate, int weeks) {
		int daysInMonthGrid = weeks * 7;
		Date endDate = (Date) startDate.clone();
		endDate.setDate(endDate.getDate() + daysInMonthGrid - 1);
		// fix for issue 164: The endDate is at the end of the day
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		return endDate;
	}

	public RoomLayoutDescription[] getWeekDescriptions() {
		return rooms;
	}

}