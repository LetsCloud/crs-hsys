/**
 * 
 */
package io.crs.hsys.server.service;

import io.crs.hsys.server.entity.chat.Chat;
import io.crs.hsys.server.entity.chat.ChatPost;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
public interface ChatService extends CrudService<Chat> {
	
	Chat addPost(String chatKey, ChatPost post) throws EntityValidationException, UniqueIndexConflictException;

}
