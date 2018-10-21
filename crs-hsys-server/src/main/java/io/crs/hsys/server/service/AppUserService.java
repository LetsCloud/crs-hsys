/**
 * 
 */
package io.crs.hsys.server.service;

import java.util.List;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.model.Registration;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
public interface AppUserService extends CrudService<AppUser> {

	AppUser getUserByUsername(String username, Long accountGeneratedId);

	AppUser getByEmail(String email);
	
	List<String> getPermissions(String username);

	AppUser getCurrentUser();

	Boolean isCurrentUserLoggedIn();

	Boolean activate(String token) throws EntityValidationException, UniqueIndexConflictException;

	void createVerificationToken(AppUser user, String token) throws Throwable;
	
	AppUser createAdminUser(Registration registration, Account account) throws EntityValidationException, UniqueIndexConflictException;
	
	void fcmSubscribe(String iidToken, String userAgent) throws Throwable;
}
