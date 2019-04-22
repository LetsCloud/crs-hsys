/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import io.crs.hsys.server.entity.profile.Organization;
import io.crs.hsys.server.repository.OrganizationRepository;

/**
 * @author robi
 *
 */
public class OrganizationRepositoryImpl extends AccountChildRepositoryImpl<Organization>
		implements OrganizationRepository {

	public OrganizationRepositoryImpl() {
		super(Organization.class);
	}
}
