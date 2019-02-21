/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.task.TaskGroup;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.TaskGroupRepository;
import io.crs.hsys.server.service.TaskGroupService;

/**
 * @author CR
 *
 */
public class TaskGroupServiceImpl extends CrudServiceImpl<TaskGroup, TaskGroupRepository> implements TaskGroupService {
	private static final Logger logger = LoggerFactory.getLogger(TaskGroupServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public TaskGroupServiceImpl(TaskGroupRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("TaskGroupServiceOfy(");
		this.accountRepository = accountRepository;
	}

	@Override
	public List<TaskGroup> getAll(String accountWebSafeKey) {
		if (accountWebSafeKey == null)
			return null;
		return getAll(accountWebSafeKey);
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
