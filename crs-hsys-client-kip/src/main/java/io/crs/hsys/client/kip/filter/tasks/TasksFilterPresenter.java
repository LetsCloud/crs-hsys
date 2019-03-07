/**
 * 
 */
package io.crs.hsys.client.kip.filter.tasks;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import io.crs.hsys.client.core.datasource.RoomTypeDataSource2;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.ui.filter.AbstractFilterPresenter;
import io.crs.hsys.client.core.ui.filter.AbstractFilterUiHandlers;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent.DataTable;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class TasksFilterPresenter extends AbstractFilterPresenter<TasksFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(TasksFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
		void setTaskKindData(List<TaskKind> taskKindData);

		TaskKind getSelectedTaskKind();

		void setRoomTypeData(List<RoomTypeDtor> hotelData);

		RoomTypeDtor getSelectedRoomType();
	}

	protected final RoomTypeDataSource2 roomTypeDataSource;

	@Inject
	TasksFilterPresenter(EventBus eventBus, TasksFilterPresenter.MyView view, CurrentUser currentUser,
			RoomTypeDataSource2 roomTypeDataSource) {
		super(eventBus, view, currentUser);
		logger.info("RoomStatusFilterPresenter2()");
		this.roomTypeDataSource = roomTypeDataSource;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().setTaskKindData(Arrays.asList(TaskKind.values()));
	}

	@Override
	public void onReveal() {
		super.onReveal();
		logger.info(
				"RoomStatusFilterPresenter2().onReveal()->webSafeKey=" + currentUser.getCurrentHotel().getWebSafeKey());
		roomTypeDataSource.setHotelKey(currentUser.getCurrentHotel().getWebSafeKey());
		LoadCallback<RoomTypeDtor> roomTypeLoadCallback = new LoadCallback<RoomTypeDtor>() {

			@Override
			public void onSuccess(LoadResult<RoomTypeDtor> loadResult) {
				logger.info("AbstractFilterPresenter().onReveal().onSuccess()");
				getView().setRoomTypeData(loadResult.getData());
				FilterChangeEvent.fire(TasksFilterPresenter.this, DataTable.ROOM_TYPE);
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
}
