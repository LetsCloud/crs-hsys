package io.crs.hsys.client.fro.roomplan.widget;

import java.util.Date;

/**
 * Contains utility methods involving dates. This class should remain GWT-API
 * independent.
 *
 * @author Carlos D. Morales
 */
public class DateUtils {

	/**
	 * Number of milliseconds in a day.
	 */
	public static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
	/**
	 * Defines the Number of Days in a Week.
	 */
	public static final int DAYS_IN_A_WEEK = 7;
	private static final int ISO_THURSDAY = 4;
	private static final int MAX_DAY_OF_WEEK = 6;
	    
	private static int dayStartsAt = 0;
   
   public static void setDayStartsAt(int start) {
	   dayStartsAt = start;
   }
   
   public static int getDayStartsAt() {
	   return dayStartsAt;
   }
   
   /**
    * Provides a <code>null</code>-safe way to return the number of milliseconds
    * on a <code>date</code>.
    *
    * @param date The date whose value in milliseconds will be returned
    * @return The number of milliseconds in <code>date</code>, <code>0</code>
    *         (zero) if <code>date</code> is <code>null</code>.
    */
   private static long safeInMillis(Date date) {
      return date != null ? date.getTime() : 0;
   }

   /**
    * Returns the number of days between the passed dates.
    *
    * @param endDate   The upper limit of the date range
    * @param startDate The lower limit of the date range
    * @return The number of days between <code>endDate</code> and
    *         <code>starDate</code> (inclusive)
    */
   @SuppressWarnings(value = "deprecation")
   public static int differenceInDays(Date endDate, Date startDate) {
      int difference = 0;
      if (!areOnTheSameDay(endDate, startDate)) {
         int endDateOffset = -(endDate.getTimezoneOffset() * 60 * 1000);
         long endDateInstant = endDate.getTime() + endDateOffset;
         int startDateOffset = -(startDate.getTimezoneOffset() * 60 * 1000);
         long startDateInstant = startDate.getTime() + startDateOffset;
         double differenceDouble =
            (double) Math.abs(endDateInstant - startDateInstant) /
               (double) MILLIS_IN_A_DAY;
         differenceDouble = Math.max(1.0D, differenceDouble);
         difference = (int) differenceDouble;
      }
      return difference;
   }

   /**
    * Returns the full year (4-digits) of the passed <code>date</code>.
    * @param date The date whose year will be returned
    * @return The full year of the passed <code>date</code>.
    */
   @SuppressWarnings("deprecation")
   public static int year(Date date) {
      return 1900 + date.getYear();
   }

   /**
    * Moves a date <code>shift</code> days. A clone of <code>date</code> to
    * prevent undesired object modifications.
    *
    * @param date  The date to shift
    * @param shift The number of days to push the original <code>date</code>
    *              <em>forward</em>
    * @return A <em>new</em> date pushed <code>shift</code> days forward
    */
   @SuppressWarnings("deprecation")
   public static Date shiftDate(Date date, int shift) {
      Date result = (Date) date.clone();
      result.setDate(date.getDate() + shift);
      return result;
   }

   /**
    * Resets the date to have no time modifiers (hours, minutes, seconds.)
    *
    * @param date The date to reset
    */
   @SuppressWarnings("deprecation")
   public static void resetTime(Date date) {
      long milliseconds = safeInMillis(date);
      milliseconds = (milliseconds / 1000) * 1000;
      date.setTime(milliseconds);
      date.setHours(DateUtils.getDayStartsAt());
      date.setMinutes(0);
      date.setSeconds(0);
   }

