/**
 * 
 */
package io.crs.hsys.server.service;

import java.util.List;

import io.crs.hsys.server.entity.profile.Profile;
import io.crs.hsys.server.entity.profile.ProfileLink;

/**
 * @author robi
 *
 */
public interface ProfileLinkService extends CrudService<ProfileLink> {
	
	List<ProfileLink> getByProfile(Profile profile, String field);

}
