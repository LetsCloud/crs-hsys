/**
 * 
 */
package io.crs.hsys.shared.constans;

import java.io.Serializable;

/**
 * @author CR
 *
 */
public enum InventoryType implements Serializable {
	PHYS("PHYS"), GENE("GENE"), COMP("COMP");

	private final String text;

	/**
	 * @param text
	 */
	InventoryType(final String text) {
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
