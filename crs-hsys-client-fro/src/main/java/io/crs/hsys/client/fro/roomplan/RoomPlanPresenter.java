/**
 * 
 */
package io.crs.hsys.client.fro.roomplan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.datasource.RoomDataSource;
import io.crs.hsys.client.core.datasource.RoomTypeDataSource2;
import io.crs.hsys.client.core.event.ContentPushEvent;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.fro.NameTokens;
import io.crs.hsys.client.fro.roomplan.model.HeaderData;
import io.crs.hsys.client.fro.roomplan.model.RoomCellData;
import io.crs.hsys.client.fro.roomplan.model.RoomTypeRowData;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
public class RoomPlanPresenter extends Presenter<RoomPlanPresenter.MyView, RoomPlanPresenter.MyProxy>
		implements RoomPlanUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(RoomPlanPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<RoomPlanUiHandlers> {
		void showPage();

		void setData(List<HeaderData> data);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.ROOM_PLAN)
// @UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<RoomPlanPresenter> {
	}

	private final RoomDataSource roomDataSource;
	private final RoomTypeDataSource2 roomTypeDataSource;
	private final CurrentUser currentUser;

	@Inject
	RoomPlanPresenter(EventBus eventBus, MyView view, MyProxy proxy, RoomTypeDataSource2 roomTypeDataSource,
			RoomDataSource roomDataSource, CurrentUser currentUser) {
		super(eventBus, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "RoomPlanPresenter()");
		this.roomDataSource = roomDataSource;
		this.roomTypeDataSource = roomTypeDataSource;
		this.currentUser = currentUser;

		addRegisteredHandler(ContentPushEvent.TYPE, this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire("Roomplan", "", MenuItemType.MENU_ITEM, this);
		loadRoomData();
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
	}

	private void loadData(List<RoomDto> rooms) {
		List<HeaderData> data = new ArrayList<HeaderData>();
		int clientWidth = Window.getClientWidth();
		int numOfDays = (clientWidth - 100) / 100;
		for (int i = 1; i < numOfDays; i++) {
			data.add(new HeaderData(i, new Date(), 0f));
		}
		getView().setData(data);

		rooms.sort((o1, o2) -> o1.getRoomType().compareTo(o2.getRoomType()));
		List<RoomTypeRowData> rows = new ArrayList<RoomTypeRowData>();
		List<RoomCellData> roomCells = new ArrayList<RoomCellData>();
		String tempCode = null;
		int numberOfRooms = 0;
		for (RoomDto room : rooms) {
			if (tempCode == null)
				tempCode = room.getRoomType().getCode();
			if (!tempCode.equals(room.getRoomType().getCode())) {
				rows.add(new RoomTypeRowData(room.getRoomType().getCode(), room.getRoomType().getName(), numberOfRooms,
						roomCells));
				tempCode = room.getRoomType().getCode();
				numberOfRooms = 0;
				roomCells.clear();
			}
			numberOfRooms++;
			roomCells.add(new RoomCellData(room.getCode(), room.getDescription()));
		}
	}

	private void loadRoomData() {
		roomDataSource.setHotelKey(currentUser.getCurrentHotel().getWebSafeKey());
		LoadCallback<RoomDto> roomLoadCallback = new LoadCallback<RoomDto>() {

			@Override
			public void onSuccess(LoadResult<RoomDto> loadResult) {
				loadData(loadResult.getData());
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		};

		roomDataSource.load(new LoadConfig<RoomDto>(0, 0, null, null), roomLoadCallback);
	}

}
