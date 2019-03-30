/**
 * 
 */
package io.crs.hsys.server.entity;

import io.crs.hsys.shared.exception.ExceptionSubType;

/**
 * @author robi
 *
 */
public class UniqueKey {
	private String property;
	private Object value;
	private ExceptionSubType exception;

	public UniqueKey(String property, Object value, ExceptionSubType exception) {
		this.property = property;
		this.value = value;
		this.exception = exception;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ExceptionSubType getException() {
		return exception;
	}

	public void setException(ExceptionSubType exception) {
		this.exception = exception;
	}

}
