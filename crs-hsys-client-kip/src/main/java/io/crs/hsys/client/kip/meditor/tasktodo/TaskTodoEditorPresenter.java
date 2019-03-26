/**
 * 
 */
package io.crs.hsys.client.kip.meditor.tasktodo;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import io.crs.hsys.client.core.datasource.TaskGroupDataSource;
import io.crs.hsys.client.core.event.RefreshTableEvent;
import io.crs.hsys.client.core.gin.CustomActionException;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.TaskTodoResource;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.task.TaskGroupDto;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public abstract class TaskTodoEditorPresenter extends PresenterWidget<TaskTodoEditorPresenter.MyView>
		implements TaskTodoEditorUiHandlers {
	private static Logger logger = Logger.getLogger(TaskTodoEditorUiHandlers.class.getName());

	public interface MyView extends View, HasUiHandlers<TaskTodoEditorUiHandlers> {
		void setTaskGroupData(List<TaskGroupDto> data);
		
		void open(Boolean isNew, TaskTodoDto dto);

		void displayError(EntityPropertyCode code, String message);

		void close();
	}

	private final ResourceDelegate<TaskTodoResource> resourceDelegate;
	private final TaskGroupDataSource taskGroupDataSource;
	private final CurrentUser currentUser;

	private Boolean isNew;

	@Inject
	TaskTodoEditorPresenter(EventBus eventBus, MyView view, ResourceDelegate<TaskTodoResource> resourceDelegate,TaskGroupDataSource taskGroupDataSource,
			CurrentUser currentUser) {
		super(eventBus, view);
		logger.info("TaskTodoEditorPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.taskGroupDataSource = taskGroupDataSource;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	public void create() {
		isNew = true;
		loadData(isNew, null);
	}

	@Override
	public void edit(TaskTodoDto dto) {
		isNew = false;
		loadData(isNew, dto);
	}

	@Override
	public void save(TaskTodoDto dto) {
		if (isNew) {
			createEntity(dto);
		} else {
			updateEntity(dto);
		}
	}

	private void createEntity(TaskTodoDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<TaskTodoDto>() {
			@Override
			public void onSuccess(TaskTodoDto dto) {
				RefreshTableEvent.fire(TaskTodoEditorPresenter.this, RefreshTableEvent.TableType.USER_GROUP);
				getView().close();
			}

			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof CustomActionException) {
					customMessage((CustomActionException) caught);
					return;
				}
				getView().displayError(EntityPropertyCode.USER_GROUP_NAME, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}

	private void updateEntity(TaskTodoDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<TaskTodoDto>() {
			@Override
			public void onSuccess(TaskTodoDto dto) {
				RefreshTableEvent.fire(TaskTodoEditorPresenter.this, RefreshTableEvent.TableType.TASK_TODO);
				getView().close();
			}

			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof CustomActionException) {
					customMessage((CustomActionException) caught);
					return;
				}
				getView().displayError(EntityPropertyCode.USER_GROUP_NAME, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}

	private void customMessage(CustomActionException e) {
		getView().displayError(EntityPropertyCode.USER_GROUP_NAME,
				e.getErDto().getProperty() + "/" + e.getErDto().getExceptionType());
	}

	protected abstract TaskKind getDefaultTaskKind();
	
	private void loadData(Boolean isNew, TaskTodoDto dto) {
		LoadCallback<TaskGroupDto> taskGroupLoadCallback = new LoadCallback<TaskGroupDto>() {
			@Override
			public void onSuccess(LoadResult<TaskGroupDto> loadResult) {
				getView().setTaskGroupData(loadResult.getData());
				if (isNew) {
					TaskTodoDto dto = new TaskTodoDto();
					dto.setAccount(currentUser.getAppUserDto().getAccount());
					dto.setKind(getDefaultTaskKind());
					dto.setActive(true);
					dto.setTimeRequired(0);

					getView().open(isNew, dto);
				} else {
					getView().open(isNew, dto);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		taskGroupDataSource.load(new LoadConfig<TaskGroupDto>(0, 0, null, null), taskGroupLoadCallback);
		
	}
}