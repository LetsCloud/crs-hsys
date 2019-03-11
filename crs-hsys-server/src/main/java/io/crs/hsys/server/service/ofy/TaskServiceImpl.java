/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.task.Task;
import io.crs.hsys.server.entity.task.TaskNote;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.TaskRepository;
import io.crs.hsys.server.security.LoggedInChecker;
import io.crs.hsys.server.service.TaskService;

/**
 * @author robi
 *
 */
public class TaskServiceImpl extends CrudServiceImpl<Task, TaskRepository> implements TaskService {
	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class.getName());

	private final AccountRepository accountRepository;
	private final LoggedInChecker loggedInChecker;

	public TaskServiceImpl(TaskRepository repository, AccountRepository accountRepository,
			LoggedInChecker loggedInChecker) {
		super(repository);
		logger.info("TaskServiceImpl(");
		this.accountRepository = accountRepository;
		this.loggedInChecker = loggedInChecker;
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

	@Override
	public Task create(final Task entity) throws Throwable {
		entity.addNote(new TaskNote(new Date(), loggedInChecker.getLoggedInUser(), "Created"));
		return super.create(entity);
	}

	@Override
	protected Task checkForChanges(Task newEntity, Task oldEntity) {
		return newEntity;
	}

}
