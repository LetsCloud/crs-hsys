/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.hotel.Hotel;
import io.crs.hsys.server.repository.HotelRepository;

/**
 * @author robi
 *
 */
public class HotelRepositoryImpl extends CrudRepositoryImpl<Hotel> implements HotelRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelRepositoryImpl.class.getName());

	public HotelRepositoryImpl() {
		super(Hotel.class);
	}

	@Override
	public String getAccountId(String id) {
		LOGGER.info("getAccountId->id=" + id);
		Key<Hotel> key = getKey(id);
		return key.getParent().getString();
	}

	@Override
	protected Object getParent(Hotel entity) {
		return entity.getAccount();
	}

	@Override
	public Hotel findByGeneratedId(Long parentId, Long generatedId) {
		return get(getKey(parentId, generatedId));
	}

	@Override
	public void delete(String webSafeString) {
		delete(getKey(webSafeString));
	}

	@Override
	public void delete(Hotel hotel) {
		Key<Hotel> key = Key.create(hotel.getWebSafeKey());
		delete(key);
	}

	/**
	 * 
	 * @param parentId
	 * @param generatedId
	 * @return
	 */
	private Key<Hotel> getKey(Long parentId, Long generatedId) {
		Key<Account> parentKey = Key.create(Account.class, parentId);
		return getKey(parentKey, generatedId);
	}

	@Override
	public Hotel findByCode(Account parent, String code) {
		Hotel hotel = getChildByProperty(parent, "code", code);
		return hotel;
	}

	@Override
	public List<Key<Hotel>> getKeysByCode(Account parent, String code) {
		List<Key<Hotel>> keys = getChildrenKeysByProperty(parent, "code", code);
		return keys;
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(Hotel entiy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void prepareForeignKeys(String webSafeKey) {
		// TODO Auto-generated method stub
		
	}
}
