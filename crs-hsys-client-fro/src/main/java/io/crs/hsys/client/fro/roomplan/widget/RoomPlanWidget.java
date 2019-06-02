/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.widget;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author CR
 *
 */
public class RoomPlanWidget extends InteractiveWidget {

	/**
	 * Set to <code>true</code> if the calendar layout is suspended and cannot be
	 * triggered.
	 */
	private boolean layoutSuspended = false;

	/**
	 * Set to <code>true</code> if the calendar is pending the layout of its
	 * appointments.
	 */
	private boolean layoutPending = false;

	/**
	 * The date currently displayed by the calendar. Set to current system date by
	 * default.
	 */
	private Date date;

	/**
	 * Calendar settings, set to default.
	 */
	private RoomPlanSettings settings = RoomPlanSettings.DEFAULT_SETTINGS;

	/**
	 * The component to manage the set of appointments displayed by this
	 * <code>CalendarWidget</code>.
	 */
	private BookingManager appointmentManager = null;

	private AbstractRoomPlanView view = null;

	/**
	 * Creates a <code>CalendarWidget</code> with an empty set of appointments and
	 * the current system date as the date currently displayed by the calendar.
	 */
	public RoomPlanWidget() {
		this(new Date());
	}

	public RoomPlanWidget(Date date) {
		super();
		appointmentManager = new BookingManager();
		this.date = date;
		DateUtils.resetTime(this.date);
	}

	/**
	 * Changes the current view of this calendar widget to the specified
	 * <code>view</code>. By setting this widget's current view the whole widget
	 * panel is cleared.
	 *
	 * @param view The {@link AbstractRoomPlanView} implementation to render this widget's
	 *             underlying calendar
	 */
	public final void setView(AbstractRoomPlanView view) {
		this.getRootPanel().clear();
		this.view = view;
		this.view.attach(this);
		this.setStyleName(this.view.getStyleName());
		this.refresh();
	}

	public final AbstractRoomPlanView getView() {
		return view;
	}

	public Date getDate() {
		return (Date) date.clone();
	}

	public void setDate(Date date, int days) {
		Date dateCopy = (Date) date.clone();
		DateUtils.resetTime(dateCopy);
		this.date = dateCopy;
		view.setDisplayedDays(days);
		refresh();
	}

	public void setDate(Date date) {
		setDate(date, getDays());
	}

	/**
	 * Moves this calendar widget current <code>date</code> as many days as
	 * specified by the <code>numOfDays</code> parameter.
	 *
	 * @param numOfDays The number of days to change the calendar date forward
	 *                  (positive number) or backwards.
	 */
	@SuppressWarnings("deprecation")
	public void addDaysToDate(int numOfDays) {
		this.date.setDate(this.date.getDate() + numOfDays);
	}

	public int getDays() {
		return view == null ? 3 : view.getDisplayedDays();
	}

	public void setDays(int days) {
		view.setDisplayedDays(days);
		refresh();
	}

	/**
	 * Returns the collection of appointments in the underlying in-memory model of
	 * this calendar widget. <strong>Warning</strong>: the returned collection of
	 * apointments can be modified by client code, possibly breaking the system
	 * model invariants.
	 *
	 * @return The set of appointments to be displayed by this calendar widget
	 * @see BookingManager#getAppointments()
	 */
	public List<Booking> getAppointments() {
		return appointmentManager.getAppointments();
	}

	/**
	 * Removes an appointment from the calendar.
	 *
	 * @param appointment the item to be removed.
	 */
	public void removeAppointment(Booking appointment) {
		removeAppointment(appointment, false);
	}

	/**
	 * Removes the currently selected appointment from the model, if such
	 * appointment is set.
	 */
	public void removeCurrentlySelectedAppointment() {
		appointmentManager.removeCurrentlySelectedAppointment();
	}

	/**
	 * Removes an appointment from the calendar.
	 *
	 * @param appointment the item to be removed.
	 * @param fireEvents  <code>true</code> to allow deletion events to be fired
	 */
	public void removeAppointment(Booking appointment, boolean fireEvents) {
		boolean commitChange = true;

		if (fireEvents) {
//			commitChange = DeleteEvent.fire(this, getSelectedAppointment());
		}

		if (commitChange) {
			appointmentManager.removeAppointment(appointment);
			refresh();
		}
	}

