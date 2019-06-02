/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.widget;

/**
 * Defines the style attribute values that will vary with a theme
 * when a particular theme+style is applied. The currently active
 * {@link com.bradrydzewski.gwt.calendar.client.monthview.MonthViewStyleManager} or
 * {@link com.bradrydzewski.gwt.calendar.client.dayview.DayViewStyleManager} will use the
 * strings in the theme style to style the elements in the view.
 *
 * @author Brad Rydzewski
 * @author Carlos D. Morales
 *
 * @see com.bradrydzewski.gwt.calendar.theme.google.client.GoogleAppointmentStyle
 * @see com.bradrydzewski.gwt.calendar.theme.ical.client.ICalAppointmentStyle
 */
public interface ThemeAppointmentStyle {

    public String getBackgroundHeader();

    public String getBackground();

    public String getSelectedBorder();

    public String getHeaderText();

    public String getSelectedBackgroundImage();

    public String getBorder();
}