/**
 * 
 */
package io.crs.hsys.shared.cnst.cube;

import java.io.Serializable;
import java.util.EnumSet;

/**
 * @author CR
 *
 */
public enum QueryPeriodType implements Serializable {

	/**
	 * Az időszak a globális dátum kiválasztóból kerül betöltésre.
	 */
	GPS,

	/**
	 * Átlagol.
	 */
	CURR_DAY,

	/**
	 * Eltérés az előzőtől.
	 */
	PREV_DAY,

	/**
	 * Eltérés a következőtől.
	 */
	NEXT_DAY,

	/**
	 * Átlagol.
	 */
	CURR_MONTH,

	/**
	 * Eltérés az előzőtől.
	 */
	PREV_MONTH,

	/**
	 * Eltérés a következőtől.
	 */
	NEXT_MONTH;

	public static final EnumSet<QueryPeriodType> ALL = EnumSet.of(GPS, CURR_DAY, NEXT_DAY, PREV_DAY);

}
