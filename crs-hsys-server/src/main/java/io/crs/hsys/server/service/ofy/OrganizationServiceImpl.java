/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.profile.Organization;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.OrganizationRepository;
import io.crs.hsys.server.service.OrganizationService;

/**
 * @author robi
 *
 */
public class OrganizationServiceImpl extends CrudServiceImpl<Organization, OrganizationRepository>
		implements OrganizationService {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public OrganizationServiceImpl(OrganizationRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("OrganizationServiceImpl()");
		this.accountRepository = accountRepository;
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findById(accountId));
		return parents;
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findByWebSafeKey(accountWebSafeKey));
		return parents;
	}
}
