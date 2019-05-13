/**
 * 
 */
package io.crs.hsys.client.kip.browser.tasktype;

import java.util.List;
import java.util.logging.Logger;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.core.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.event.DisplayMessageEvent;
import io.crs.hsys.client.core.event.DisplayMessageEvent.DisplayMessageHandler;
import io.crs.hsys.client.core.event.DisplayMessageEvent.MessageTarget;
import io.crs.hsys.client.core.event.RefreshTableEvent.TableType;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.message.MessageData;
import io.crs.hsys.client.core.message.callback.AbstractAsyncCallback;
import io.crs.hsys.client.core.message.callback.ErrorHandlerAsyncCallback;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.kip.filter.KipFilterPresenterFactory;
import io.crs.hsys.client.kip.filter.taskgroup.AbstractTaskGroupFilterPresenter;
import io.crs.hsys.shared.api.TaskTypeResource;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public abstract class TaskTypeBrowserPresenter
		extends AbstractBrowserPresenter<TaskTypeDto, TaskTypeBrowserPresenter.MyView>
		implements TaskTypeBrowserUiHandlers, FilterChangeEvent.FilterChangeHandler, DisplayMessageHandler {
	private static Logger logger = Logger.getLogger(TaskTypeBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<TaskTypeBrowserUiHandlers> {
		void setData(List<TaskTypeDto> data);

		void showMessage(MessageData message);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();

	private final ResourceDelegate<TaskTypeResource> resourceDelegate;
	private final AbstractTaskGroupFilterPresenter<?> filter;
	private final CoreMessages i18nCore;

	TaskTypeBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<TaskTypeResource> resourceDelegate, KipFilterPresenterFactory filterFactory,
			CoreMessages i18nCore) {
		super(eventBus, view, placeManager);
		logger.info("TaskTypeBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterFactory.createTaskGroupFilter();
		this.i18nCore = i18nCore;

		addVisibleHandler(FilterChangeEvent.TYPE, this);

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_FILTER, filter);
		addRegisteredHandler(DisplayMessageEvent.TYPE, this);
	}

	@Override
	protected void loadData() {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<TaskTypeDto>>() {
			@Override
			public void onSuccess(List<TaskTypeDto> result) {
				getView().setData(result);
			}
		}).getAll();
	}

	@Override
	protected void deleteData(String webSafeKey) {
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<Void>(this, MessageTarget.TASK_TYPE, i18nCore) {
			@Override
			public void onSuccess(Void result) {
				loadData();
			}
		}).delete(webSafeKey);
	}

	@Override
	public void onFilterChange(FilterChangeEvent event) {
		logger.info("TaskTypeBrowserPresenter().onFilterChange()");
		loadData();
	}

	protected abstract String getChildName();

	@Override
	public void onDisplayMessage(DisplayMessageEvent event) {
		logger.info("TaskTypeBrowserPresenter().onDisplayMessage()->childName=" + getChildName());
		if (event.getTarget().equals(MessageTarget.TASK_TYPE)) {
			logger.info("TaskTypeBrowserPresenter().onDisplayMessage()-2");
			getView().showMessage(event.getMessage());
		}
	}

	@Override
	protected TableType getTableType() {
		return TableType.TASK_TYPE;
	}

}