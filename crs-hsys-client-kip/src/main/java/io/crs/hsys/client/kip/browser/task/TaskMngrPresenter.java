/**
 * 
 */
package io.crs.hsys.client.kip.browser.task;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.shared.api.TaskResource;
import io.crs.hsys.shared.constans.MenuItemType;
import io.crs.hsys.shared.constans.TaskStatus;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.browser.task.TaskActionEvent.TaskActionEventHandler;
import io.crs.hsys.client.kip.filter.KipFilterPresenterFactory;
import io.crs.hsys.client.kip.filter.tasks.TasksFilterPresenter;
import io.crs.hsys.client.kip.i18n.KipMessages;

/**
 * @author robi
 *
 */
public class TaskMngrPresenter extends Presenter<TaskMngrPresenter.MyView, TaskMngrPresenter.MyProxy>
		implements TaskMngrUiHandlers, TaskActionEventHandler {
	private static Logger logger = Logger.getLogger(TaskMngrPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<TaskMngrUiHandlers> {
		void setTasks(List<TaskDto> tasks);
	}

	@ProxyStandard
	@NameToken(KipNameTokens.TASK_MANAGER)
	interface MyProxy extends ProxyPlace<TaskMngrPresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> FILTER_SLOT = new SingleSlot<>();

	private final PlaceManager placeManager;
	private final ResourceDelegate<TaskResource> resourceDelegate;
	private KipMessages i18n;
	private final TasksFilterPresenter filter;

	@Inject
	TaskMngrPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager,
			ResourceDelegate<TaskResource> resourceDelegate, KipFilterPresenterFactory filterFactory,
			KipMessages i18n) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		logger.info("TaskMngrPresenter()");
		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.filter = filterFactory.createTasksFilter();
		this.i18n = i18n;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		this.addRegisteredHandler(TaskActionEvent.TYPE, this);
		setInSlot(FILTER_SLOT, filter);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(i18n.tasksTitle(), i18n.tasksSubTitle(), MenuItemType.MENU_ITEM, this);

		loadTasks();
	}

	private void loadTasks() {
//		getView().setTasks(dataBuilder.getTaskDtos(), res);

		resourceDelegate.withCallback(new AsyncCallback<List<TaskDto>>() {
			@Override
			public void onSuccess(List<TaskDto> result) {
				getView().setTasks(
						result.stream().sorted(Comparator.comparing(TaskDto::getRoom).thenComparing(TaskDto::getKind))
								.collect(Collectors.toList()));
			}

			@Override
			public void onFailure(Throwable caught) {
//				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).list();
	}

	private void changeTaskStatus(String webSafeKey, TaskStatus status) {
		resourceDelegate.withCallback(new AsyncCallback<TaskDto>() {
			@Override
			public void onSuccess(TaskDto result) {
			}

			@Override
			public void onFailure(Throwable caught) {
//				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).changeTaskStatus(webSafeKey, status);
	}

	@Override
	public void create() {
		Builder placeBuilder = new Builder().nameToken(CoreNameTokens.TASK_EDITOR);
		placeManager.revealPlace(placeBuilder.build());
	}

	@Override
	public void onTaskActionEvent(TaskActionEvent event) {
		logger.info("onTaskActionEvent().onTaskActionEvent()");
		switch (event.getAction()) {
		case CREATE:
			break;
		case START:
			logger.info("onTaskActionEvent().onTaskActionEvent()->START");
			changeTaskStatus(event.getTask().getWebSafeKey(), TaskStatus.TS_IN_PROGRESS);
			break;
		case PAUSE:
			logger.info("onTaskActionEvent().onTaskActionEvent()->PAUSE");
			changeTaskStatus(event.getTask().getWebSafeKey(), TaskStatus.TS_DEFFERED);
			break;
		case COMPLETE:
			logger.info("onTaskActionEvent().onTaskActionEvent()->COMPLETE");
			changeTaskStatus(event.getTask().getWebSafeKey(), TaskStatus.TS_COMPLETED);
			break;
		case EDIT:
			modify(event.getTask().getWebSafeKey());
			break;
		case DELETE:
			break;
		default:
			break;
		}
	}

	@Override
	public void modify(String webSafeKey) {
		Builder placeBuilder = new Builder().nameToken(CoreNameTokens.TASK_EDITOR);
		placeBuilder.with(WEBSAFEKEY, String.valueOf(webSafeKey));
		placeManager.revealPlace(placeBuilder.build());
	}
}
