/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.profile.ProfileGroup;
import io.crs.hsys.server.repository.ProfileGroupRepository;
import io.crs.hsys.shared.exception.cnst.ErrorMessageCode;

/**
 * @author robi
 *
 */
public class ProfileGroupRepositoryImpl extends AccountChildRepositoryImpl<ProfileGroup>
		implements ProfileGroupRepository {

	public ProfileGroupRepositoryImpl() {
		super(ProfileGroup.class);
	}

	@Override
	protected void loadUniqueIndexMap(ProfileGroup entiy) {
		if (entiy.getCode() != null) {
			entiy.addUniqueIndex(ProfileGroup.PROFILEGROUP_CODE, entiy.getCode(),
					ErrorMessageCode.PROFILEGROUP_CODE_ALREADY_EXISTS);
		}
	}
}
