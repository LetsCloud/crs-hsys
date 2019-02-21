/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.common.Account;
import io.crs.hsys.server.entity.common.UserGroup;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.UserGroupRepository;
import io.crs.hsys.server.service.UserGroupService;

/**
 * @author robi
 *
 */
public class UserGroupServiceImpl extends CrudServiceImpl<UserGroup, UserGroupRepository> implements UserGroupService {
	private static final Logger logger = LoggerFactory.getLogger(UserGroupServiceImpl.class.getName());

	private final UserGroupRepository userGroupRepository;
	private final AccountRepository accountRepository;

	public UserGroupServiceImpl(UserGroupRepository userGroupRepository, AccountRepository accountRepository) {
		super(userGroupRepository);
		logger.info("UserGroupServiceImpl(");
		this.userGroupRepository = userGroupRepository;
		this.accountRepository = accountRepository;
	}

	@Override
	public List<UserGroup> getAll(String accountWebSafeKey) {
		Account account = accountRepository.findByWebSafeKey(accountWebSafeKey);

		if (account == null)
			return null;

		List<UserGroup> result = userGroupRepository.getAll(account);
		for (UserGroup group : result) {
			group.getMembers();
		}
		return result;
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findById(accountId));
		return parents;
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
