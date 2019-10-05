/**
 * 
 */
package io.crs.hsys.shared.cnst;

/**
 * @author robi
 *
 */
public enum RateUpdateOperation {
	NO_CHANGE("NO_CHANGE"), SET_AMOUNT("SET_AMOUNT"), INC_BY_AMOUNT("INC_BY_AMOUNT"), DEC_BY_AMOUNT("DEC_BY_AMOUNT"),
	INC_BY_PERCENT("INC_BY_PERCENT"), DEC_BY_PERCENT("DEC_BY_PERCENT");

	private final String text;

	/**
	 * @param text
	 */
	RateUpdateOperation(final String text) {
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
