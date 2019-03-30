/**
 * 
 */
package io.crs.hsys.server.repository;

import java.util.List;
import java.util.Map;

import io.crs.hsys.server.entity.BaseEntity;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.ForeignKeyConflictException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
public interface CrudRepository<T extends BaseEntity> {

	String getAccountId(String id);

	/**
	 * Az entitás mentését végző metódus definiciója.
	 * 
	 * @param entity A mnetésre váró entitás.
	 * @return Az elmentett entitás.
	 * @throws EntityValidationException    Validációs hiba esetén dobandó kivétel.
	 * @throws UniqueIndexConflictException Egyedi kulcs ütközés esetén dobandó
	 *                                      kivétel.
	 */
	T save(T entity) throws EntityValidationException, UniqueIndexConflictException;

	T findByWebSafeKey(String webSafeKey);

	T findById(Long id);

	Boolean isExists(String webSafeKey);

	void delete(String webSafeKey) throws ForeignKeyConflictException;

	List<T> getAll();

	List<T> getAll(Object parent);

	void deleteAll(Object parent);

	List<T> getChildren(String parentWebSafeKey);

	List<T> getChildrenByFilters(String parentWebSafeKey, Map<String, Object> filters);
}
