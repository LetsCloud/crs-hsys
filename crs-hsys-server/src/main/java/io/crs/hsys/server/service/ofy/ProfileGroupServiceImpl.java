/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.profile.ProfileGroup;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.ProfileGroupRepository;
import io.crs.hsys.server.service.ProfileGroupService;

/**
 * @author robi
 *
 */
public class ProfileGroupServiceImpl extends CrudServiceImpl<ProfileGroup, ProfileGroupRepository>
		implements ProfileGroupService {
	private static final Logger logger = LoggerFactory.getLogger(ProfileGroupServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public ProfileGroupServiceImpl(ProfileGroupRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("HotelServiceImpl");
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
