/**
 * 
 */
package io.crs.hsys.shared.dto;

import io.crs.hsys.shared.exception.ExceptionType;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class ErrorResponseDto implements Dto {

	private int errorCode;

	private ExceptionType exceptionType;

	private String property;

	private String message;

	public ErrorResponseDto() {
	
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public ExceptionType getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(ExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponseDto [errorCode=" + errorCode + ", exceptionType=" + exceptionType + ", property="
				+ property + ", message=" + message + "]";
	}
	
}
