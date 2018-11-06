/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import java.util.List;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.chat.Chat;
import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.repository.ChatRepository;

/**
 * @author robi
 *
 */
public class ChatRepositoryImpl extends CrudRepositoryImpl<Chat> implements ChatRepository {

	protected ChatRepositoryImpl() {
		super(Chat.class);
	}

	@Override
	protected Object getParent(Chat entity) {
		return entity.getAccount();
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<Chat> key = getKey(webSafeString);
		return key.getParent().getString();
	}

	@Override
	public List<Chat> getByAccount(Object account) {
		return getChildren(account);
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(Chat entiy) {
		// TODO Auto-generated method stub
		
	}

}
