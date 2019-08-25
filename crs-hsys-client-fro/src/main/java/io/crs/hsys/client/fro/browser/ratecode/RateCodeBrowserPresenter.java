/**
 * 
 */
package io.crs.hsys.client.fro.browser.ratecode;

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
import io.crs.hsys.client.core.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.event.RefreshTableEvent.TableType;
import io.crs.hsys.client.core.filter.FilterPresenterFactory;
import io.crs.hsys.client.core.filter.roomtype.RoomTypeFilterPresenter;
import io.crs.hsys.client.core.message.callback.AbstractAsyncCallback;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.fro.NameTokens;
import io.crs.hsys.shared.api.RateCodeResource;
import io.crs.hsys.shared.dto.hotel.RateCodeDto;

/**
 * @author robi
 *
 */
public class RateCodeBrowserPresenter extends AbstractBrowserPresenter<RateCodeDto, RateCodeBrowserPresenter.MyView>
		implements RateCodeBrowserUiHandlers, FilterChangeEvent.FilterChangeHandler {
	private static Logger logger = Logger.getLogger(RateCodeBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<RateCodeBrowserUiHandlers> {
		void setData(List<RateCodeDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();

	private final ResourceDelegate<RateCodeResource> resourceDelegate;
	private final RoomTypeFilterPresenter filter;

	@Inject
	RateCodeBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<RateCodeResource> resourceDelegate, FilterPresenterFactory filterPresenterFactory) {
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
//filter.onReveal();
	}

	@Override
	protected void loadData() {
	}

	@Override
	protected String getCreatorNameToken() {
		return NameTokens.RATECODE_EDITOR;
	}

	@Override
	protected String getEditorNameToken() {
		return NameTokens.RATECODE_EDITOR;
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
		logger.info("RoomTypeTablePresenter().onFilterChange()");
		/*
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<RateCodeDto>>() {
			@Override
			public void onSuccess(List<RateCodeDto> result) {
				getView().setData(result);
			}
		}).getAll(filter.getSelectedHotel().getWebSafeKey(), filter.isOnlyActive(), filter.getSelectedInventoryType());

		addFilter(HOTEL_KEY, filter.getSelectedHotel().getWebSafeKey());
		*/
	}

	@Override
	protected TableType getTableType() {
		return TableType.ROOM_TYPE;
	}

}