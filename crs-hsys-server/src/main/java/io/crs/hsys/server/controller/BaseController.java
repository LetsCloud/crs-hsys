/**
 * 
 */
package io.crs.hsys.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import io.crs.hsys.server.service.AccountService;
import io.crs.hsys.shared.dto.ErrorResponseDto;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.ExceptionType;
import io.crs.hsys.shared.exception.RestApiException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
public abstract class BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	AccountService accountService;

	/**
	 * 
	 * @param request
	 * @param accountId
	 * @throws RestApiException
	 */
	public void accountIdValidation(WebRequest request, String accountId) throws RestApiException {
		logger.info("accountIdValidation->accountId=" + accountId);
		if (!accountService.sameAccountIds(accountId, getAccountId(request)))
			throw new RestApiException(new Exception(ExceptionType.MISMATCHED_ACCOUNT + " " + accountId));
	}

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(RestApiException.class)
	public ResponseEntity<ErrorResponseDto> exceptionHandler(RestApiException ex) {
		logger.info("exceptionHandler()");
		ErrorResponseDto error = new ErrorResponseDto();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ExceptionType.UNKNOWN.toString() + "-" + ex.getMessage());

		if (ex.getCause() instanceof EntityValidationException) {
			error.setExceptionType(ExceptionType.FAILD_ENTITIY_VALIDATION);
			error.setMessage(ExceptionType.FAILD_ENTITIY_VALIDATION.toString() + "-" + ex.getMessage());
		}

		if (ex.getCause() instanceof UniqueIndexConflictException) {
			error = createUiceResponse((UniqueIndexConflictException) ex.getCause());
		}

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json; charset=utf-8");

		return new ResponseEntity<ErrorResponseDto>(error, headers, HttpStatus.NOT_FOUND);
	}

	private ErrorResponseDto createUiceResponse(UniqueIndexConflictException e) {
		ErrorResponseDto error = new ErrorResponseDto();
		error.setExceptionType(ExceptionType.UNIQUE_INDEX_CONFLICT);
		error.setProperty(e.getProperty());
		error.setMessage(ExceptionType.UNIQUE_INDEX_CONFLICT.toString() + "-" + e.getMessage());
		return error;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public Long getAccountId(WebRequest request) {
		logger.info("getAccountId()");
		String username = request.getUserPrincipal().getName();
		logger.info("getAccountId()->username=" + username);
		String[] split = username.split(":");
		Long generatedId = new Long(split[1]);
		logger.info("getAccountId()->generatedId=" + generatedId);
		return generatedId;
	}
}