	/**
	 * Resets the &quot;currently selected&quot; appointment of this calendar.
	 *
	 * @see io.crs.hsys.client.fro.roomplan.widget.BookingManager.gwt.calendar.client.AppointmentManager
	 */
	public void resetSelectedAppointment() {
		appointmentManager.resetSelectedAppointment();
	}

	/**
	 * Adds an appointment to the calendar.
	 *
	 * @param appointment item to be added
	 */
	public void addAppointment(Booking appointment) {
		if (appointment == null) {
			throw new NullPointerException("Added appointment cannot be null.");
		}
		appointmentManager.addAppointment(appointment);
		refresh();
	}

	/**
	 * Adds each appointment in the list to the calendar.
	 *
	 * @param appointments items to be added.
	 */
	public void addAppointments(List<Booking> appointments) {
		appointmentManager.addAppointments(appointments);
		refresh();
	}

	/**
	 * Clears all appointment items.
	 */
	public void clearAppointments() {
		appointmentManager.clearAppointments();
		refresh();
	}

	/**
	 * Sets the currently selected item.
	 *
	 * @param appointment the item to be selected, or <code>null</code> to de-select
	 *                    all items.
	 */
	public void setSelectedAppointment(Booking appointment) {
		setSelectedAppointment(appointment, true);
	}

	public void setSelectedAppointment(Booking appointment, boolean fireEvents) {
		appointmentManager.setSelectedAppointment(appointment);
		if (fireEvents) {
			fireSelectionEvent(appointment);
		}
	}

	/**
	 * Indicates whether there is a &quot;currently selected&quot; appointment at
	 * the moment.
	 *
	 * @return <code>true</code> if there is an appointment currently selected,
	 *         <code>false</code> if it is <code>null</code>.
	 * @see io.crs.hsys.client.fro.roomplan.widget.BookingManager.gwt.calendar.client.AppointmentManager#hasAppointmentSelected()
	 */
	public boolean hasAppointmentSelected() {
		return appointmentManager.hasAppointmentSelected();
	}

	/**
	 * Gets the currently selected item.
	 *
	 * @return the selected item.
	 */
	public Booking getSelectedAppointment() {
		return appointmentManager.getSelectedAppointment();
	}

	/**
	 * Tells whether the passed <code>appointment</code> is the currently selected
	 * appointment.
	 *
	 * @param appointment The appointment to test to be the currently selected
	 * @return <code>true</code> if there is a currently selected appointment and
	 *         happens to be equal to the passed <code>appointment</code>
	 * @see io.crs.hsys.client.fro.roomplan.widget.BookingManager.gwt.calendar.client.AppointmentManager#isTheSelectedAppointment(Booking)
	 */
	public boolean isTheSelectedAppointment(Booking appointment) {
		return appointmentManager.isTheSelectedAppointment(appointment);
	}

	/**
	 * Performs all layout calculations for the list of appointments and resizes the
	 * Calendar View appropriately.
	 */
	protected void refresh() {
		if (layoutSuspended) {
			layoutPending = true;
			return;
		}

		appointmentManager.resetHoveredAppointment();
		appointmentManager.sortAppointments();

		doLayout();
		doSizing();
	}

	public void doLayout() {
		view.doLayout();
	}

	public void doSizing() {
		view.doSizing();
	}

