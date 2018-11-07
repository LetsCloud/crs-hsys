/**
 * 
 */
package io.crs.hsys.server.repository;

import java.util.List;

import io.crs.hsys.server.entity.chat.Chat;

/**
 * @author robi
 *
 */
public interface ChatRepository extends CrudRepository<Chat> {
	List<Chat> getByAccount(Object account);

}
