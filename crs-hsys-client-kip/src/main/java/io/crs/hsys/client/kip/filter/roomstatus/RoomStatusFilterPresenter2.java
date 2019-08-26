/**
 * 
 */
package io.crs.hsys.client.kip.filter.roomstatus;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import io.crs.hsys.client.core.datasource.RoomTypeDataSource2;
import io.crs.hsys.client.core.filter.AbstractFilterPresenter;
import io.crs.hsys.client.core.filter.AbstractFilterUiHandlers;
import io.crs.hsys.client.core.filter.FilterChangeEvent;
import io.crs.hsys.client.core.filter.FilterChangeEvent.DataTable;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class RoomStatusFilterPresenter2 extends AbstractFilterPresenter<RoomStatusFilterPresenter2.MyView> {
	private static Logger logger = Logger.getLogger(RoomStatusFilterPresenter2.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
		void setRoomTypeData(List<RoomTypeDtor> hotelData);

		RoomTypeDtor getSelectedRoomType();
	}

	protected final RoomTypeDataSource2 roomTypeDataSource;

	@Inject
	RoomStatusFilterPresenter2(EventBus eventBus, RoomStatusFilterPresenter2.MyView view, CurrentUser currentUser,
			RoomTypeDataSource2 roomTypeDataSource) {
		super(eventBus, view, currentUser);
		logger.info("RoomStatusFilterPresenter2()");
		this.roomTypeDataSource = roomTypeDataSource;
		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		logger.info("RoomStatusFilterPresenter2().onReveal()->webSafeKey=" + currentUser.getCurrentHotel().getWebSafeKey());
		roomTypeDataSource.setHotelKey(currentUser.getCurrentHotel().getWebSafeKey());
		LoadCallback<RoomTypeDtor> roomTypeLoadCallback = new LoadCallback<RoomTypeDtor>() {

			@Override
			public void onSuccess(LoadResult<RoomTypeDtor> loadResult) {
				logger.info("AbstractFilterPresenter().onReveal().onSuccess()");
				getView().setRoomTypeData(loadResult.getData());
				FilterChangeEvent.fire(RoomStatusFilterPresenter2.this, DataTable.ROOM_TYPE);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};

		roomTypeDataSource.load(new LoadConfig<RoomTypeDtor>(0, 0, null, null), roomTypeLoadCallback);
	}

	public RoomTypeDtor getSelectedRoomType() {
		return getView().getSelectedRoomType();
	}

	@Override
	public void filterChange() {
		// TODO Auto-generated method stub
		
	}
}
