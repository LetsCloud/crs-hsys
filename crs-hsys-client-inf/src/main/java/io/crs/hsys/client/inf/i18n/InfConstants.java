/**
 * 
 */
package io.crs.hsys.client.inf.i18n;

import java.util.Map;

import com.google.gwt.i18n.client.Constants;

/**
 * @author CR
 *
 */
public interface InfConstants extends Constants {
	public static final String P_TODAY = "pToday";
	public static final String P_YESTERDAY = "pYesterday";
	public static final String P_TOMORROW = "pTomorrow";
	public static final String P_THIS_WEEK = "pThisWeek";
	public static final String P_LAST_WEEK = "pLastWeek";
	public static final String P_NEXT_WEEK = "pNextWeek";
	public static final String P_THIS_MONTH = "pThisMonth";
	public static final String P_LAST_MONTH = "pLastMonth";
	public static final String P_NEXT_MONTH = "pNextMonth";
	public static final String P_THIS_QUART = "pThisQuart";
	public static final String P_LAST_QUART = "pLastQuart";
	public static final String P_NEXT_QUART = "pNextQuart";
	public static final String P_THIS_HALF = "pThisHalf";
	public static final String P_LAST_HALF = "pLastHalf";
	public static final String P_NEXT_HALF = "pNextHalf";
	public static final String P_THIS_YEAR = "pThisYear";
	public static final String P_LAST_YEAR = "pLastYear";
	public static final String P_NEXT_YEAR = "pNextYear";

	@DefaultStringMapValue({ P_TODAY, "Today", P_YESTERDAY, "Yesterday", P_TOMORROW, "Tomorrow", P_THIS_WEEK,
			"This week", P_LAST_WEEK, "Previous week", P_NEXT_WEEK, "Next week", P_THIS_MONTH, "This month",
			P_LAST_MONTH, "Previous month", P_NEXT_MONTH, "Next month", P_THIS_QUART, "This quarter", P_LAST_QUART,
			"Previous quarter", P_NEXT_QUART, "Next quarter", P_THIS_HALF, "This half-year", P_LAST_HALF,
			"Previous half-year", P_NEXT_HALF, "Next half-year", P_THIS_YEAR, "This year", P_LAST_YEAR, "Previous year",
			P_NEXT_YEAR, "Next year" })
	Map<String, String> gpsPeriodMap();

	public static final String P2_NEXT_DAY = "p2NextDay";
	public static final String P2_PREV_DAY = "p2PrevDay";
	public static final String P2_NEXT_WEEK = "p2NextWeek";
	public static final String P2_PREV_WEEK = "p2PrevWeek";
	public static final String P2_NEXT_MONTH = "p2NextMonth";
	public static final String P2_PREV_MONTH = "p2PrevMonth";
	public static final String P2_NEXT_QUART = "p2NextQuart";
	public static final String P2_PREV_QUART = "p2PrevQuart";
	public static final String P2_NEXT_HALF = "p2NextHalf";
	public static final String P2_PREV_HALF = "p2PrevHalf";
	public static final String P2_NEXT_YEAR = "p2NextYear";
	public static final String P2_PREV_YEAR = "p2PrevYear";

	@DefaultStringMapValue({ P2_NEXT_DAY, "Next day", P2_PREV_DAY, "Previous day", P2_NEXT_WEEK, "Next week",
			P2_PREV_WEEK, "Previous week", P2_NEXT_MONTH, "Next month", P2_PREV_MONTH, "Previous month", P2_NEXT_QUART,
			"Next quarter", P2_PREV_QUART, "Previous quarter", P2_NEXT_HALF, "Next half-year", P2_PREV_HALF,
			"Previous half-year", P2_NEXT_YEAR, "Next year", P2_PREV_YEAR, "Previous year" })
	Map<String, String> gpsPeriodMap2();

	public static final String SD = "sd";
	public static final String SW = "sw";
	public static final String SM = "sm";
	public static final String SQ = "sq";
	public static final String SH = "sh";
	public static final String SY = "ss";

	@DefaultStringMapValue({ SD, "Daily", SW, "Weekly", SM, "Monthly", SQ, "Quarterly", SH, "Half-yearly", SY,
			"Yearly" })
	Map<String, String> gpsScaleMap();

}
