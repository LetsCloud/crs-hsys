/**
 * 
 */
package io.crs.hsys.shared.util;

import java.util.Date;

/**
 * @author robi
 *
 */
public class DateUtils {

	public static final long SECOND_IN_MILISEC = 1000l;
	public static final long MINUTE_IN_MILISEC = 60000l;
	public static final long HOUR_IN_MILISEC = 3600000l;
	public static final long DAY_IN_MILISEC = 86400000l;
	public static final long WEEK_IN_MILISEC = 604800000l;
	public static final long MONTH_IN_MILISEC = 2592000000l;
	public static final long YEAR_IN_MILISEC = 31536000000l;

	public static int getYearOfDate(Date date) {
		return (int) (date.getTime() / YEAR_IN_MILISEC);
	}

	public static Date addDay(Date date, int day) {
		long temp = date.getTime() + (day * DAY_IN_MILISEC);
		return new Date(temp);
	}
}
