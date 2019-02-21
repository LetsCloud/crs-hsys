/**
 * 
 */
package io.crs.hsys.client.kip.browser.hktasktype;

import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.filter.FilterPresenterFactory;
import io.crs.hsys.client.core.filter.room.RoomFilterPresenter;
import io.crs.hsys.client.core.ui.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.core.util.AbstractAsyncCallback;
import io.crs.hsys.shared.api.TaskTypeResource;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public class HkTaskTypeBrowserPresenter extends AbstractBrowserPresenter<TaskTypeDto, HkTaskTypeBrowserPresenter.MyView>
		implements HkTaskTypeBrowserUiHandlers, FilterChangeEvent.FilterChangeHandler {
	private static Logger logger = Logger.getLogger(HkTaskTypeBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<HkTaskTypeBrowserUiHandlers> {
		void setData(List<TaskTypeDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();

	private final ResourceDelegate<TaskTypeResource> resourceDelegate;
	private final RoomFilterPresenter filter;

	@Inject
	HkTaskTypeBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<TaskTypeResource> resourceDelegate, FilterPresenterFactory filterPresenterFactory) {
		super(eventBus, view, placeManager);
		logger.info("HkTaskTypeBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterPresenterFactory.createRoomFilterPresenter();

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
	}

	@Override
	protected String getCreatorNameToken() {
		return CoreNameTokens.ROOM_EDITOR;
	}

	@Override
	protected String getEditorNameToken() {
		return CoreNameTokens.ROOM_EDITOR;
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
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<TaskTypeDto>>() {
			@Override
			public void onSuccess(List<TaskTypeDto> result) {
				getView().setData(result);
			}
		}).getAll(filter.getSelectedHotel().getWebSafeKey(), filter.isOnlyActive());

		addFilter(HOTEL_KEY, filter.getSelectedHotel().getWebSafeKey());
	}
}