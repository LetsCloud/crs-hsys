/**
 * 
 */
package io.crs.hsys.client.kip.meditor.taskgroup;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import io.crs.hsys.client.core.event.DisplayMessageEvent;
import io.crs.hsys.client.core.event.DisplayMessageEvent.DisplayMessageHandler;
import io.crs.hsys.client.core.event.RefreshTableEvent;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.message.MessageData;
import io.crs.hsys.client.core.message.callback.ErrorHandlerAsyncCallback;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.TaskGroupResource;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.dto.task.TaskGroupDto;

/**
 * @author robi
 *
 */
public abstract class TaskGroupEditorPresenter extends PresenterWidget<TaskGroupEditorPresenter.MyView>
		implements TaskGroupEditorUiHandlers, DisplayMessageHandler {
	private static Logger logger = Logger.getLogger(TaskGroupEditorPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<TaskGroupEditorUiHandlers> {
		void open(Boolean isNew, TaskGroupDto dto);

		void showMessage(MessageData message);

		void close();
	}

	private final ResourceDelegate<TaskGroupResource> resourceDelegate;
	private final CurrentUser currentUser;
	private final CoreMessages i18nCore;

	private Boolean isNew;

	@Inject
	TaskGroupEditorPresenter(EventBus eventBus, MyView view, ResourceDelegate<TaskGroupResource> resourceDelegate,
			CurrentUser currentUser, CoreMessages i18nCore) {
		super(eventBus, view);
		logger.info("TaskGroupEditorPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;
		this.i18nCore = i18nCore;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(DisplayMessageEvent.TYPE, this);
	}

	@Override
	public void create() {
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
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<TaskGroupDto>(this, i18nCore) {
			@Override
			public void onSuccess(TaskGroupDto dto) {
				RefreshTableEvent.fire(TaskGroupEditorPresenter.this, RefreshTableEvent.TableType.TASK_GROUP);
				getView().close();
			}
		}).saveOrCreate(dto);
	}

	private void updateEntity(TaskGroupDto dto) {
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<TaskGroupDto>(this, i18nCore) {
			@Override
			public void onSuccess(TaskGroupDto dto) {
				RefreshTableEvent.fire(TaskGroupEditorPresenter.this, RefreshTableEvent.TableType.TASK_GROUP);
				getView().close();
			}
		}).saveOrCreate(dto);
	}

	protected abstract TaskKind getDefaultTaskKind();

	@Override
	public void onDisplayMessage(DisplayMessageEvent event) {
//		logger.info("TaskGroupEditorPresenter().onDisplayMessage()");
//		getView().showMessage(event.getMessage());
	}
}