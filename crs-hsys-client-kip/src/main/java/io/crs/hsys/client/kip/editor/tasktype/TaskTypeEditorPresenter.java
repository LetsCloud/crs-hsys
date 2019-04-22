/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.datasource.TaskGroupDataSource;
import io.crs.hsys.client.core.datasource.TaskTodoDataSource;
import io.crs.hsys.client.core.editor.AbstractEditorPresenter;
import io.crs.hsys.client.core.editor.AbstractEditorView;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.editor.tasktype.todo.AddTaskTodoEvent;
import io.crs.hsys.client.kip.editor.tasktype.todo.AddTaskTodoPresenter;
import io.crs.hsys.shared.api.TaskTypeResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.task.TaskGroupDto;
import io.crs.hsys.shared.dto.task.TaskTodoDto;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public class TaskTypeEditorPresenter
		extends AbstractEditorPresenter<TaskTypeDto, TaskTypeEditorPresenter.MyView, TaskTypeEditorPresenter.MyProxy>
		implements TaskTypeEditorUiHandlers, AddTaskTodoEvent.AddTaskTodoEventHandler {
	private static Logger logger = Logger.getLogger(TaskTypeEditorPresenter.class.getName());

	public static final String PARAM_KIND = "paramKind";

	public static final SingleSlot<PresenterWidget<?>> SLOT_ADD_TASKTODO = new SingleSlot<>();

	public interface MyView extends AbstractEditorView<TaskTypeDto>, HasUiHandlers<TaskTypeEditorUiHandlers> {
		void setTaskGroupData(List<TaskGroupDto> data);

		void setTaskTodoData(List<TaskTodoDto> data);

		void setAddTaskTodo(AddTaskTodoPresenter addTaskTodo);

		void addTaskTodos(List<TaskTodoDto> todos);

		void displayError(EntityPropertyCode code, String message);
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.TASK_TYPE_EDITOR)
	interface MyProxy extends ProxyPlace<TaskTypeEditorPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<TaskTypeResource> resourceDelegate;
	private final TaskGroupDataSource taskGroupDataSource;
	private final TaskTodoDataSource taskTodoDataSource;
	private final CurrentUser currentUser;
	private final CoreMessages i18nCore;
	private final AddTaskTodoPresenter addTaskTodo;

	@Inject
	TaskTypeEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<TaskTypeResource> resourceDelegate, TaskGroupDataSource taskGroupDataSource,
			TaskTodoDataSource taskTodoDataSource, CurrentUser currentUser, CoreMessages i18nCore,
			TaskTypeEditorFactory factory) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("TaskTypeEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.taskGroupDataSource = taskGroupDataSource;
		this.taskTodoDataSource = taskTodoDataSource;
		this.currentUser = currentUser;
		this.i18nCore = i18nCore;
		this.addTaskTodo = factory.createAddTaskTodo();

		getView().setUiHandlers(this);
		getView().setAddTaskTodo(addTaskTodo);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_ADD_TASKTODO, addTaskTodo);
		addRegisteredHandler(AddTaskTodoEvent.TYPE, this);
	}

	private void start() {
		if (isNew()) {
			SetPageTitleEvent.fire(i18nCore.taskTypeEditorCreateTitle(), "", MenuItemType.MENU_ITEM,
					TaskTypeEditorPresenter.this);
			create();
		} else {
			SetPageTitleEvent.fire(i18nCore.taskTypeEditorModifyTitle(), "", MenuItemType.MENU_ITEM,
					TaskTypeEditorPresenter.this);
			edit(filters.get(WEBSAFEKEY));
		}
	}

	private void loadTaskGroupData() {
		LoadCallback<TaskGroupDto> taskGroupLoadCallback = new LoadCallback<TaskGroupDto>() {
			@Override
			public void onSuccess(LoadResult<TaskGroupDto> loadResult) {
				getView().setTaskGroupData(loadResult.getData());
				if (taskTodoDataSource.getIsLoaded())
					start();
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		taskGroupDataSource.load(new LoadConfig<TaskGroupDto>(0, 0, null, null), taskGroupLoadCallback);
	}

	private void loadTaskTodoData() {
		LoadCallback<TaskTodoDto> taskTodoLoadCallback = new LoadCallback<TaskTodoDto>() {
			@Override
			public void onSuccess(LoadResult<TaskTodoDto> loadResult) {
				getView().setTaskTodoData(loadResult.getData());
				if (taskTodoDataSource.getIsLoaded())
					start();
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		taskTodoDataSource.load(new LoadConfig<TaskTodoDto>(0, 0, null, null), taskTodoLoadCallback);
	}

	@Override
	protected void loadData() {
		loadTaskGroupData();
		loadTaskTodoData();
	}

	@Override
	protected TaskTypeDto createDto() {
		TaskTypeDto dto = new TaskTypeDto();
		dto.setAccount(currentUser.getAppUserDto().getAccount());
		if (filters.containsKey(PARAM_KIND))
			dto.setKind(TaskKind.valueOf(filters.get(PARAM_KIND)));
		dto.setActive(true);
		return dto;
	}

	private void edit(String webSafeKey) {
		logger.info("TaskTypeEditorPresenter().edit()->webSafeKey=" + webSafeKey);

		resourceDelegate.withCallback(new AsyncCallback<TaskTypeDto>() {
			@Override
			public void onSuccess(TaskTypeDto dto) {
//				if (dto.getTodos() != null) {
//					List<TaskTodoDto> todos = dto.getTodos();
//					todos.sort((TaskTodoDto o1, TaskTodoDto o2) -> o1.getDescription().compareTo(o2.getDescription()));
//				}

				SetPageTitleEvent.fire(i18nCore.taskTypeEditorModifyTitle(), "", MenuItemType.MENU_ITEM,
						TaskTypeEditorPresenter.this);

				getView().edit(dto);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).get(webSafeKey);
	}

	@Override
	public void save(TaskTypeDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<TaskTypeDto>() {
			@Override
			public void onSuccess(TaskTypeDto dto) {
				placeManager.navigateBack();
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}

	@Override
	public void onAddTaskTodoEvent(AddTaskTodoEvent event) {
		logger.info("TaskTypeEditorPresenter().onAddTaskTodoEvent()");
		getView().addTaskTodos(event.getTodos());
	}
}