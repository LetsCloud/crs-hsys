/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import java.util.List;

import io.crs.hsys.server.entity.chat.Chat;
import io.crs.hsys.server.repository.ChatRepository;

/**
 * @author robi
 *
 */
public class ChatRepositoryImpl extends AccountChildRepositoryImpl<Chat> implements ChatRepository {

	protected ChatRepositoryImpl() {
		super(Chat.class);
	}

	@Override
	public List<Chat> getByAccount(Object account) {
		return getChildren(account);
	}
}
