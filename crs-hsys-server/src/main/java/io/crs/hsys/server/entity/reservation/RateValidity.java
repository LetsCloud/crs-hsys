/**
 * 
 */
package io.crs.hsys.server.entity.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author robi
 *
 */
public class RateValidity {

	/**
	 * A hét napjai.
	 * 
	 * @author CR
	 *
	 */
	public enum DayOfWeek {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
	}

	/**
	 * Érvényesség kezdete.
	 */
	private Date fromDate;

	/**
	 * Érvényesség vége, beleértve.
	 */
	private Date toDate;

	/**
	 * Érvényes a listában szereplő napokon. Üres lista esetén az összes nap
	 * érévnyes.
	 */
	private List<DayOfWeek> dayOfWeeks = new ArrayList<DayOfWeek>();

	public RateValidity(Date fromDate, Date toDate, List<DayOfWeek> dayOfWeeks) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.dayOfWeeks = dayOfWeeks;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public List<DayOfWeek> getDayOfWeeks() {
		return dayOfWeeks;
	}

	public void setDayOfWeeks(List<DayOfWeek> dayOfWeeks) {
		this.dayOfWeeks = dayOfWeeks;
	}

}
