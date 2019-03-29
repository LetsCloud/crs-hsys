/**
 * 
 */
package io.crs.hsys.client.kip.browser.task;

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
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.shared.api.TaskResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.browser.task.widget.TaskWidgetFactory;
import io.crs.hsys.client.kip.browser.task.widget.TaskWidgetPresenter;
import io.crs.hsys.client.kip.filter.KipFilterPresenterFactory;
import io.crs.hsys.client.kip.filter.tasks.TasksFilterPresenter;
import io.crs.hsys.client.kip.i18n.KipMessages;

/**
 * @author robi
 *
 */
public class TaskMngrPresenter extends Presenter<TaskMngrPresenter.MyView, TaskMngrPresenter.MyProxy>
		implements TaskMngrUiHandlers {
	private static Logger logger = Logger.getLogger(TaskMngrPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<TaskMngrUiHandlers> {
		void clearTasksPanel();
	}

	@ProxyStandard
	@NameToken(KipNameTokens.TASK_MANAGER)
	interface MyProxy extends ProxyPlace<TaskMngrPresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> FILTER_SLOT = new SingleSlot<>();
	public static final Slot<PresenterWidget<?>> SLOT_TASKS = new Slot<>();

	private final PlaceManager placeManager;
	private final ResourceDelegate<TaskResource> resourceDelegate;
	private KipMessages i18n;
	private final TasksFilterPresenter filter;
	private final TaskWidgetFactory taskWidgetFactory;

	@Inject
	TaskMngrPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager,
			ResourceDelegate<TaskResource> resourceDelegate, KipFilterPresenterFactory filterFactory,
			TaskWidgetFactory taskWidgetFactory, KipMessages i18n) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		logger.info("TaskMngrPresenter()");
		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.filter = filterFactory.createTasksFilter();
		this.taskWidgetFactory = taskWidgetFactory;
		this.i18n = i18n;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(FILTER_SLOT, filter);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(i18n.tasksTitle(), i18n.tasksSubTitle(), MenuItemType.MENU_ITEM, this);

		loadTasks();
	}

	private void loadTasks() {
		resourceDelegate.withCallback(new AsyncCallback<List<TaskDto>>() {
			@Override
			public void onSuccess(List<TaskDto> result) {
				result = result.stream().sorted(Comparator.comparing(TaskDto::getRoom).thenComparing(TaskDto::getKind))
						.collect(Collectors.toList());
				getView().clearTasksPanel();
				for (TaskDto task : result) {
					TaskWidgetPresenter taskWidget = taskWidgetFactory.createTaskWidget();
					taskWidget.setTask(task);
					addToSlot(SLOT_TASKS, taskWidget);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
//				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).list();
	}

	@Override
	public void create() {
		Builder placeBuilder = new Builder().nameToken(CoreNameTokens.TASK_EDITOR);
		placeManager.revealPlace(placeBuilder.build());
	}
}
