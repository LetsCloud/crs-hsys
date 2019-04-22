/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import java.util.List;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.hotel.Hotel;
import io.crs.hsys.server.repository.HotelRepository;

/**
 * @author robi
 *
 */
public class HotelRepositoryImpl extends AccountChildRepositoryImpl<Hotel> implements HotelRepository {

	public HotelRepositoryImpl() {
		super(Hotel.class);
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
}
