/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.widget;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

/**
 * Applies styles in a month view based on the currently selected theme. This class
 * provides a template method to consistently style the appointments in a month view. Subclasses
 * provide appropriate appointment styles based on a specific theme using methods
 * {@link #getViewAppointmentStyleForTheme(io.crs.hsys.client.fro.roomplan.widget.bradrydzewski.gwt.calendar.client.Booking)} and
 * {@link #getDefaultViewAppointmentStyleForTheme()}.
 *
 * @author Brad Rydzewski
 * @author Carlos D. Morales
 * @see com.bradrydzewski.gwt.calendar.theme.google.client.GoogleMonthViewStyleManager
 * @see com.bradrydzewski.gwt.calendar.theme.ical.client.ICalMonthViewStyleManager
 */
public abstract class MonthViewStyleManager {

    protected static final String APPOINTMENT_STYLE = "appointment";
    protected static final String APPOINTMENT_STYLE_SELECTED = "-selected";
    protected static final String APPOINTMENT_STYLE_MULTIDAY = "-multiday";

    protected static final String BACKGROUND_COLOR_STYLE_ATTRIBUTE = "backgroundColor";
    protected static final String BORDER_COLOR_STYLE_ATTRIBUTE = "borderColor";
    protected static final String COLOR_STYLE_ATTRIBUTE = "color";

    /**
     * Returns the appointment style appropriate to the passed appointment based on a specific theme.
     *
     * @param appointment An appointment to be displayed in the month view, should include a style to be used
     * @return The style to use with a theme, can be <code>null</code> if a default should be used
     * @see io.crs.hsys.client.fro.roomplan.widget.bradrydzewski.gwt.calendar.client.monthview.MonthViewStyleManager#getDefaultViewAppointmentStyleForTheme()
     */
    protected abstract ThemeAppointmentStyle getViewAppointmentStyleForTheme(Booking appointment);

    /**
     * Returns the default appointment style corresponding to the currently used theme. Subclasses
     * should provide a style to be used if no appropriate style can be identified
     * ({@link #getViewAppointmentStyleForTheme(io.crs.hsys.client.fro.roomplan.widget.bradrydzewski.gwt.calendar.client.Booking)} returns <code>null</code>).
     *
     * @return The style to use with a theme if no specific style can be identified with
     * {@link #getViewAppointmentStyleForTheme(io.crs.hsys.client.fro.roomplan.widget.bradrydzewski.gwt.calendar.client.Booking)}
     */
    protected abstract ThemeAppointmentStyle getDefaultViewAppointmentStyleForTheme();

    /**
     * Applies a style in the currently selected theme using the runtime-provided
     * theme styles defined by {@link #getViewAppointmentStyleForTheme(io.crs.hsys.client.fro.roomplan.widget.bradrydzewski.gwt.calendar.client.Booking)}
     * and {@link #getDefaultViewAppointmentStyleForTheme()}.
     *
     * @param widget The widget to style
     * @param selected Indicates if the appointment is the currently selected in the view
     */
    public void applyStyle(BookingWidget widget, boolean selected) {
        doApplyStyleInternal(widget, selected);
    }

    /**
     * Template method to consistently apply the styles to an appointment in the month view.
     *
     * @param widget The widget to style
     * @param selected Indicates if the appointment is the currently selected in the view
     */
    protected void doApplyStyleInternal(BookingWidget widget, boolean selected) {
        //Extract the Booking for later reference
        Booking appointment = widget.getAppointment();
        //Extract the DOM Element for later reference
        Element elem = widget.getElement();
        //Is MultiDay?
        boolean multiDay = appointment.isMultiDay() || appointment.isAllDay();

        //Lookup the style from the map
        ThemeAppointmentStyle style = getViewAppointmentStyleForTheme(appointment);

        //Determine Style Name
        String styleName = APPOINTMENT_STYLE;
        if (multiDay) styleName += APPOINTMENT_STYLE_MULTIDAY;
        if (selected) styleName += APPOINTMENT_STYLE_SELECTED;
        widget.setStylePrimaryName(styleName);

        //If no style is found, apply the default blue style
        //TODO: need to check for a custom style
        if (style == null)
            style = getDefaultViewAppointmentStyleForTheme();

        //Apply Single vs. Multi-day style
        if (multiDay) {

            DOM.setStyleAttribute(elem, BACKGROUND_COLOR_STYLE_ATTRIBUTE, style.getBackground());
            DOM.setStyleAttribute(elem, BORDER_COLOR_STYLE_ATTRIBUTE, style.getBorder());

        } else {

            DOM.setStyleAttribute(elem, COLOR_STYLE_ATTRIBUTE, style.getSelectedBorder());
        }

        //Apply style specific to selected appointments
        if (selected) {

            DOM.setStyleAttribute(elem, BORDER_COLOR_STYLE_ATTRIBUTE, style.getSelectedBorder());
        }
    }
}