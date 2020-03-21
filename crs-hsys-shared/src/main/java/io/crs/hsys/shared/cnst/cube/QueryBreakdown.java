/**
 * 
 */
package io.crs.hsys.shared.cnst.cube;

import java.io.Serializable;

/**
 * @author CR
 *
 */
public enum QueryBreakdown implements Serializable {
	/**
	 * Ninics részletezés.
	 */
	NONE,

	/**
	 * Napi részletezés.
	 */
	DAILY,

	/**
	 * Heti részletezés.
	 */
	WEEKLY,

	/**
	 * Havi részletezés.
	 */
	MONTHLY,

	/**
	 * Negyedéves részletezés.
	 */
	QUARTERLY,

	/**
	 * Féléves részletezés.
	 */
	HALFYEARLY,

	/**
	 * Éves részletezés.
	 */
	YEARLY;
}
