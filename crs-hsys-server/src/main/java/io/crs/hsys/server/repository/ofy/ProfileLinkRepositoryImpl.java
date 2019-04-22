/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.profile.ProfileLink;
import io.crs.hsys.server.repository.ProfileLinkRepository;

/**
 * @author robi
 *
 */
public class ProfileLinkRepositoryImpl extends AccountChildRepositoryImpl<ProfileLink>
		implements ProfileLinkRepository {

	public ProfileLinkRepositoryImpl() {
		super(ProfileLink.class);
	}
}
