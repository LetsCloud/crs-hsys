/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.profile.Contact;
import io.crs.hsys.server.repository.ContactRepository;

/**
 * @author robi
 *
 */
public class ContactRepositoryImpl extends AccountChildRepositoryImpl<Contact> implements ContactRepository {

	public ContactRepositoryImpl() {
		super(Contact.class);
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
