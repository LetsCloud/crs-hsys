/**
 * 
 */
package io.crs.hsys.client.fro.rate.updater;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.datasource.RateCodeDataSource2;
import io.crs.hsys.client.core.datasource.RoomTypeDataSource2;
import io.crs.hsys.client.core.event.ContentPushEvent;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.filter.FilterChangeEvent;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.fro.NameTokens;
import io.crs.hsys.shared.api.RoomRateResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.dto.rate.RateCodeDtor;
import io.crs.hsys.shared.dto.rate.update.RoomRateUpdateDto;

/**
 * @author robi
 *
 */
public class RateUpdaterPresenter extends Presenter<RateUpdaterPresenter.MyView, RateUpdaterPresenter.MyProxy>
		implements RateUpdaterUiHandlers, FilterChangeEvent.FilterChangeHandler, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(RateUpdaterPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<RateUpdaterUiHandlers> {
		void setRateCodeData(List<RateCodeDtor> data);

		void setRoomTypeData(List<RoomTypeDtor> data);

		void edit(RoomRateUpdateDto dto);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.RATE_UPDATER)
//@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<RateUpdaterPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<RoomRateResource> resourceDelegate;
	private final RateCodeDataSource2 rateCodeDataSource;
	private final RoomTypeDataSource2 roomTypeDataSource;
	private final CurrentUser currentUser;

	@Inject
	RateUpdaterPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager,
			ResourceDelegate<RoomRateResource> resourceDelegate, RateCodeDataSource2 rateCodeDataSource,
			RoomTypeDataSource2 roomTypeDataSource, CurrentUser currentUser) {
		super(eventBus, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("RateUpdaterPresenter()");
		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.rateCodeDataSource = rateCodeDataSource;
		this.roomTypeDataSource = roomTypeDataSource;
		this.currentUser = currentUser;

		addRegisteredHandler(ContentPushEvent.TYPE, this);
		addVisibleHandler(FilterChangeEvent.TYPE, this);

		getView().setUiHandlers(this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire("Rate Updater", "", MenuItemType.MENU_ITEM, this);
		loadRoomTypeData();
		loadRateCodeData();
		RoomRateUpdateDto dto = new RoomRateUpdateDto();
		dto.setHotel(currentUser.getCurrentHotel());
		getView().edit(dto);
	}

	private void loadRateCodeData() {
		rateCodeDataSource.setHotelKey(currentUser.getCurrentHotel().getWebSafeKey());
		LoadCallback<RateCodeDtor> rateCodeLoadCallback = new LoadCallback<RateCodeDtor>() {

			@Override
			public void onSuccess(LoadResult<RateCodeDtor> loadResult) {
				getView().setRateCodeData(loadResult.getData());
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};

		rateCodeDataSource.load(new LoadConfig<RateCodeDtor>(0, 0, null, null), rateCodeLoadCallback);
	}

	private void loadRoomTypeData() {
		roomTypeDataSource.setHotelKey(currentUser.getCurrentHotel().getWebSafeKey());
		LoadCallback<RoomTypeDtor> roomTypeLoadCallback = new LoadCallback<RoomTypeDtor>() {

			@Override
			public void onSuccess(LoadResult<RoomTypeDtor> loadResult) {
				getView().setRoomTypeData(loadResult.getData());
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};

		roomTypeDataSource.load(new LoadConfig<RoomTypeDtor>(0, 0, null, null), roomTypeLoadCallback);
	}

	@Override
	public void onFilterChange(FilterChangeEvent event) {
		logger.info("RateBrowserPresenter()->onFilterChange()");
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
	}

	@Override
	public void save(RoomRateUpdateDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<RoomRateUpdateDto>() {
			@Override
			public void onSuccess(RoomRateUpdateDto dto) {
				placeManager.navigateBack();
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		}).roomRateUpdate(dto);
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub

	}
}
