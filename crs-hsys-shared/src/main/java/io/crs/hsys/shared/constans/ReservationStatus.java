/**
 * 
 */
package io.crs.hsys.shared.constans;

/**
 * Foglalási státusz.
 * 
 * @author CR
 *
 */
public enum ReservationStatus {
	/**
	 * Kilátásba helyezett.
	 */
	PROSPECT,
	/**
	 * Feltételes.
	 */
	TENTATIVE,
	/**
	 * Garantált.
	 */
	DEFINITIVE,
	/**
	 * Bejelentkezett.
	 */
	CHECKED_IN,
	/**
	 * Kijelentkezett.
	 */
	CHECKED_OUT,
	/**
	 * Hátralékos.
	 */
	OUTSTANDING,
	/**
	 * Törölve.
	 */
	CANCELLED,
	/**
	 * Szolgáltatásszámla.
	 */
	PAYMASTER,
	/**
	 * Out of order.
	 */
	OOO
}
