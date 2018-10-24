/**
 * 
 */
package io.crs.hsys.client.cfg.browser.roomtype;

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

import io.crs.hsys.client.cfg.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.cfg.filter.FilterChangeEvent;
import io.crs.hsys.client.cfg.filter.FilterPresenterFactory;
import io.crs.hsys.client.cfg.filter.roomtype.RoomTypeFilterPresenter;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.util.AbstractAsyncCallback;
import io.crs.hsys.shared.api.RoomTypeResource;
import io.crs.hsys.shared.dto.hotel.RoomTypeDto;

import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;

/**
 * @author robi
 *
 */
public class RoomTypeBrowserPresenter extends AbstractBrowserPresenter<RoomTypeDto, RoomTypeBrowserPresenter.MyView>
		implements RoomTypeBrowserUiHandlers, FilterChangeEvent.FilterChangeHandler {
	private static Logger logger = Logger.getLogger(RoomTypeBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<RoomTypeBrowserUiHandlers> {
		void setData(List<RoomTypeDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();

	private final ResourceDelegate<RoomTypeResource> resourceDelegate;
	private final RoomTypeFilterPresenter filter;

	@Inject
	RoomTypeBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<RoomTypeResource> resourceDelegate, FilterPresenterFactory filterPresenterFactory) {
		super(eventBus, view, placeManager);
		logger.info("RoomTypeTablePresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterPresenterFactory.createRoomTypeFilterPresenter();

		addVisibleHandler(FilterChangeEvent.TYPE, this);

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_FILTER, filter);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
//		filter.onReveal();
	}

	@Override
	protected void loadData() {
	}

	@Override
	protected String getCreatorNameToken() {
		return CoreNameTokens.ROOMTYPE_EDITOR;
	}

	@Override
	protected String getEditorNameToken() {
		return CoreNameTokens.ROOMTYPE_EDITOR;
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
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<RoomTypeDto>>() {
			@Override
			public void onSuccess(List<RoomTypeDto> result) {
				getView().setData(result);
			}
		}).getAll(filter.getSelectedHotel().getWebSafeKey(), filter.isOnlyActive(), filter.getSelectedInventoryType());

		addFilter(HOTEL_KEY, filter.getSelectedHotel().getWebSafeKey());
	}
}