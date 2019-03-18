/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.crs.hsys.server.entity.hotel.Room;
import io.crs.hsys.server.entity.task.Task;
import io.crs.hsys.server.entity.task.TaskNote;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.TaskRepository;
import io.crs.hsys.server.security.LoggedInChecker;
import io.crs.hsys.server.service.TaskService;
import io.crs.hsys.shared.constans.TaskNoteType;

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
	public Task create(Task entity) throws Throwable {
		entity.setCreated(new Date());
		entity.setUpdated(entity.getCreated());
		entity.setReporter(loggedInChecker.getLoggedInUser());
		entity = addNote(entity, TaskNoteType.TNT_CREATED);
		return super.create(entity);
	}

	@Override
	protected Task checkForChanges(Task newEntity, Task oldEntity) {
		if (!oldEntity.getAssignee().getCode().equals(newEntity.getAssignee().getCode()))
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_ASSIGNEE, oldEntity.getAssignee().getCode());
		if (!oldEntity.getDescription().equals(newEntity.getDescription()))
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_DESCRIPTION, oldEntity.getDescription());

		if ((oldEntity.getDueDate() != null) && (newEntity.getDueDate() != null)
				&& (!oldEntity.getDueDate().equals(newEntity.getDueDate()))) {
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_DUEDATE,
					new Long(oldEntity.getDueDate().getTime()).toString());
		}

		if (!oldEntity.getRoom().getCode().equals(newEntity.getRoom().getCode()))
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_ROOM, oldEntity.getRoom().getCode());
		if (!oldEntity.getStatus().equals(newEntity.getStatus())) {
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_STATUS, newEntity.getStatus().toString());
			newEntity.setUpdated(new Date());
		}
		if (!oldEntity.getType().getCode().equals(newEntity.getType().getCode()))
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_TYPE, oldEntity.getType().getCode());
		return newEntity;
	}

	private Task addNote(Task entity, TaskNoteType type) {
		entity.addNote(new TaskNote(new Date(), loggedInChecker.getLoggedInUser(), type));
		return entity;
	}

	private Task addNote(Task entity, TaskNoteType type, String text) {
		entity.addNote(new TaskNote(new Date(), loggedInChecker.getLoggedInUser(), type, text));
		return entity;
	}

	@Override
	public List<Task> getByRoom(Room room) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("room", room);
		return getChildrenByFilters(room.getHotel().getWebSafeKey(), filters);
	}

}
