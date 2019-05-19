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

import io.crs.hsys.client.core.datasource.AppUserDataSource2;
import io.crs.hsys.client.core.datasource.RoomTypeDataSource2;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.ui.filter.AbstractFilterPresenter;
import io.crs.hsys.client.core.ui.filter.AbstractFilterUiHandlers;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.cnst.TaskStatus;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class TasksFilterPresenter extends AbstractFilterPresenter<TasksFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(TasksFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {

		void setCurrentUserKey(String webSafeKey);

		List<TaskStatus> getSelectedTaskStatuses();

		void setTaskKindData(List<TaskKind> taskKindData);

		TaskKind getSelectedTaskKind();

		void setAppUserData(List<AppUserDtor> hotelData);

		void setRoomTypeData(List<RoomTypeDtor> hotelData);

		String getSelectedReporterKey();

		String getSelectedAssigneeKey();
		
		RoomTypeDtor getSelectedRoomType();
	}

	protected final AppUserDataSource2 appUserDataSource;
	protected final RoomTypeDataSource2 roomTypeDataSource;

	@Inject
	TasksFilterPresenter(EventBus eventBus, TasksFilterPresenter.MyView view, CurrentUser currentUser,
			AppUserDataSource2 appUserDataSource, RoomTypeDataSource2 roomTypeDataSource) {
		super(eventBus, view, currentUser);
		logger.info("TasksFilterPresenter()");
		this.appUserDataSource = appUserDataSource;
		this.roomTypeDataSource = roomTypeDataSource;
		getView().setUiHandlers(this);
	}

	@Override
	public void onBind() {
		super.onBind();
		getView().setTaskKindData(Arrays.asList(TaskKind.values()));
		getView().setCurrentUserKey(currentUser.getAppUserDto().getWebSafeKey());
	}

	@Override
	public void onReveal() {
		super.onReveal();
		loadAppUserData();
		loadRoomTypeData();
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

	public String getSelectedReporterKey() {
		return getView().getSelectedReporterKey();
	}

	public String getSelectedAssigneeKey() {
		return getView().getSelectedAssigneeKey();
	}

	public List<TaskStatus> getSelectedTaskStatuses() {
		return getView().getSelectedTaskStatuses();
	}

	public RoomTypeDtor getSelectedRoomType() {
		return getView().getSelectedRoomType();
	}
}
