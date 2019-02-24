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

import io.crs.hsys.client.core.ui.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.core.util.AbstractAsyncCallback;
import io.crs.hsys.client.kip.filter.KipFilterPresenterFactory;
import io.crs.hsys.client.kip.filter.taskgroup.TaskGroupFilterPresenter;
import io.crs.hsys.shared.api.TaskTypeResource;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public abstract class TaskTypeBrowserPresenter
		extends AbstractBrowserPresenter<TaskTypeDto, TaskTypeBrowserPresenter.MyView>
		implements TaskTypeBrowserUiHandlers, FilterChangeEvent.FilterChangeHandler {
	private static Logger logger = Logger.getLogger(TaskTypeBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<TaskTypeBrowserUiHandlers> {
		void setData(List<TaskTypeDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();

	private final ResourceDelegate<TaskTypeResource> resourceDelegate;
	private final TaskGroupFilterPresenter filter;

	TaskTypeBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<TaskTypeResource> resourceDelegate, KipFilterPresenterFactory filterFactory) {
		super(eventBus, view, placeManager);
		logger.info("TaskTypeBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterFactory.createTaskGroupFilterPresenter();

		addVisibleHandler(FilterChangeEvent.TYPE, this);

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_FILTER, filter);
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
		resourceDelegate.withCallback(new AbstractAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				loadData();
			}
		}).delete(webSafeKey);
	}

	@Override
	public void onFilterChange(FilterChangeEvent event) {
		loadData();
	}
}