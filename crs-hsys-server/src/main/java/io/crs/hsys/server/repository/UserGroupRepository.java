/**
 * 
 */
package io.crs.hsys.server.repository;

import java.util.List;

import io.crs.hsys.server.entity.common.UserGroup;

/**
 * @author robi
 *
 */
public interface UserGroupRepository extends CrudRepository<UserGroup> {

	List<UserGroup> getByAccount(Object account);

}
