/**
 * 
 */
package io.crs.hsys.client.fro.filter.ratemngr;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import io.crs.hsys.client.core.datasource.HotelDataSource2;
import io.crs.hsys.client.core.datasource.RateCodeDataSource2;
import io.crs.hsys.client.core.datasource.RoomTypeDataSource2;
import io.crs.hsys.client.core.filter.AbstractFilterUiHandlers;
import io.crs.hsys.client.core.filter.FilterChangeEvent;
import io.crs.hsys.client.core.filter.FilterChangeEvent.DataTable;
import io.crs.hsys.client.core.filter.hotelchild.AbstractHotelChildFilterPresenter;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.dto.hotel.HotelDtor;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.dto.rate.RateCodeDtor;

/**
 * @author robi
 *
 */
public class RateMngrFilterPresenter extends AbstractHotelChildFilterPresenter<RateMngrFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(RateMngrFilterPresenter.class.getName());

	public interface MyView extends AbstractHotelChildFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {

		void setHotelData(List<HotelDtor> data);

		HotelDtor getSelectedHotel();

		void setRateCodeData(List<RateCodeDtor> data);

		List<RateCodeDtor> getSelectedRateCodes();

		void setRoomTypeData(List<RoomTypeDtor> data);

		List<RoomTypeDtor> getSelectedRoomTypes();

	}

//	protected final HotelDataSource2 hotelDataSource;
	protected final RateCodeDataSource2 rateCodeDataSource;
	protected final RoomTypeDataSource2 roomTypeDataSource;

	@Inject
	RateMngrFilterPresenter(EventBus eventBus, MyView view, CurrentUser currentUser, HotelDataSource2 hotelDataSource,
			RateCodeDataSource2 rateCodeDataSource, RoomTypeDataSource2 roomTypeDataSource) {
		super(eventBus, view, currentUser, hotelDataSource);
		logger.info("RateMngrFilterPresenter()");
		this.rateCodeDataSource = rateCodeDataSource;
		this.roomTypeDataSource = roomTypeDataSource;
		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		loadRateCodeData();
		loadRoomTypeData();
	}

	private void loadRateCodeData() {
		rateCodeDataSource.setHotelKey(currentUser.getCurrentHotel().getWebSafeKey());
		LoadCallback<RateCodeDtor> rateCodeLoadCallback = new LoadCallback<RateCodeDtor>() {

			@Override
			public void onSuccess(LoadResult<RateCodeDtor> loadResult) {
				getView().setRateCodeData(loadResult.getData());
//				FilterChangeEvent.fire(TasksFilterPresenter.this, DataTable.ROOM_TYPE);
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
//				FilterChangeEvent.fire(TasksFilterPresenter.this, DataTable.ROOM_TYPE);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};

		roomTypeDataSource.load(new LoadConfig<RoomTypeDtor>(0, 0, null, null), roomTypeLoadCallback);
	}

	public Boolean isOnlyActive() {
		return getView().isOnlyActive();
	}

	@Override
	public void filterChange() {
		FilterChangeEvent.fire(RateMngrFilterPresenter.this, DataTable.RATE_CODE);
	}
}
