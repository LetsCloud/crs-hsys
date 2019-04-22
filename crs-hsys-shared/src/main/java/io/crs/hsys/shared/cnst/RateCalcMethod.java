/**
 * 
 */
package io.crs.hsys.shared.cnst;

/**
 * Összetevők kalkulálásának módja
 * 
 * @author CR
 *
 */
public enum RateCalcMethod {
	/**
	 * Összeg alapú. Az összetevőben megaqdott érték felhasználásval.
	 */
	AMOUNT,
	/**
	 * Százalék alapú. Az összetevő értékes az összesen ár megadott százaléka.
	 */
	PERCENT
}
