/**
 * 
 */
package io.crs.hsys.client.kip.task.editor;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import io.crs.hsys.client.core.event.RefreshTableEvent;
import io.crs.hsys.client.core.gin.CustomActionException;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.TaskResource;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public class TaskEditorPresenter extends PresenterWidget<TaskEditorPresenter.MyView> implements TaskEditorUiHandlers {

	public interface MyView extends View, HasUiHandlers<TaskEditorUiHandlers> {
		void open(Boolean isNew, TaskDto dto);

		void displayError(EntityPropertyCode code, String message);

		void close();
	}

	private final ResourceDelegate<TaskResource> resourceDelegate;

	private final CurrentUser currentUser;

	private Boolean isNew;

	@Inject
	TaskEditorPresenter(EventBus eventBus, MyView view, ResourceDelegate<TaskResource> resourceDelegate,
			CurrentUser currentUser) {
		super(eventBus, view);

		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	public void create() {
		isNew = true;

		TaskDto dto = new TaskDto();
		dto.setAccount(currentUser.getAppUserDto().getAccount());

		getView().open(isNew, dto);
	}

	@Override
	public void edit(TaskDto dto) {
		isNew = false;
		getView().open(isNew, dto);
	}

	@Override
	public void save(TaskDto dto) {
		if (isNew) {
			createEntity(dto);
		} else {
			updateEntity(dto);
		}
	}

	private void createEntity(TaskDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<TaskDto>() {
			@Override
			public void onSuccess(TaskDto dto) {
				RefreshTableEvent.fire(TaskEditorPresenter.this, RefreshTableEvent.TableType.USER_GROUP);
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

	private void updateEntity(TaskDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<TaskDto>() {
			@Override
			public void onSuccess(TaskDto dto) {
				RefreshTableEvent.fire(TaskEditorPresenter.this, RefreshTableEvent.TableType.USER_GROUP);
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
}