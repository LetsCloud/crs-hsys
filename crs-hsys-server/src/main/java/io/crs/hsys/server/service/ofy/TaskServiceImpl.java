/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Work;

import io.crs.hsys.server.entity.hotel.Room;
import io.crs.hsys.server.entity.task.Task;
import io.crs.hsys.server.entity.task.TaskNote;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.TaskRepository;
import io.crs.hsys.server.security.LoggedInChecker;
import io.crs.hsys.server.service.TaskService;
import io.crs.hsys.shared.constans.RoomStatus;
import io.crs.hsys.shared.constans.TaskNoteType;
import io.crs.hsys.shared.constans.TaskStatus;
import io.crs.hsys.shared.dto.hk.RoomStatusDto;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.shared.exception.RestApiException;

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
		newEntity = checkAssignee(newEntity, oldEntity);
		newEntity = checkDescription(newEntity, oldEntity);
		newEntity = checkDueDate(newEntity, oldEntity);
		newEntity = checkRoom(newEntity, oldEntity);
		newEntity = checkTaskType(newEntity, oldEntity);
		newEntity = checkStatus(newEntity, oldEntity);
		return newEntity;
	}

	private Task checkStatus(Task newEntity, Task oldEntity) {
		if ((oldEntity.getStatus() == null) && (newEntity.getStatus() == null))
			return newEntity;		
		
		if ((oldEntity.getStatus() == null) && (newEntity.getStatus() != null)) {
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_STATUS, "-");
			newEntity.setUpdated(new Date());
			return newEntity;		
		}

		if (!oldEntity.getStatus().equals(newEntity.getStatus())) {
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_STATUS, newEntity.getStatus().toString());
			newEntity.setUpdated(new Date());
		}
		return newEntity;
	}

	private Task checkAssignee(Task newEntity, Task oldEntity) {
		if ((oldEntity.getAssignee() == null) && (newEntity.getAssignee() == null))
			return newEntity;		
		
		if ((oldEntity.getAssignee() == null) && (newEntity.getAssignee() != null)) {
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_ASSIGNEE, "-");
			return newEntity;		
		}

		if (!oldEntity.getAssignee().equals(newEntity.getAssignee())) {
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_ASSIGNEE, oldEntity.getAssignee().getCode());
		}
		return newEntity;
	}

	private Task checkDescription(Task newEntity, Task oldEntity) {
		if ((oldEntity.getDescription() == null) && (newEntity.getDescription() == null))
			return newEntity;		
		
		if ((oldEntity.getDescription() == null) && (newEntity.getDescription() != null)) {
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_DESCRIPTION, "-");
			return newEntity;		
		}

		if (!oldEntity.getDescription().equals(newEntity.getDescription())) {
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_DESCRIPTION, oldEntity.getDescription());
		}
		return newEntity;
	}

	private Task checkDueDate(Task newEntity, Task oldEntity) {
		if ((oldEntity.getDueDate() == null) && (newEntity.getDueDate() == null))
			return newEntity;		
		
		if ((oldEntity.getDueDate() == null) && (newEntity.getDueDate() != null)) {
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_DUEDATE, "");
			return newEntity;		
		}

		if (!oldEntity.getDueDate().equals(newEntity.getDueDate())) {
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_DUEDATE, new Long(oldEntity.getDueDate().getTime()).toString());
		}
		return newEntity;
	}

	private Task checkRoom(Task newEntity, Task oldEntity) {
		if ((oldEntity.getRoom() == null) && (newEntity.getRoom() == null))
			return newEntity;		
		
		if ((oldEntity.getRoom() == null) && (newEntity.getRoom() != null)) {
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_ROOM, "-");
			return newEntity;		
		}

		if (!oldEntity.getRoom().equals(newEntity.getRoom())) {
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_ROOM, oldEntity.getRoom().getCode());
		}
		return newEntity;
	}

	private Task checkTaskType(Task newEntity, Task oldEntity) {
		if ((oldEntity.getRoom() == null) && (newEntity.getRoom() == null))
			return newEntity;		
		
		if ((oldEntity.getRoom() == null) && (newEntity.getRoom() != null)) {
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_TYPE, "-");
			return newEntity;		
		}

		if (!oldEntity.getRoom().equals(newEntity.getRoom())) {
			newEntity = addNote(newEntity, TaskNoteType.TNT_MOD_TYPE, oldEntity.getRoom().getCode());
		}
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

	@Override
	public Task changeStatus(final String taskKey, TaskStatus status) throws RestApiException {
		try {
			// Objectify tranzakció indul
			Task th = ofy().transact(new Work<Task>() {
				public Task run() {
					Task entity = repository.findByWebSafeKey(taskKey);
					try {
						// LOGGER.info("changeStatus()->roomStatus=" +
						// roomStatus);
						entity.setStatus(status);
						entity = repository.save(entity);
						return entity;
					} catch (Throwable e) {
						e.printStackTrace(System.out);
						throw new RuntimeException(e);
					}
				}
			});
			return th;
		} catch (RuntimeException re) {
			throw new RestApiException(re);
		}
	}

}
