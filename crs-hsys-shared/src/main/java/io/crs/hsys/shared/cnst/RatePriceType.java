/**
 * 
 */
package io.crs.hsys.shared.cnst;

/**
 * @author robi
 *
 */
public enum RatePriceType {
	DOUBLE("DOUBLE"), SINGLE("SINGLE"), EXT_ADULT("EXT_ADULT"), TINY("TINY"), CHILD("CHILD");

	private final String text;

	/**
	 * @param text
	 */
	RatePriceType(final String text) {
		this.text = text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return text;
	}
}
