/**
 * 
 */
package io.crs.hsys.server.repository;

import java.util.List;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.hotel.Hotel;

/**
 * @author robi
 *
 */
public interface HotelRepository extends CrudRepository<Hotel> {

	Hotel findByCode(Account parent, String code);

	Hotel findByGeneratedId(Long parentGeneratedId, Long generatedId);

	Long getGeneratedId(String webSafeString);

	void delete(Hotel hotel);

	List<Key<Hotel>> getKeysByCode(Account parent, String code);
}