	public void onLoad() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			public void execute() {
				refresh();
			}
		});
	}

	/**
	 * Suspends the calendar from performing a layout. This can be useful when
	 * adding a large number of appointments at a time, since a layout is performed
	 * each time an appointment is added.
	 */
	public void suspendLayout() {
		layoutSuspended = true;
	}

	/**
	 * Allows the calendar to perform a layout, sizing the component and placing all
	 * appointments. If a layout is pending it will get executed when this method is
	 * called.
	 */
	public void resumeLayout() {
		layoutSuspended = false;

		if (layoutPending) {
			refresh();
		}
	}

	public RoomPlanSettings getSettings() {
		return this.settings;
	}

	public void setSettings(RoomPlanSettings settings) {
		this.settings = settings;
	}

	public void scrollToHour(int hour) {
		view.scrollToHour(hour);
	}

	public boolean selectPreviousAppointment() {

		boolean selected = appointmentManager.selectPreviousAppointment();
		if (selected) {
			fireSelectionEvent(getSelectedAppointment());
		}

		return selected;
	}

	public boolean selectNextAppointment() {
		boolean selected = appointmentManager.selectNextAppointment();
		if (selected) {
			fireSelectionEvent(getSelectedAppointment());
		}
		return selected;
	}

	@Override
	public void onDeleteKeyPressed() {
		view.onDeleteKeyPressed();
	}

	@Override
	public void onDoubleClick(Element element, Event event) {
		view.onDoubleClick(element, event);
	}

	@Override
	public void onDownArrowKeyPressed() {
		view.onDownArrowKeyPressed();
	}

	@Override
	public void onLeftArrowKeyPressed() {
		view.onLeftArrowKeyPressed();
	}

	@Override
	public void onMouseDown(Element element, Event event) {
		view.onSingleClick(element, event);
	}

	public void onMouseOver(Element element, Event event) {
		view.onMouseOver(element, event);
	}

	@Override
	public void onRightArrowKeyPressed() {
		view.onRightArrowKeyPressed();
	}

	@Override
	public void onUpArrowKeyPressed() {
		view.onUpArrowKeyPressed();
	}

	public void fireOpenEvent(Booking appointment) {
//		OpenEvent.fire(this, appointment);
	}

	public void fireDeleteEvent(Booking appointment) {

		// fire the event to notify the client
/*		boolean allow = DeleteEvent.fire(this, appointment);

		if (allow) {
			appointmentManager.removeAppointment(appointment);
			refresh();
		}*/
	}

	public void fireSelectionEvent(Booking appointment) {
		view.onAppointmentSelected(appointment);
//		SelectionEvent.fire(this, appointment);
	}

	public void fireMouseOverEvent(Booking appointment, Element element) {
		// we need to make sure we aren't re-firing the event
		// for the same appointment. This is a bit problematic,
		// because the mouse over event will fire for an appointment's
		// child elements (title label, footer, body, for example)
		// and will cause this method to be called with a null
		// appointment. this is a temp workaround, but basically
		// an appointment cannot be hovered twice in a row
		if (appointment != null && !appointment.equals(appointmentManager.getHoveredAppointment())) {
			appointmentManager.setHoveredAppointment(appointment);
//			MouseOverEvent.fire(this, appointment, element);
		}
	}

	public void fireTimeBlockClickEvent(Date date) {
//		TimeBlockClickEvent.fire(this, date);
	}

	public void fireCreateEvent(Booking appointment) {
/*		boolean allow = CreateEvent.fire(this, appointment);
		if (!allow) {
			appointmentManager.rollback();
			refresh();
		}*/
	}

	public void fireDateRequestEvent(Date date) {
//		DateRequestEvent.fire(this, date);
	}

	public void fireDateRequestEvent(Date date, Element clicked) {
//		DateRequestEvent.fire(this, date, clicked);
	}

	public void fireUpdateEvent(Booking appointment) {
		// refresh the appointment
		refresh();
		// fire the event to notify the client
/*		boolean allow = UpdateEvent.fire(this, appointment);

		if (!allow) {
			appointmentManager.rollback();
			refresh();
		}*/
	}
/*
	public HandlerRegistration addSelectionHandler(SelectionHandler<Appointment> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}

	public HandlerRegistration addDeleteHandler(DeleteHandler<Appointment> handler) {
		return addHandler(handler, DeleteEvent.getType());
	}

	public HandlerRegistration addMouseOverHandler(MouseOverHandler<Appointment> handler) {
		return addHandler(handler, MouseOverEvent.getType());
	}

	public HandlerRegistration addTimeBlockClickHandler(TimeBlockClickHandler<Date> handler) {
		return addHandler(handler, TimeBlockClickEvent.getType());
	}

	public HandlerRegistration addUpdateHandler(UpdateHandler<Appointment> handler) {
		return addHandler(handler, UpdateEvent.getType());
	}

	public HandlerRegistration addCreateHandler(CreateHandler<Appointment> handler) {
		return addHandler(handler, CreateEvent.getType());
	}

	public HandlerRegistration addOpenHandler(OpenHandler<Appointment> handler) {
		return addHandler(handler, OpenEvent.getType());
	}

	public HandlerRegistration addDateRequestHandler(DateRequestHandler<Date> handler) {
		return addHandler(handler, DateRequestEvent.getType());
	}
*/
	public void addToRootPanel(Widget widget) {
		getRootPanel().add(widget);
	}

	public void setRollbackAppointment(Booking appt) {
		appointmentManager.setRollbackAppointment(appt);
	}

	public void setCommittedAppointment(Booking appt) {
		appointmentManager.setCommittedAppointment(appt);
	}
}