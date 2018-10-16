/**
 * 
 */
package io.crs.hsys.server.repository;

import io.crs.hsys.server.entity.common.Account;

/**
 * @author robi
 *
 */
public interface AccountRepository extends CrudRepository<Account> {

	String getWebSafeKeyById(Long id);

}
