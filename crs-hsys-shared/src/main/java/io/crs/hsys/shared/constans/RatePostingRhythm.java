/**
 * 
 */
package io.crs.hsys.shared.constans;

/**
 * Terhelés ritmusa.
 * 
 * @author CR
 *
 */
public enum RatePostingRhythm {
	/**
	 * Minden éjszaka terhelendő.
	 */
	EVERY_NIGHT,
	/**
	 * Első éjszaka terhelendő.
	 */
	FIRST_NIGHT,
	/**
	 * Y éjszakától kezdődőne minden X-edik éjszaka.
	 */
	EVERY_X_NIGHTS_STARTING_NIGHT_Y,
	/**
	 * A hét adott napjain.
	 */
	NIGHTS_OF_THE_WEEK,
	/**
	 * Utolsó éjszaka terhelendő.
	 */
	LAST_NIGHT,
	/**
	 * Kivéve első éjszaka terhelendő.
	 */
	EXCEPT_FIRST_NIGHT,
	/**
	 * Kivéve utolsó éjszaka.
	 */
	EXCEPT_LAST_NIGHT,
	/**
	 * Kivéve első és utolsó éjszaka.
	 */
	EXCEPT_FIRST_AND_LAST_NIGHT
}
