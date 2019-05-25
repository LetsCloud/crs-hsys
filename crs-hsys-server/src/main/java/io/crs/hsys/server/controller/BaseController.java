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
import io.crs.hsys.shared.exception.BaseException;
import io.crs.hsys.shared.exception.RestApiException;
import io.crs.hsys.shared.exception.cnst.ErrorTitleCode;

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
			throw new RestApiException(new BaseException(ErrorTitleCode.MISMATCHED_ACCOUNT + " " + accountId));
	}

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(RestApiException.class)
	public ResponseEntity<ErrorResponseDto> exceptionHandler(RestApiException ex) {
		ErrorResponseDto error = ex.getBaseException().getErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json; charset=utf-8");

		return new ResponseEntity<ErrorResponseDto>(error, headers, HttpStatus.NOT_FOUND);
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
