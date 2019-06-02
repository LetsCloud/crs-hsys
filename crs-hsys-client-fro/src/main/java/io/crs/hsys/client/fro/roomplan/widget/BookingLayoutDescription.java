/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.widget;

/**
 * Contains common properties and behavior for layout descriptions that can be
 * &quot;stacked&quot; on top of each roomplan view's room.
 *
 * @author Carlos D. Morales
 */
public class BookingLayoutDescription {
	/**
	 * The layer order (in a stack-like arrangement)assigned to this description by
	 * the <code>BookingStackingManager</code> when arranging the descriptions on
	 * the top of a room.
	 */
	private int stackOrder = 0;

	/**
	 * The start day of the described <code>Booking</code> when laid on the top of
	 * one of the rooms while visualizing a roomplan through the
	 * <code>RoomPlanView</code>.
	 */
	private int fromDayCell = 0;

	/**
	 * The end day of the described <code>Booking</code> when laid on the top of one
	 * of the rooms while visualizing a roomplan through the
	 * <code>RoomPlanView</code>.
	 */
	private int toDayCell = 0;

	/**
	 * The underlying <code>Booking</code> whose details will be displayed through
	 * the <code>RoomPlanView</code>.
	 */
	private Booking booking = null;

	public BookingLayoutDescription(int fromDayCell, int toDayCell, Booking booking) {
		this.fromDayCell = fromDayCell;
		this.toDayCell = toDayCell;
		this.booking = booking;
	}

	public BookingLayoutDescription(int dayCell, Booking booking) {
		this(dayCell, dayCell, booking);
	}

	public boolean overlapsWithRange(int rangeFrom, int rangeTo) {
		return fromDayCell >= rangeFrom && fromDayCell <= rangeTo || fromDayCell <= rangeFrom && toDayCell >= rangeFrom;
	}

	public void setStackOrder(int stackOrder) {
		this.stackOrder = stackOrder;
	}

	public int getStackOrder() {
		return stackOrder;
	}

	public int getFromDayCell() {
		return fromDayCell;
	}

	public int getToDayCell() {
		return toDayCell;
	}

	public boolean spansMoreThanADay() {
		return fromDayCell != toDayCell;
	}

	public BookingLayoutDescription split() {
		BookingLayoutDescription secondPart = null;
		if (spansMoreThanADay()) {
			secondPart = new BookingLayoutDescription(fromDayCell + 1, toDayCell, booking);
			this.toDayCell = this.fromDayCell;
		} else {
			secondPart = this;
		}
		return secondPart;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "AppointmentLayoutDescription{" + "stackOrder=" + stackOrder + ", fromWeekDay=" + fromDayCell
				+ ", toWeekDay=" + toDayCell + ", appointment=[" + booking.getTitle() + "]@" + booking.hashCode()
				+ '}';
	}
}