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

	private ExceptionSubType exception;

	private String entiy;

	private String property;

	private Object value;

	public UniqueIndexConflictException(String entiy, String property, Object value) {
		super();
		this.entiy = entiy;
		this.property = property;
		this.value = value;
	}

	public UniqueIndexConflictException(ExceptionSubType exception, String entiy, String property, Object value) {
		this(entiy, property, value);
		this.exception = exception;
	}

	public ExceptionSubType getException() {
		return exception;
	}

	public void setException(ExceptionSubType exception) {
		this.exception = exception;
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
