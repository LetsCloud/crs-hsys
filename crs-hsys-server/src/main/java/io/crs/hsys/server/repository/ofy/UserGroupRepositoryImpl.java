/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.common.UserGroup;
import io.crs.hsys.server.repository.UserGroupRepository;
import io.crs.hsys.shared.exception.cnst.ErrorMessageCode;

/**
 * @author robi
 *
 */
public class UserGroupRepositoryImpl extends AccountChildRepositoryImpl<UserGroup> implements UserGroupRepository {

	/**
	 * 
	 */
	private static final String PROPERTY_NAME = "name";

	protected UserGroupRepositoryImpl() {
		super(UserGroup.class);
	}

	@Override
	protected void loadUniqueIndexMap(UserGroup entiy) {
		if ((entiy.getName() != null) && (!entiy.getName().isEmpty()))
			entiy.addUniqueIndex(PROPERTY_NAME, entiy.getName(), ErrorMessageCode.USERGROUP_NAME_ALREADY_EXISTS);
	}
}
