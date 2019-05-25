/**
 * 
 */
package io.crs.hsys.shared.exception;

import io.crs.hsys.shared.dto.ErrorResponseDto;
import io.crs.hsys.shared.exception.cnst.ErrorMessageCode;
import io.crs.hsys.shared.exception.cnst.ErrorTitleCode;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class UniqueIndexConflictException extends BaseException {

	private ErrorMessageCode exception;

	private String entiy;

	private String property;

	private Object value;

	public UniqueIndexConflictException(String entiy, String property, Object value) {
		super();
		this.entiy = entiy;
		this.property = property;
		this.value = value;
	}

	public UniqueIndexConflictException(ErrorMessageCode exception, String entiy, String property, Object value) {
		this(entiy, property, value);
		this.exception = exception;
	}

	public ErrorMessageCode getException() {
		return exception;
	}

	public void setException(ErrorMessageCode exception) {
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

	@Override
	public ErrorResponseDto getErrorResponse() {
		ErrorResponseDto error = new ErrorResponseDto();
		error.setTitleCode(ErrorTitleCode.CRUD_CANNOT_BE_SAVED);
		error.setMessageCode(getException());
		error.setProperty(getProperty());
		error.setMessage(ErrorTitleCode.CRUD_CANNOT_BE_SAVED.toString() + "-" + getMessage());
		return error;
	}
}
