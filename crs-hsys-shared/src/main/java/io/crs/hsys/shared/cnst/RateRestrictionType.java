/**
 * 
 */
package io.crs.hsys.shared.cnst;

/**
 * @author robi
 *
 */
public enum RateRestrictionType {

	/**
	 * No restrictions
	 */
	NORST,

	/**
	 * Closed sales
	 * <p>
	 * Hotel rooms made unavailable from sales. Such a closed out could be for a
	 * variety of reasons and applied to individual market segments.
	 * </p>
	 */
	CLOSED,

	/**
	 * Closed to Arrival
	 * <p>
	 * CTA stands for Closed to Arrival. It is a yield tool used to close days from
	 * reservations arriving on a particular day.
	 * </p>
	 */
	CTA,

	/**
	 * Closed to Departure
	 * <p>
	 * CTD stands for Closed to Departure. It is a specific set of days guests
	 * cannot make their reservation for with this date as check-out.
	 * </p>
	 */
	CTD,

	/**
	 * Minimum Length of Stay
	 */
	MIN_LOS,

	/**
	 * Maximum Length of Stay
	 */
	MAX_LOS,

	/**
	 * Minimum Stay Through
	 * <p>
	 * Stay Through works by looking at each night that the guest wants to book, it
	 * takes the highest number from all the dates and checks if this is higher than
	 * the booking length. Arrival type works by only looking at the first night
	 * restriction of the booking dates.
	 * </p>
	 */
	MIN_ST,

	/**
	 * Minimum Advance Booking
	 */
	MIN_AB,

	/**
	 * Maximum Advance Booking
	 */
	MAX_AB;

}
