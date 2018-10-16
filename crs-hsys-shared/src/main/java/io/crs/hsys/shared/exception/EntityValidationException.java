/**
 * 
 */
package io.crs.hsys.shared.exception;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class EntityValidationException extends Exception {

	private ExceptionType type;

	private String entity;

	private String property;

	public EntityValidationException(ExceptionType type, String entity, String property) {
		super("Entities (" + entity + ") property (" + property + ") has no value!");
		this.type = type;
		this.entity = entity;
		this.property = property;
	}

	public ExceptionType getType() {
		return type;
	}

	public String getEntity() {
		return entity;
	}

	public String getProperty() {
		return property;
	}
}
