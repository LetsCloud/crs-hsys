/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import io.crs.hsys.server.entity.profile.Organization;
import io.crs.hsys.server.repository.OrganizationRepository;
import io.crs.hsys.server.service.AccountService;
import io.crs.hsys.server.service.OrganizationService;

/**
 * @author robi
 *
 */
public class OrganizationServiceImpl extends AccountChildServiceImpl<Organization, OrganizationRepository>
		implements OrganizationService {

	public OrganizationServiceImpl(OrganizationRepository repository, AccountService accountService) {
		super(repository, accountService);
	}
}
