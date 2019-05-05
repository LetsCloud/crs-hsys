/**
 * 
 */
package io.crs.hsys.client.core.message;

/**
 * @author robi
 *
 */
public enum MessageButtonType {
	CLOSE("Close"), YES("Yes"), NO("No");

	private String label;

	private MessageButtonType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
