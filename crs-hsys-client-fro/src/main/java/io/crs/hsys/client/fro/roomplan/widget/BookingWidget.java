/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.widget;

import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * A panel used to render an <code>Appointment</code> in a
 * <code>MonthView</code>.
 * <p>
 * Through an association to a domain-model <code>Appointment</code>,
 * <code>AppointmentWidget</code>s allow displaying the appointment details
 * <em>and</em> to capture mouse and keyboard events.
 * </p>
 *
 * @author Brad Rydzewski
 * @author Carlos D. Morales
 */
public class BookingWidget extends FocusPanel {
	/**
	 * The underlying <code>Appointment</code> represented by this panel.
	 */
	private Booking appointment;

	/**
	 * Creates an <code>AppointmentWidget</code> with a reference to the provided
	 * <code>appointment</code>.
	 *
	 * @param appointment The appointment to be displayed through this panel widget
	 */
	public BookingWidget(Booking appointment) {
		this.appointment = appointment;
		Label titleLabel = new Label();
		titleLabel.getElement().setInnerHTML(this.appointment.getTitle());
		this.add(titleLabel);
	}

	/**
	 * Returns the <code>Appointment</code> this panel represents/is associated to.
	 *
	 * @return The domain model appointment rendered through this panel
	 */
	public Booking getAppointment() {
		return appointment;
	}
}