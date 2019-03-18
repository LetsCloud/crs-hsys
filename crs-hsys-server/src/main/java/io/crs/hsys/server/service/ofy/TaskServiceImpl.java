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

import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.entity.hotel.Room;
import io.crs.hsys.server.entity.task.Task;
import io.crs.hsys.server.entity.task.TaskNote;
import io.crs.hsys.server.entity.task.TaskType;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.TaskRepository;
import io.crs.hsys.server.security.LoggedInChecker;
import io.crs.hsys.server.service.TaskService;
import io.crs.hsys.shared.constans.TaskNoteType;
import io.crs.hsys.shared.constans.TaskStatus;
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

	private Task addNote(Task entity, TaskNoteType type) {
		entity.addNote(new TaskNote(new Date(), loggedInChecker.getLoggedInUser(), type));
		return entity;
	}

	@Override
	protected Task checkForChanges(Task newEntity, Task oldEntity) {
		newEntity = new checkAssigneeChange(oldEntity, newEntity).isDifferent();
		newEntity = new checkDescriptionChange(oldEntity, newEntity).isDifferent();
		newEntity = new checkDueDateChange(oldEntity, newEntity).isDifferent();
		newEntity = new checkRoomChange(oldEntity, newEntity).isDifferent();
		newEntity = new checkTaskTypeChange(oldEntity, newEntity).isDifferent();
		newEntity = new checkStatusChange(oldEntity, newEntity).isDifferent();
		return newEntity;
	}

	@Override
	public List<Task> getByRoom(Room room) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("room", room);
		return getChildrenByFilters(room.getHotel().getWebSafeKey(), filters);
	}

	@Override
	public Task changeStatus(final String taskKey, TaskStatus status) throws RestApiException {
		logger.info("TaskServiceImpl().changeStatus()");
		try {
			// Objectify tranzakció indul
			Task th = ofy().transact(new Work<Task>() {
				public Task run() {
					Task oldEntity = repository.findByWebSafeKey(taskKey);
					try {
						Task newEntity = new Task(oldEntity);
						newEntity.setStatus(status);
						newEntity = new checkStatusChange(oldEntity, newEntity).isDifferent();
						return repository.save(newEntity);
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

	public class checkAssigneeChange extends checkChange<AppUser> {

		public checkAssigneeChange(Task oldTask, Task newTask) {
			super(newTask, oldTask.getAssignee(), newTask.getAssignee(), TaskNoteType.TNT_MOD_ASSIGNEE);
			text = oldTask.getAssignee().getCode();
		}
	}

	public class checkDescriptionChange extends checkChange<String> {

		public checkDescriptionChange(Task oldTask, Task newTask) {
			super(newTask, oldTask.getDescription(), newTask.getDescription(), TaskNoteType.TNT_MOD_DESCRIPTION);
			text = oldTask.getDescription();
		}
	}

	public class checkDueDateChange extends checkChange<Date> {

		public checkDueDateChange(Task oldTask, Task newTask) {
			super(newTask, oldTask.getDueDate(), newTask.getDueDate(), TaskNoteType.TNT_MOD_DUEDATE);
			text = new Long(oldTask.getDueDate().getTime()).toString();
		}
	}

	public class checkRoomChange extends checkChange<Room> {

		public checkRoomChange(Task oldTask, Task newTask) {
			super(newTask, oldTask.getRoom(), newTask.getRoom(), TaskNoteType.TNT_MOD_ROOM);
			text = oldTask.getRoom().getCode();
		}
	}

	public class checkTaskTypeChange extends checkChange<TaskType> {

		public checkTaskTypeChange(Task oldTask, Task newTask) {
			super(newTask, oldTask.getType(), newTask.getType(), TaskNoteType.TNT_MOD_TYPE);
			text = oldTask.getType().getCode();
		}
	}

	public class checkStatusChange extends checkChange<TaskStatus> {

		public checkStatusChange(Task oldTask, Task newTask) {
			super(newTask, oldTask.getStatus(), newTask.getStatus(), TaskNoteType.TNT_MOD_STATUS);
			text = newTask.getStatus().toString();
		}

		@Override
		protected Task modifyTask(Task newTask) {
			newTask.setUpdated(new Date());
			return newTask;
		}
	}

	public abstract class checkChange<T> {
		protected Task newTask;
		private T o1, o2;
		private TaskNoteType noteType;
		protected String text;

		public checkChange(Task task, T o1, T o2, TaskNoteType noteType) {
			this.newTask = task;
			this.o1 = o1;
			this.o2 = o2;
			this.noteType = noteType;
		}

		public Task isDifferent() {
			if ((o1 == null) && (o2 == null))
				return newTask;

			if ((o1 == null) && (o2 != null)) {
				newTask.addNote(createNote(getNoPrevNote()));
				return modifyTask(newTask);
			}

			if (!o1.equals(o2)) {
				newTask.addNote(createNote(text));
				return modifyTask(newTask);
			}
			return newTask;
		}

		protected String getNoPrevNote() {
			return "-";
		};

		protected Task modifyTask(Task task) {
			return task;
		}

		private TaskNote createNote(String text) {
			return new TaskNote(new Date(), loggedInChecker.getLoggedInUser(), noteType, text);
		}
	}
}
