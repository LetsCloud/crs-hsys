/**
 * 
 */
package io.crs.hsys.shared.cnst;

/**
 * Kalkulációs mód.
 * 
 * @author CR
 *
 */
public enum RateBase {
	/**
	 * Az összetevő ára szobára értendő.
	 */
	PER_ROOM,
	/**
	 * Az összetevő ára vendégre értendő.
	 */
	PER_GUEST,
	/**
	 * Az összetevő ára felnőtt vendégre értendő.
	 */
	PER_ADULT,
	/**
	 * Az összetevő ára tini vendégre értendő.
	 */
	PER_TEEN,
	/**
	 * Az összetevő ára gyermek vendégre értendő.
	 */
	PER_CHILD,
	/**
	 * Az összetevő ára csecsmőre értendő.
	 */
	PER_INFANT
}
