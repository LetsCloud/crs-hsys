/**
 * 
 */
package io.crs.hsys.client.kip.tasks;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

import java.util.List;
import java.util.logging.Logger;

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
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.filter.KipFilterPresenterFactory;
import io.crs.hsys.client.kip.filter.tasks.TasksFilterPresenter;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.client.kip.resources.KipGssResources;

/**
 * @author robi
 *
 */
public class TaskMngrPresenter extends Presenter<TaskMngrPresenter.MyView, TaskMngrPresenter.MyProxy>
		implements TaskMngrUiHandlers {
	private static Logger logger = Logger.getLogger(TaskMngrPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<TaskMngrUiHandlers> {
		void setTasks(List<TaskDto> tasks, KipGssResources res);
	}

	@ProxyStandard
	@NameToken(KipNameTokens.TASK_MANAGER)
	interface MyProxy extends ProxyPlace<TaskMngrPresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> FILTER_SLOT = new SingleSlot<>();

	private final PlaceManager placeManager;
	private final ResourceDelegate<TaskResource> resourceDelegate;
	private KipGssResources res;
	private KipMessages i18n;
	private final TasksFilterPresenter filter;

	@Inject
	TaskMngrPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager,
			ResourceDelegate<TaskResource> resourceDelegate, KipFilterPresenterFactory filterFactory,
			KipGssResources res, KipMessages i18n) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		logger.info("TaskMngrPresenter()");
		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.filter = filterFactory.createTasksFilter();
		this.res = res;
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
//		getView().setTasks(dataBuilder.getTaskDtos(), res);

		resourceDelegate.withCallback(new AsyncCallback<List<TaskDto>>() {
			@Override
			public void onSuccess(List<TaskDto> dto) {
				getView().setTasks(dto, res);
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

	@Override
	public void modify(String webSafeKey) {
		Builder placeBuilder = new Builder().nameToken(CoreNameTokens.TASK_EDITOR);
		placeBuilder.with(WEBSAFEKEY, String.valueOf(webSafeKey));
		placeManager.revealPlace(placeBuilder.build());
	}
}
