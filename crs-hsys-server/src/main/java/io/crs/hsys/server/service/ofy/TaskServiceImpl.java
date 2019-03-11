/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.task.Task;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.TaskRepository;
import io.crs.hsys.server.service.TaskService;

/**
 * @author robi
 *
 */
public class TaskServiceImpl extends CrudServiceImpl<Task, TaskRepository> implements TaskService {
	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public TaskServiceImpl(TaskRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("TaskServiceImpl(");
		this.accountRepository = accountRepository;
	}

	@Override
	public List<Task> getAll(String accountWebSafeKey) {
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
