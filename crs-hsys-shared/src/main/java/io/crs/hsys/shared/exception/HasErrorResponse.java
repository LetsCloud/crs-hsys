/**
 * 
 */
package io.crs.hsys.shared.exception;

import io.crs.hsys.shared.dto.ErrorResponseDto;

/**
 * @author robi
 *
 */
public interface HasErrorResponse {
	ErrorResponseDto getErrorResponse();
}
