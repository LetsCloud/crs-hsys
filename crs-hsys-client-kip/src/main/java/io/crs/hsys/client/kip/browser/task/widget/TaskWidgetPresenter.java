/**
 * 
 */
package io.crs.hsys.client.kip.browser.task.widget;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.kip.browser.task.TaskActionEvent;
import io.crs.hsys.client.kip.browser.task.TaskActionEvent.TaskActionEventHandler;
import io.crs.hsys.shared.api.TaskResource;
import io.crs.hsys.shared.constans.TaskStatus;
import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author CR
 *
 */
public class TaskWidgetPresenter extends PresenterWidget<TaskWidgetPresenter.MyView>
		implements TaskWidgetUiHandlers, TaskActionEventHandler {
	private static Logger logger = Logger.getLogger(TaskWidgetPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<TaskWidgetUiHandlers> {
		void setTask(TaskDto data);
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<TaskResource> resourceDelegate;

	@Inject
	TaskWidgetPresenter(EventBus eventBus, MyView view, PlaceManager placeManager,
			ResourceDelegate<TaskResource> resourceDelegate) {
		super(eventBus, view);

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;

		getView().setUiHandlers(this);
	}

	@Override
	public void onBind() {
		super.onBind();
		addRegisteredHandler(TaskActionEvent.TYPE, this);
	}

	public void setTask(TaskDto task) {
		getView().setTask(task);
	}

	@Override
	public void startTask(TaskDto task) {
		changeTaskStatus(task.getWebSafeKey(), TaskStatus.TS_IN_PROGRESS);
	}

	@Override
	public void pauseTask(TaskDto task) {
		changeTaskStatus(task.getWebSafeKey(), TaskStatus.TS_DEFFERED);
	}

	@Override
	public void completeTask(TaskDto task) {
		changeTaskStatus(task.getWebSafeKey(), TaskStatus.TS_COMPLETED);
	}

	@Override
	public void reassignTask(TaskDto task) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyTask(TaskDto task) {
//		TaskActionEvent.fire(this, TaskAction.EDIT, task);
		Builder placeBuilder = new Builder().nameToken(CoreNameTokens.TASK_EDITOR);
		placeBuilder.with(WEBSAFEKEY, String.valueOf(task.getWebSafeKey()));
		placeManager.revealPlace(placeBuilder.build());
	}

	@Override
	public void deleteTask(TaskDto task) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTaskActionEvent(TaskActionEvent event) {
		logger.info("onTaskActionEvent().onTaskActionEvent()");
	}

	private void changeTaskStatus(String webSafeKey, TaskStatus status) {
		resourceDelegate.withCallback(new AsyncCallback<TaskDto>() {
			@Override
			public void onSuccess(TaskDto result) {
				getView().setTask(result);
			}

			@Override
			public void onFailure(Throwable caught) {
//				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).changeTaskStatus(webSafeKey, status);
	}
}