/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static final Logger logger = LoggerFactory.getLogger(HotelChildRepositoryImpl.class.getName());

	protected HotelChildRepositoryImpl(Class<T> clazz) {
		super(clazz);
		logger.info("HotelChildRepositoryImpl");
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
	public String getAccountId(String webSafeString) {
		Key<T> key = getKey(webSafeString);
		return key.getParent().getParent().getString();
	}
}
