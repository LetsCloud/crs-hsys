/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.task.TaskType;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.TaskTypeRepository;
import io.crs.hsys.server.service.TaskTypeService;

/**
 * @author robi
 *
 */
public class TaskTypeServiceImpl extends CrudServiceImpl<TaskType, TaskTypeRepository> implements TaskTypeService {
	private static final Logger logger = LoggerFactory.getLogger(TaskTypeServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public TaskTypeServiceImpl(TaskTypeRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("TaskTypeServiceImpl(");
		this.accountRepository = accountRepository;
	}

	@Override
	public List<TaskType> getAll(String accountWebSafeKey) {
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
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findByWebSafeKey(accountWebSafeKey));
		return parents;
	}

}
