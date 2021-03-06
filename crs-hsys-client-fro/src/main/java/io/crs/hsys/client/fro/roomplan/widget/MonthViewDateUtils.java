package io.crs.hsys.client.fro.roomplan.widget;

import static io.crs.hsys.client.fro.roomplan.widget.DateUtils.areOnTheSameMonth;
import static io.crs.hsys.client.fro.roomplan.widget.DateUtils.firstOfNextMonth;
import static io.crs.hsys.client.fro.roomplan.widget.DateUtils.previousDay;

import java.util.Date;

import io.crs.hsys.client.fro.roomplan.widget.DateUtils;

/**
 * Contains date-related utilities with logic required to generate the
 * {@link com.bradrydzewski.gwt.calendar.client.monthview.MonthView}.
 *
 * @author Carlos D. Morales
 */
public class MonthViewDateUtils {

	/**
	 * Calculates the first date that should be displayed in a month view. Depending
	 * on the year and month, sometimes, viewing at a month will show days from the
	 * previous month in the first week.
	 *
	 * @param dayInMonth     Any day in the month that is being visualized in a
	 *                       month view
	 * @param firstDayOfWeek The day the weeks start on the month view, Sunday is
	 *                       <code>0</code>, Monday is <code>1</code>, etc.
	 * @return The first date that should appear on the first week of a month view
	 */
	@SuppressWarnings("deprecation")
	public static Date firstDateShownInAMonthView(Date dayInMonth, int firstDayOfWeek) {
		Date date = DateUtils.firstOfTheMonth(dayInMonth);
		int firstDayOffset = firstDayOfWeek + date.getDate() - date.getDay();
		date.setDate(firstDayOffset);
		if (areOnTheSameMonth(date, dayInMonth) && date.getDate() > 1) {
			date.setDate(firstDayOffset - 7);
		}
		return date;
	}

	/**
	 * Dynamically calculates the number of rows required to display all the days in
	 * a month.
	 *
	 * @param dayInMonth     Any day in the month that is being visualized through
	 *                       the month view
	 * @param firstDayOfWeek The day the weeks start on the month view, Sunday is
	 *                       <code>0</code>, Monday is <code>1</code>, etc.
	 * @return The number of rows (which represent weeks in the month view) required
	 *         to display all days in the month view
	 */
	@SuppressWarnings("deprecation")
	public static int monthViewRequiredRows(Date dayInMonth, int firstDayOfWeek) {
		int requiredRows = 5;

		Date firstOfTheMonthClone = (Date) dayInMonth.clone();
		firstOfTheMonthClone.setDate(1);

		Date firstDayInCalendar = firstDateShownInAMonthView(dayInMonth, firstDayOfWeek);

		if (firstDayInCalendar.getMonth() != firstOfTheMonthClone.getMonth()) {
			Date lastDayOfPreviousMonth = previousDay(firstOfTheMonthClone);
			int prevMonthOverlap = daysInPeriod(firstDayInCalendar, lastDayOfPreviousMonth);

			Date firstOfNextMonth = firstOfNextMonth(firstOfTheMonthClone);

			int daysInMonth = daysInPeriod(firstOfTheMonthClone, previousDay(firstOfNextMonth));

			if (prevMonthOverlap + daysInMonth > 35) {
				requiredRows = 6;
			}
		}
		return requiredRows;
	}

	/**
	 * Returns the total number of days between <code>start</code> and
	 * <code>end</code>.
	 *
	 * @param start The first day in the period
	 * @param end   The last day in the period
	 * @return The number of days between <code>start</code> and <code>end</code>,
	 *         the minimum difference being one (<code>1</code>)
	 */
	@SuppressWarnings("deprecation")
	private static int daysInPeriod(Date start, Date end) {
		if (start.getMonth() != end.getMonth()) {
			throw new IllegalArgumentException("Start and end dates must be in the same month.");
		}
		return 1 + end.getDate() - start.getDate();
	}
}