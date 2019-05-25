/**
 * 
 */
package io.crs.hsys.client.core.gin;

import com.gwtplatform.dispatch.shared.ActionException;

import io.crs.hsys.shared.dto.ErrorResponseDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class CustomActionException extends ActionException {

	private ErrorResponseDto errorResponse;
	
    public CustomActionException() {
    	super();
    }

    public CustomActionException(String message) {
        super(message);
    }

    public CustomActionException(String message, ErrorResponseDto erDto) {
        super(message);
        this.errorResponse = erDto;
    }

    public CustomActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomActionException(Throwable cause) {
        super(cause.getMessage());
    }
	public ErrorResponseDto getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponseDto errorResponse) {
		this.errorResponse = errorResponse;
	}

}
