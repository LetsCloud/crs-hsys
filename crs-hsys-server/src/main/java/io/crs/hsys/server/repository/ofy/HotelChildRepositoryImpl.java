/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.hotel.Hotel;
import io.crs.hsys.server.entity.hotel.HotelChild;
import io.crs.hsys.server.repository.HotelChildRepository;

/**
 * @author robi
 *
 */
public abstract class HotelChildRepositoryImpl<T extends HotelChild> extends CrudRepositoryImpl<T>
		implements HotelChildRepository<T> {

	protected HotelChildRepositoryImpl(Class<T> clazz) {
		super(clazz);
	}

	@Override
	protected Object getParent(T entity) {
		return entity.getHotel();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Hotel> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(T entiy) {
	}

	@Override
	protected void prepareForeignKeys(String webSafeKey) {
	}
}
