/**
 * 
 */
package io.crs.hsys.server.repository;

import java.util.List;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.common.AppUser;

/**
 * @author robi
 *
 */
public interface AppUserRepository extends CrudRepository<AppUser> {

	AppUser findByUsername(Account account, String username);

	AppUser findByEmail(String emailAddress);

	List<AppUser> getByAccount(Object account);

	AppUser findByToken(String token);
}
