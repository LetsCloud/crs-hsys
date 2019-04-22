/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.profile.Profile;
import io.crs.hsys.server.repository.ProfileRepository;

/**
 * @author robi
 *
 */
public class ProfileRepositoryImpl extends AccountChildRepositoryImpl<Profile> implements ProfileRepository {

	public ProfileRepositoryImpl() {
		super(Profile.class);
	}
}
