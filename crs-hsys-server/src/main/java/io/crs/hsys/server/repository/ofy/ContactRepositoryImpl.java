/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.profile.Contact;
import io.crs.hsys.server.repository.ContactRepository;

/**
 * @author robi
 *
 */
public class ContactRepositoryImpl extends CrudRepositoryImpl<Contact> implements ContactRepository {
	private static final Logger logger = LoggerFactory.getLogger(ContactRepositoryImpl.class.getName());

	public ContactRepositoryImpl() {
		super(Contact.class);
		logger.info("ContactRepositoryImpl()");
	}

	@Override
	public String getAccountId(String id) {
		Key<Contact> key = getKey(id);
		return key.getParent().getString();
	}

	@Override
	protected Object getParent(Contact entity) {
		return entity.getAccount();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(Contact entiy) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void prepareForeignKeys(String webSafeKey) {
		// TODO Auto-generated method stub
		
	}
}
