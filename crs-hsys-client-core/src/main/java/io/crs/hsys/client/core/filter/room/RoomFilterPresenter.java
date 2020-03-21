package io.crs.hsys.client.core.filter.room;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import io.crs.hsys.client.core.datasource.HotelDataSource2;
import io.crs.hsys.client.core.datasource.RoomTypeDataSource2;
import io.crs.hsys.client.core.filter.AbstractFilterUiHandlers;
import io.crs.hsys.client.core.filter.hotelchild.AbstractHotelChildFilterPresenter;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class RoomFilterPresenter extends AbstractHotelChildFilterPresenter<RoomFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(RoomFilterPresenter.class.getName());

	public interface MyView extends AbstractHotelChildFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {

		void setFloors(List<String> floors);

		String getSelectedFloor();

		void setRoomTypeData(List<RoomTypeDtor> roomTypeData);

		List<String> getSelectedRoomTypeKeys();
	}

	private final RoomTypeDataSource2 roomTypeDataSource;
	private final CurrentUser currentUser;

	@Inject
	RoomFilterPresenter(EventBus eventBus, MyView view, CurrentUser currentUser, HotelDataSource2 hotelDataSource,
			RoomTypeDataSource2 roomTypeDataSource) {
		super(eventBus, view, currentUser, hotelDataSource);
		logger.info("RoomFilterPresenter()");

		this.roomTypeDataSource = roomTypeDataSource;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		logger.info("RoomFilterPresenter().onReveal()");
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
		logger.info("RoomFilterPresenter().onReveal()-2");

		roomTypeDataSource.setHotelKey(currentUser.getCurrentHotel().getWebSafeKey());
		logger.info("RoomFilterPresenter().onReveal()-3");
//		roomTypeDataSource.setOnlyActive(true);
		logger.info("RoomFilterPresenter().onReveal()-4");
//		roomTypeDataSource.setInventoryType(InventoryType.PHYS);
		roomTypeDataSource.load(new LoadConfig<RoomTypeDtor>(0, 0, null, null), roomTypeLoadCallback);
		logger.info("RoomFilterPresenter().onReveal()-5");
	}

	public void setFloors(List<String> floors) {
		getView().setFloors(floors);
	}

	public String getSelectedFloor() {
		return getView().getSelectedFloor();
	}

	public List<String> getSelectedRoomTypeKeys() {
		return getView().getSelectedRoomTypeKeys();
	}

	@Override
	public void filterChange() {
		// TODO Auto-generated method stub
		
	}
}