   /**
    * Indicates whether two dates are on the same date by comparing their day,
    * month and year values. Time values such as hours and minutes are not
    * considered in this comparison.
    *
    * @param dateOne The first date to test
    * @param dateTwo The second date to test
    * @return <code>true</code> if both dates have their <code>date</code>,
    *         <code>month</code> and <code>year</code> properties with the
    *         <em>exact</em> same values (whatever they are)
    */
   @SuppressWarnings("deprecation")
	public static boolean areOnTheSameDay(Date dateOne, Date dateTwo) {
		Date end;
		Date start;
		
		if (dateTwo.getTime() > dateOne.getTime()) {
			end = (Date) dateTwo.clone();
			start = (Date) dateOne.clone();
		} else {
			start = (Date) dateTwo.clone();
			end = (Date) dateOne.clone();
		}

		if (DateUtils.dayStartsAt != 0) {
			long time = start.getTime()
			- (1000 * 60 * 60 * DateUtils.dayStartsAt);
			start = new Date(time);
			
			time = end.getTime()
					- (1000 * 60 * 60 * DateUtils.dayStartsAt);
			end = new Date(time);
		}

		return start.getDate() == end.getDate()
				&& start.getMonth() == end.getMonth()
				&& start.getYear() == end.getYear();
	}

   /**
    * Indicates whether two dates are on the same month of the same year.
    *
    * @param dateOne The first date of the comparison
    * @param dateTwo The second date of the comparison
    * @return <code>true</code> if both dates have the same year and month,
    * <code>false</code> otherwise
    */
   @SuppressWarnings("deprecation")
   public static boolean areOnTheSameMonth(Date dateOne, Date dateTwo) {
	   Date end = (Date)dateTwo.clone();
	   
	  if (DateUtils.dayStartsAt != 0) {
		  long time = end.getTime() - (1000 * 60 * 60 * DateUtils.dayStartsAt);
		  end = new Date(time);
	  }
	   
      return dateOne.getYear() == end.getYear() &&
            dateOne.getMonth() == end.getMonth();
   }

   /**
    * Returns a clone of the <code>anyDayInMonth</code> date set to the
    * <em>first</em> day of whatever its month is.
    *
    * @param anyDayInMonth Any date on a month+year
    * @return A clone of the <code>anyDayInMonth</code> date, representing the
    *         first day of that same month and year
    */
   @SuppressWarnings("deprecation")
   public static Date firstOfTheMonth(Date anyDayInMonth) {
      Date first = (Date) anyDayInMonth.clone();
      first.setDate(1);
      return first;
   }

   /**
    * Moves the date of the passed object to be one day after whatever date it
    * has.
    *
    * @param date An object representing a date
    * @return The day
    */
   @SuppressWarnings("deprecation")
   public static Date moveOneDayForward(Date date) {
      date.setDate(date.getDate() + 1);
      return date;
   }

   /**
    * Returns the date corresponding to the first day of the next month relative
    * to the passed <code>date</code>.
    *
    * @param date The reference date
    * @return The first day of the next month, if the month of the passed date
    *         corresponds to december (<code>11</code>) <em>one</em> will be
    *         added to the year of the returned date.
    */
   @SuppressWarnings("deprecation")
   public static Date firstOfNextMonth(Date date) {
      Date firstOfNextMonth = null;
      if (date != null) {
         int year = date.getMonth() == 11 ? date.getYear() + 1 : date.getYear();
         firstOfNextMonth = new Date(year, date.getMonth() + 1 % 11, 1);
      }
      return firstOfNextMonth;
   }

   /**
    * Returns a day <em>exactly</em> 24 hours before the instant passed as
    * <code>date</code>. // TODO: This logic should address the time zone
    * offset
    *
    * @param date A point in time from which the moment 24 hours before will be
    *             calculated
    * @return A new object <em>24</em> hours prior to the passed
    *         <code>date</code>
    */
   public static Date previousDay(Date date) {
      return new Date(date.getTime() - MILLIS_IN_A_DAY);
   }


   /**
    * Copies the hours, minutes and seconds in the <code>source</code> date into
    * the <code>target</code> date object.
    *
    * @param source The date with the hour, minutes and seconds to be copied
    * @param target The date whose time fields will be set
    */
   @SuppressWarnings("deprecation")
   public static void copyTime(Date source, Date target) {
      target.setHours(source.getHours());
      target.setMinutes(source.getMinutes());
      target.setSeconds(source.getSeconds());
   }

