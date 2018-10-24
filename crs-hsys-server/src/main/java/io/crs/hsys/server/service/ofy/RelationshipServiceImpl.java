/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.profile.Relationship;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.RelationshipRepository;
import io.crs.hsys.server.service.RelationshipService;

/**
 * @author robi
 *
 */
public class RelationshipServiceImpl extends CrudServiceImpl<Relationship, RelationshipRepository>
		implements RelationshipService {
	private static final Logger logger = LoggerFactory.getLogger(RelationshipServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public RelationshipServiceImpl(RelationshipRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("RelationshipServiceImpl");
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
