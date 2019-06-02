/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.widget;

import java.util.Date;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Label;

/**
 * Abstract base class defining the operations to render a calendar and
 * user-input dispatching methods. <p/> <p></p>Subclasses will provide the
 * details of rendering the calendar to visualize by day (Day View), monthly
 * (month view), agenda (list view) and the logic implementing the user-input
 * event processing.
 *
 * @author Brad Rydzewski
 */
public abstract class AbstractRoomPlanView {

    /**
     * Calendar widget bound to the view.
     *
     * @see CalendarWidget
     */
    protected RoomPlanWidget roomPlanWidget = null;

    /**
     * Number of days the calendar should display at a given time, 3 by
     * default.
     */
    private int displayedDays = 3;
    
    /**
     * Attaches this view to the provided {@link CalendarWidget}.
     *
     * @param calendarWidget The interactive widget containing the calendar
     */
    public void attach(RoomPlanWidget roomPlanWidget) {
        this.roomPlanWidget = roomPlanWidget;
    }

    /**
     * Detaches this view from the currently associated {@link CalendarWidget}.
     * TODO: The CalendarWidget might still have a reference to this
     * CalendarView, is that correct??
     */
    public void detatch() {
        roomPlanWidget = null;
    }

    /**
     * Returns the CSS style name of this calendar view.
     *
     * @return The CSS style that should be used when rendering this calendar
     *         view
     */
    public abstract String getStyleName();

    public void doSizing() {
    }

    public abstract void doLayout();

    /**
     * Configures this view component's  currently selected
     * <code>appointment</code>. Notification to the calendar widget associated
     * is optional and controlled with the <code>fireEvent</code> flag.
     *
     * @param appointment The appointment in the calendar in-memory model to be
     *                    configured as the currently selected
     * @param fireEvent   Indicates whether a selection event should be
     *                    triggered by the parent widget so that it informs its
     *                    set of registered listeners about the change
     */
//    public void setSelectedAppointment(Appointment appointment,
//                                       boolean fireEvent) {
//        calendarWidget.setSelectedAppointment(appointment);
//        if (fireEvent) {
//            calendarWidget.fireSelectionEvent(appointment);
//        }
//    }

    /**
     * Configures this view component's currently selected
     * <code>appointment</code> and notifies widget about the change in the
     * model state.
     *
     * @param appointment The appointment in the calendar in-memory model to be
     *                    configured as the currently selected
     */
//    public void setSelectedAppointment(Appointment appointment) {
//        setSelectedAppointment(appointment, true);
//    }

    /**
     * Returns the configured number of days the calendar should display at a
     * given time.
     *
     * @return The number of days this calendar view should display at a given
     *         time
     */
    public int getDisplayedDays() {
        return displayedDays;
    }

    /**
     * Sets the configured number of days the calendar should display at a given
     * time.
     *
     * @param displayedDays The number of days this calendar view should display
     *                      at a given time
     */
    public void setDisplayedDays(int displayedDays) {
        this.displayedDays = displayedDays;
    }

    /* on clicks */
    public abstract void onDoubleClick(Element element, Event event);
    
    public abstract void onSingleClick(Element element, Event event);

    public abstract void onMouseOver(Element element, Event event);
    
    /**
     * Processes user {@link com.google.gwt.event.dom.client.KeyCodes#KEY_DELETE}
     * and {@link com.google.gwt.event.dom.client.KeyCodes.KEY_BACKSPACE}
     * keystrokes. The <code>CalendarView</code> implementation is empty so that
     * subclasses are not forced to implement it if no specific logic is needed
     * for {@link com.google.gwt.event.dom.client.KeyCodes#KEY_DELETE} or
     * {@link com.google.gwt.event.dom.client.KeyCodes#KEY_BACKSPACE} keystrokes.
     */
    public void onDeleteKeyPressed() {
    }

    /**
     * Processes user {@link com.google.gwt.event.dom.client.KeyCodes#KEY_UP}
     * keystrokes. The <code>CalendarView</code> implementation is empty so that
     * subclasses are not forced to implement it if no specific logic is needed
     * for {@link com.google.gwt.event.dom.client.KeyCodes#KEY_UP} keystrokes.
     */
    public void onUpArrowKeyPressed() {
    }

    /**
     * Processes user {@link com.google.gwt.event.dom.client.KeyCodes#KEY_DOWN}
     * keystrokes. The <code>CalendarView</code> implementation is empty so that
     * subclasses are not forced to implement it if no specific logic is needed
     * for {@link com.google.gwt.event.dom.client.KeyCodes#KEY_DOWN}
     * keystrokes.
     */
    public void onDownArrowKeyPressed() {
    }

    /**
     * Processes user {@link com.google.gwt.event.dom.client.KeyCodes#KEY_LEFT}
     * keystrokes. The <code>CalendarView</code> implementation is empty so that
     * subclasses are not forced to implement it if no specific logic is needed
     * for {@link com.google.gwt.event.dom.client.KeyCodes#KEY_LEFT}
     * keystrokes.
     */
    public void onLeftArrowKeyPressed() {
    }

    /**
     * Processes user {@link com.google.gwt.event.dom.client.KeyCodes#KEY_RIGHT}
     * keystrokes. The <code>CalendarView</code> implementation is empty so that
     * subclasses are not forced to implement it if no specific logic is needed
     * for {@link com.google.gwt.event.dom.client.KeyCodes#KEY_RIGHT}
     * keystrokes.
     */
    public void onRightArrowKeyPressed() {
    }

    public abstract void onAppointmentSelected(Booking appt);
    
    public final void selectAppointment(Booking appt) {
        roomPlanWidget.setSelectedAppointment(appt, true);
    }

    public final void selectNextAppointment() {
    	roomPlanWidget.selectNextAppointment();
    }
    
    public final void selectPreviousAppointment() {
    	roomPlanWidget.selectPreviousAppointment();
    }
    
    public final void updateAppointment(Booking toAppt) {
    	roomPlanWidget.fireUpdateEvent(toAppt);
    }
    
    public final void deleteAppointment(Booking appt) {
    	roomPlanWidget.fireDeleteEvent(appt);
    }
    
    public final void openAppointment(Booking appt) {
    	roomPlanWidget.fireOpenEvent(appt);
    }
    
    public final void createAppointment(Booking appt) {
    	createAppointment(appt.getStart(), appt.getEnd());
    }
    
    public final void createAppointment(Date start, Date end) {
    	roomPlanWidget.fireTimeBlockClickEvent(start);
    }
    
    public void scrollToHour(int hour) {
    	
    }
    
	public RoomPlanSettings getSettings() {
		return roomPlanWidget.getSettings();
	}

	public void setSettings(RoomPlanSettings settings) {
		roomPlanWidget.setSettings(settings);
	}

	protected void addDayClickHandler(final Label dayLabel, final Date day) {
		dayLabel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
//				fireSelectedDay(day);
			}
		});
	}
	
	protected void addWeekClickHandler(final Label weekLabel, final Date day) {
		weekLabel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//fireSelectedWeek(day);
			}
		});
	}
	
	public void fireEvent(GwtEvent<?> event) {
		roomPlanWidget.fireEvent(event);
	}
}