   /**
    * Returns the amount of minutes elapsed since the beginning of the passed
    * <code>day</code>.
    *
    * @param day The day to calculate the elapsed minutes
    * @return The number of minutes since <code>day</code> started
    */
   @SuppressWarnings("deprecation")
   public static int minutesSinceDayStarted(Date day) {
      int minutes = (day.getHours() - dayStartsAt) * 60 + day.getMinutes();
      if (day.getHours() < dayStartsAt) {
    	  minutes = ((24 - dayStartsAt) + day.getHours()) * 60 + day.getMinutes();
      }
      return minutes;
   }

    /**
     * Creates a new date with whatever date/time the passed <code>date</code>
     * object represents.
     *
     * @param date The source date
     * @return A new date object representing the same date and time as the passed
     * object
     */
	public static Date newDate(Date date) {
       Date result = null;
		if (date != null) {
           result = new Date(date.getTime());
       }
       return result;
   }
   
   @SuppressWarnings("deprecation")
   public static boolean isWeekend(final Date day) {
	   return day.getDay()==0 || day.getDay()==6;
   }
   
	@SuppressWarnings("deprecation")
	public static Integer weekday(final int firstDayOfWeek, final Date date) {
		int weekday = date.getDay();
		if ((firstDayOfWeek == 1) && (weekday == 0)) {
			weekday = 7;
		}
		return weekday;
	}

    @SuppressWarnings("deprecation")
	public static int calendarWeekIso(final Date inputDate) {
    	int dayOfWeek = inputDate.getDay();
        
        // ISO 8601 use weeks that start on monday so we use
        // MON = 1, TUE = 2,...SUN = 7;
        if (dayOfWeek == 0) {
        	dayOfWeek = 7;
        }
        
        // Find nearest Thursday (defines the week in ISO 8601).
        int thursdayDiff = 4 - dayOfWeek;
        Date nearestThursday = new Date(inputDate.getTime() + thursdayDiff * MILLIS_IN_A_DAY);
        
        Date firstOfJanuary = new Date(nearestThursday.getYear(), 0, 1);
        
        return (int) (((nearestThursday.getTime() - firstOfJanuary.getTime()) / MILLIS_IN_A_DAY) / 7 + 1);
    }

	/**
	 * Adds or subtracts the specified amount of days for the given Date.
	 * 
	 * @param date
	 *            A point in time
	 * @param days
	 *            Number of days to add or substract
	 * @return A new object <em>days</em> after to the passed <code>date</code>
	 */
	public static Date addDays(final Date date, final int days) {
		return new Date(date.getTime() + (days * MILLIS_IN_A_DAY));
	}
	
	/**
	 * Adds or subtracts the specified amount of weeks for the given Date.
	 * 
	 * @param date
	 *            A point in time
	 * @param weeks
	 *            Number of weeks to add or substract
	 * @return A new object <em>weeks</em> after to the passed <code>date</code>
	 */
	public static Date addWeeks(final Date date, final int weeks) {
		return new Date(date.getTime() + (weeks * (MILLIS_IN_A_DAY * DAYS_IN_A_WEEK)));
	}
   
	/**
	 * Adds or subtracts the specified amount of months for the given Date.
	 * 
	 * @param date
	 *            A point in time
	 * @param months
	 *            Number of months to add or substract
	 * @return A new object <em>weeks</em> after to the passed <code>date</code>
	 */
	public static Date addMonths(final Date date, final int months) {
		return new Date(date.getTime() + (months * (MILLIS_IN_A_DAY * DAYS_IN_A_WEEK)));
	}
	
	/**
	 * Returns the total number of days between <code>start</code> and
	 * <code>end</code>. The calculation is rounded to take in account
	 * the saving day calculation.
	 * 
	 * @param start
	 *            The first day in the period
	 * @param end
	 *            The last day in the period
	 * @return The number of days between <code>start</code> and
	 *         <code>end</code>, the minimum difference being one (
	 *         <code>1</code>)
	 */
	public static int daysInPeriod(final Date start, final Date end) {
		long diff = end.getTime() - start.getTime();
		double days = ((double)diff / MILLIS_IN_A_DAY) + 1;

		Long result = Math.round(days);
		return result.intValue();
	}

	/**
	 * Calculates the amount of days in the month the
	 * @param day
	 * @return
	 */
	public static int daysInMonth(final Date date) {
		return 32 - new Date(date.getYear(), date.getMonth(), 32).getDate();	
	}
}