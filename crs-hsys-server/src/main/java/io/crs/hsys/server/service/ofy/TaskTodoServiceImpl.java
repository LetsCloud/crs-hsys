/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.task.TaskTodo;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.TaskTodoRepository;
import io.crs.hsys.server.service.TaskTodoService;

/**
 * @author robi
 *
 */
public class TaskTodoServiceImpl extends CrudServiceImpl<TaskTodo, TaskTodoRepository> implements TaskTodoService {
	private static final Logger logger = LoggerFactory.getLogger(TaskTodoServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public TaskTodoServiceImpl(TaskTodoRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("TaskGroupServiceOfy(");
		this.accountRepository = accountRepository;
	}

	@Override
	public List<TaskTodo> getAll(String accountWebSafeKey) {
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
