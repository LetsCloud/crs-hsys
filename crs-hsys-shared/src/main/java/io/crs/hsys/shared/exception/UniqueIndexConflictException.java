/**
 * 
 */
package io.crs.hsys.shared.exception;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class UniqueIndexConflictException extends Exception {

	private String entiy;

	private String property;

	private Object value;

	public UniqueIndexConflictException(String entiy, String property, Object value) {
		super();
		this.entiy = entiy;
		this.property = property;
		this.value = value;
	}

	public String getEntiy() {
		return entiy;
	}

	public String getProperty() {
		return property;
	}

	public Object getValue() {
		return value;
	}
}
