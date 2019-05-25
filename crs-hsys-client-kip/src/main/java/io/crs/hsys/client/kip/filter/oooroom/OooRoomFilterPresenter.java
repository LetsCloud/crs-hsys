/**
 * 
 */
package io.crs.hsys.client.kip.filter.oooroom;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import io.crs.hsys.client.core.datasource.AppUserDataSource2;
import io.crs.hsys.client.core.datasource.RoomDataSource;
import io.crs.hsys.client.core.datasource.RoomTypeDataSource2;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.ui.filter.AbstractFilterPresenter;
import io.crs.hsys.client.core.ui.filter.AbstractFilterUiHandlers;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class OooRoomFilterPresenter extends AbstractFilterPresenter<OooRoomFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(OooRoomFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
		void setFromDate(Date fromDate);

		void setToDate(Date toDate);

		void setRoomTypeData(List<RoomTypeDtor> data);

		RoomTypeDtor getSelectedRoomType();

		void setRoomData(List<RoomDto> data);

		RoomDto getSelectedRoom();

		void setAppUserData(List<AppUserDtor> data);

		String getSelectedReporterKey();
	}

	protected final AppUserDataSource2 appUserDataSource;
	protected final RoomDataSource roomDataSource;
	protected final RoomTypeDataSource2 roomTypeDataSource;

	@Inject
	OooRoomFilterPresenter(EventBus eventBus, OooRoomFilterPresenter.MyView view, CurrentUser currentUser,
			AppUserDataSource2 appUserDataSource, RoomTypeDataSource2 roomTypeDataSource,
			RoomDataSource roomDataSource) {
		super(eventBus, view, currentUser);
		logger.info("OooRoomFilterPresenter()");
		this.appUserDataSource = appUserDataSource;
		this.roomDataSource = roomDataSource;
		this.roomTypeDataSource = roomTypeDataSource;
		getView().setUiHandlers(this);
	}

	@Override
	public void onBind() {
		super.onBind();
	}

	@Override
	public void onReveal() {
		super.onReveal();
		loadRoomData();
		loadRoomTypeData();
		loadAppUserData();
	}

	private void loadRoomData() {
		roomDataSource.setHotelKey(currentUser.getCurrentHotel().getWebSafeKey());
		LoadCallback<RoomDto> roomLoadCallback = new LoadCallback<RoomDto>() {

			@Override
			public void onSuccess(LoadResult<RoomDto> loadResult) {
				getView().setRoomData(loadResult.getData());
//				FilterChangeEvent.fire(TasksFilterPresenter.this, DataTable.ROOM_TYPE);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};

		roomDataSource.load(new LoadConfig<RoomDto>(0, 0, null, null), roomLoadCallback);
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

	private void loadAppUserData() {
		LoadCallback<AppUserDtor> appUserLoadCallback = new LoadCallback<AppUserDtor>() {

			@Override
			public void onSuccess(LoadResult<AppUserDtor> loadResult) {
				getView().setAppUserData(loadResult.getData());
//				FilterChangeEvent.fire(TasksFilterPresenter.this, DataTable.ROOM_TYPE);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};

		appUserDataSource.load(new LoadConfig<AppUserDtor>(0, 0, null, null), appUserLoadCallback);
	}

	public void setFromDate(Date fromDate) {
		getView().setFromDate(fromDate);
	}

	public void setToDate(Date toDate) {
		getView().setToDate(toDate);
	}
}
