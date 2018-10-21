/**
 * 
 */
package io.crs.hsys.server.service;

import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.model.Registration;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.IdNotFoundException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author CR
 *
 */
public interface AccountService  {

	AppUser register(Registration registration)
			throws EntityValidationException, UniqueIndexConflictException, IdNotFoundException;
	
	Boolean sameAccountIds(String id, Long generatedId);
}