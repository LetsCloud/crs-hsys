/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.BaseEntity;
import io.crs.hsys.server.repository.CrudRepository;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
public abstract class CrudRepositoryImpl<T extends BaseEntity> extends ObjectifyBaseRepository<T>
implements CrudRepository<T> {
private static final Logger logger = Logger.getLogger(CrudRepositoryImpl.class.getName());

protected CrudRepositoryImpl(Class<T> clazz) {
super(clazz);
logger.info("CrudRepositoryImpl");
}

protected abstract Object getParent(T entity);

protected abstract Object getParentKey(String parentWebSafeKey);

protected abstract void loadUniqueIndexMap(T entiy);

public abstract String getAccountId(String webSafeKey);

@Override
public T save(T entity) throws EntityValidationException, UniqueIndexConflictException {
entity.validate();
entity.clearUniqueIndexes();
loadUniqueIndexMap(entity);
checkUniqueIndexConflict(getParent(entity), entity);
return putAndLoad(entity);
}

@Override
public T findById(Long id) {
return get(id);
}

@Override
public T findByWebSafeKey(String id) {
return get(id);
}

@Override
public void delete(String webSafeKey) {
delete(getKey(webSafeKey));
}

@Override
public List<T> getAll(Object parent) {
return getChildren(parent);
}

@Override
public void deleteAll(Object parent) {
Boolean delete = true;
while (delete) {
	List<Key<T>> keys = getChildrenKeys(parent);
	if (keys.isEmpty()) {
		delete = false;
	} else {
		deleteByKeys(keys);
	}
}
}

@Override
public List<T> getChildren(String parentWebSafeKey) {
return getChildren(getParentKey(parentWebSafeKey));
}

@Override
public List<T> getChildrenByFilters(String parentWebSafeKey, Map<String, Object> filters) {
return getChildrenByFilters(getParentKey(parentWebSafeKey), filters);
}

}
