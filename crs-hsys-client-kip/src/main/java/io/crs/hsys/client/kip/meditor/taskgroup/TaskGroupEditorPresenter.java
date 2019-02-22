/**
 * 
 */
package io.crs.hsys.client.kip.meditor.taskgroup;

import java.util.logging.Logger;

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
import io.crs.hsys.shared.api.TaskGroupResource;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.common.UserGroupDto;
import io.crs.hsys.shared.dto.task.TaskGroupDto;

/**
 * @author robi
 *
 */
public abstract class TaskGroupEditorPresenter extends PresenterWidget<TaskGroupEditorPresenter.MyView>
		implements TaskGroupEditorUiHandlers {
	private static Logger logger = Logger.getLogger(TaskGroupEditorPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<TaskGroupEditorUiHandlers> {
		void open(Boolean isNew, TaskGroupDto dto);

		void displayError(EntityPropertyCode code, String message);

		void close();
	}

	private final ResourceDelegate<TaskGroupResource> resourceDelegate;

	private final CurrentUser currentUser;

	private Boolean isNew;

	@Inject
	TaskGroupEditorPresenter(EventBus eventBus, MyView view, ResourceDelegate<TaskGroupResource> resourceDelegate,
			CurrentUser currentUser) {
		super(eventBus, view);

		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	public void create() {
		logger.info("create()");
		isNew = true;

		TaskGroupDto dto = new TaskGroupDto();
		dto.setAccount(currentUser.getAppUserDto().getAccount());
		dto.setKind(getDefaultTaskKind());
		dto.setActive(true);

		getView().open(isNew, dto);
	}

	@Override
	public void edit(TaskGroupDto dto) {
		isNew = false;
		getView().open(isNew, dto);
	}

	@Override
	public void save(TaskGroupDto dto) {
		if (isNew) {
			createEntity(dto);
		} else {
			updateEntity(dto);
		}
	}

	private void createEntity(TaskGroupDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<TaskGroupDto>() {
			@Override
			public void onSuccess(TaskGroupDto dto) {
				RefreshTableEvent.fire(TaskGroupEditorPresenter.this, RefreshTableEvent.TableType.USER_GROUP);
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

	private void updateEntity(TaskGroupDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<UserGroupDto>() {
			@Override
			public void onSuccess(UserGroupDto dto) {
				RefreshTableEvent.fire(TaskGroupEditorPresenter.this, RefreshTableEvent.TableType.USER_GROUP);
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
}