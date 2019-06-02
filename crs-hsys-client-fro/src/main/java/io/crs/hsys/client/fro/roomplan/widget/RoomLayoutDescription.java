/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.widget;

import java.util.Date;

/**
 * Describes the layout of days (single, all and multi-day) within a single week
 * that is visualized in the <code>MonthView</code>. A
 * <code>WeekLayoutDescription</code> is not aware of any other thing than
 * placing an appointment <em>horizontally</em>, i.e., without considering the
 * exact week the appointment belongs to. It is the
 * <code>MonthLayoutDescription</code> responsibility to allocate the month
 * necessary <code>weeks</code> and distributing appointments over them.
 *
 * @author Carlos D. Morales
 * @see io.crs.hsys.client.fro.roomplan.widget.RoomPlanView.gwt.calendar.client.monthview.MonthView
 * @see com.bradrydzewski.gwt.calendar.client.monthview.MonthLayoutDescription
 */
public class RoomLayoutDescription {

	public static final int FIRST_DAY = 0;
	public static final int LAST_DAY = 6;
	private BookingStackingManager topAppointmentsManager = null;

	private DayCellLayoutDescription[] days = null;

	private Date calendarFirstDay = null;
	private Date calendarLastDay = null;

	private int maxLayer = -1;

	public RoomLayoutDescription(Date calendarFirstDay, Date calendarLastDay, int maxLayer) {
		this.calendarFirstDay = calendarFirstDay;
		this.calendarLastDay = calendarLastDay;
		days = new DayCellLayoutDescription[7];
		this.maxLayer = maxLayer;
		topAppointmentsManager = new BookingStackingManager();
		topAppointmentsManager.setLayerOverflowLimit(this.maxLayer);
	}

	public RoomLayoutDescription(Date calendarFirstDay, Date calendarLastDay) {
		this(calendarFirstDay, calendarLastDay, Integer.MAX_VALUE);
	}

	private void assertValidDayIndex(int day) {
		if (day < FIRST_DAY || day > days.length) {
			throw new IllegalArgumentException("Invalid day index (" + day + ")");
		}
	}

	private DayCellLayoutDescription initDay(int day) {
		assertValidDayIndex(day);
		if (days[day] == null) {
			days[day] = new DayCellLayoutDescription(day);
		}
		return days[day];
	}

	public boolean areThereAppointmentsOnDay(int day) {
		assertValidDayIndex(day);
		return days[day] != null || topAppointmentsManager.areThereAppointmentsOn(day);
	}

	public DayCellLayoutDescription getDayLayoutDescription(int day) {
		assertValidDayIndex(day);
		if (!areThereAppointmentsOnDay(day)) {
			return null;
		}
		return days[day];
	}

	public void addBooking(Booking booking) {
		int dayOfWeek = dayInWeek(booking.getStart());
		if (booking.isAllDay()) {
			topAppointmentsManager.assignLayer(new BookingLayoutDescription(dayOfWeek, booking));
		} else {
			initDay(dayOfWeek).addBooking(booking);
		}
	}

	public int currentStackOrderInDay(int dayIndex) {
		return topAppointmentsManager.lowestLayerIndex(dayIndex);
	}

	public void addMultiDayAppointment(Booking appointment) {
		int weekStartDay = dayInWeek(appointment.getStart());
		int weekEndDay = dayInWeek(appointment.getEnd());

		if (!appointment.getEnd().before(calendarLastDay)) {
			weekEndDay = LAST_DAY;
		}

		topAppointmentsManager.assignLayer(new BookingLayoutDescription(weekStartDay, weekEndDay, appointment));
	}

	public void addMultiWeekAppointment(Booking appointment, AppointmentWidgetParts presenceInMonth) {

		switch (presenceInMonth) {
		case FIRST_WEEK:
			int weekStartDay = dayInWeek(appointment.getStart());
			topAppointmentsManager.assignLayer(new BookingLayoutDescription(weekStartDay, LAST_DAY, appointment));
			break;
		case IN_BETWEEN:
			topAppointmentsManager.assignLayer(new BookingLayoutDescription(FIRST_DAY, LAST_DAY, appointment));
			break;
		case LAST_WEEK:
			int weekEndDay = dayInWeek(appointment.getEnd());
			topAppointmentsManager.assignLayer(new BookingLayoutDescription(FIRST_DAY, weekEndDay, appointment));
			break;
		}
	}

	private int dayInWeek(Date date) {
		if (date.before(calendarFirstDay)) {
			return FIRST_DAY;
		}

		if (date.after(calendarLastDay)) {
			return LAST_DAY;
		}

		return (int) Math.floor(DateUtils.differenceInDays(date, calendarFirstDay) % 7d);
	}

	public BookingStackingManager getTopAppointmentsManager() {
		return topAppointmentsManager;
	}
}