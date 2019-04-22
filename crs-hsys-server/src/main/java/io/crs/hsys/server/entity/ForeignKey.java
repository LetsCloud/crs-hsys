/**
 * 
 */
package io.crs.hsys.server.entity;

import io.crs.hsys.server.repository.CrudRepository;
import io.crs.hsys.shared.exception.ExceptionSubType;

/**
 * @author robi
 *
 */
public class ForeignKey {
	private Object parent;
	private String property;
	private Object value;
	private CrudRepository<?> repo;
	private ExceptionSubType exception;

	public ForeignKey(String property, CrudRepository<?> repo, ExceptionSubType exception) {
		this.property = property;
		this.repo = repo;
		this.exception = exception;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public CrudRepository<?> getRepo() {
		return repo;
	}

	public void setRepo(CrudRepository<?> repo) {
		this.repo = repo;
	}

	public ExceptionSubType getException() {
		return exception;
	}

	public void setException(ExceptionSubType exception) {
		this.exception = exception;
	}

	public Object getParent() {
		return parent;
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
