/**
 * 
 */
package io.crs.hsys.client.core.editor.room;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.datasource.HotelDataSource;
import io.crs.hsys.client.core.datasource.RoomTypeDataSource2;
import io.crs.hsys.client.core.editor.AbstractEditorPresenter;
import io.crs.hsys.client.core.editor.AbstractEditorView;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.RoomResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.hotel.HotelDto;
import io.crs.hsys.shared.dto.hotel.RoomAvailabilityDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;

/**
 * @author robi
 *
 */
public class RoomEditorPresenter
		extends AbstractEditorPresenter<RoomDto, RoomEditorPresenter.MyView, RoomEditorPresenter.MyProxy>
		implements RoomEditorUiHandlers {
	private static Logger logger = Logger.getLogger(RoomEditorPresenter.class.getName());

	public interface MyView extends AbstractEditorView<RoomDto>, HasUiHandlers<RoomEditorUiHandlers> {
		void setRoomTypeData(List<RoomTypeDtor> roomTypeData);

		void displayError(EntityPropertyCode code, String message);
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.ROOM_EDITOR)
	interface MyProxy extends ProxyPlace<RoomEditorPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<RoomResource> resourceDelegate;
	private final RoomTypeDataSource2 roomTypeDataSource;
	private final HotelDataSource hotelDataSource;
	private final CurrentUser currentUser;
	private final CoreMessages i18nCore;

	@Inject
	RoomEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<RoomResource> resourceDelegate, RoomTypeDataSource2 roomTypeDataSource,
			HotelDataSource hotelDataSource, CurrentUser currentUser, CoreMessages i18nCore) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("RoomTypeEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.roomTypeDataSource = roomTypeDataSource;
		this.hotelDataSource = hotelDataSource;
		this.currentUser = currentUser;
		this.i18nCore = i18nCore;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		roomTypeDataSource.setHotelKey(filters.get(HOTEL_KEY));
		hotelDataSource.setWebSafeKey(filters.get(HOTEL_KEY));
		
		LoadCallback<RoomTypeDtor> roomTypeLoadCallback = new LoadCallback<RoomTypeDtor>() {
			@Override
			public void onSuccess(LoadResult<RoomTypeDtor> loadResult) {
				getView().setRoomTypeData(loadResult.getData());
				if (isNew()) {
					LoadCallback<HotelDto> hotelLoadCallback = new LoadCallback<HotelDto>() {
						@Override
						public void onSuccess(LoadResult<HotelDto> loadResult) {
							SetPageTitleEvent.fire(i18nCore.roomEditorCreateTitle(),
									loadResult.getData().get(0).getName(), MenuItemType.MENU_ITEM,
									RoomEditorPresenter.this);
							create();
						}

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
						}
					};
					hotelDataSource.get(new LoadConfig<HotelDto>(0, 0, null, null), hotelLoadCallback);
				} else {
					edit(filters.get(WEBSAFEKEY));
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		roomTypeDataSource.load(new LoadConfig<RoomTypeDtor>(0, 0, null, null), roomTypeLoadCallback);
	}

	@Override
	protected RoomDto createDto() {
		RoomDto dto = new RoomDto();
		dto.setHotel(currentUser.getAppUserDto().getDefaultHotel());
		RoomAvailabilityDto ra = new RoomAvailabilityDto(true, new Date());
		dto.addRoomAvailability(ra);
		return dto;
	}

	private void edit(String webSafeKey) {
		resourceDelegate.withCallback(new AsyncCallback<RoomDto>() {
			@Override
			public void onSuccess(RoomDto dto) {
				List<RoomAvailabilityDto> availabilities = dto.getRoomAvailabilities();
				if (availabilities != null)
					availabilities.sort(
							(RoomAvailabilityDto o1, RoomAvailabilityDto o2) -> o1.getDate().compareTo(o2.getDate()));

				SetPageTitleEvent.fire(i18nCore.roomEditorModifyTitle(), dto.getHotel().getName(),
						MenuItemType.MENU_ITEM, RoomEditorPresenter.this);

				getView().edit(dto);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).get(webSafeKey);
	}

	@Override
	public void save(RoomDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<RoomDto>() {
			@Override
			public void onSuccess(RoomDto dto) {
				placeManager.navigateBack();
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}
}