/**
 * 
 */
package io.crs.hsys.client.kip.creator.oooroom;

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
import io.crs.hsys.client.core.datasource.RoomDataSource;
import io.crs.hsys.client.core.datasource.RoomTypeDataSource2;
import io.crs.hsys.client.core.editor.AbstractEditorPresenter;
import io.crs.hsys.client.core.editor.AbstractEditorView;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.event.DisplayMessageEvent;
import io.crs.hsys.client.core.event.DisplayMessageEvent.DisplayMessageHandler;
import io.crs.hsys.client.core.gin.CustomActionException;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.message.MessageData;
import io.crs.hsys.client.core.message.MessageStyle;
import io.crs.hsys.client.core.message.MessageUtils;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.shared.api.OooRoomResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.cnst.OooReturnWhen;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.hotel.OooCreateDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author CR
 *
 */
public class OooRoomCreatorPresenter
		extends AbstractEditorPresenter<OooCreateDto, OooRoomCreatorPresenter.MyView, OooRoomCreatorPresenter.MyProxy>
		implements OooRoomCreatorUiHandlers, DisplayMessageHandler {
	private static Logger logger = Logger.getLogger(OooRoomCreatorPresenter.class.getName());

	public interface MyView extends AbstractEditorView<OooCreateDto>, HasUiHandlers<OooRoomCreatorUiHandlers> {
		void setRoomData(List<RoomDto> data);

		void setRoomTypeData(List<RoomTypeDtor> data);

		void setRoomStatusFilterData(RoomStatus[] data);

		void setOooReturnTimeData(OooReturnWhen[] data);

		void setRoomStatusData(RoomStatus[] data);

		void displayError(EntityPropertyCode code, String message);

		void showMessage(MessageData message);
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.OOO_ROOM_CREATOR)
	interface MyProxy extends ProxyPlace<OooRoomCreatorPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<OooRoomResource> resourceDelegate;
	private final RoomDataSource roomDataSource;
	private final RoomTypeDataSource2 roomTypeDataSource;
	private final CurrentUser currentUser;
	private final KipMessages i18n;
	private final CoreMessages i18nCore;

	@Inject
	OooRoomCreatorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<OooRoomResource> resourceDelegate, RoomDataSource roomDataSource,
			RoomTypeDataSource2 roomTypeDataSource, CurrentUser currentUser, KipMessages i18n, CoreMessages i18nCore) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("OooRoomCreatorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.roomTypeDataSource = roomTypeDataSource;
		this.roomDataSource = roomDataSource;
		this.currentUser = currentUser;
		this.i18n = i18n;
		this.i18nCore = i18nCore;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(DisplayMessageEvent.TYPE, this);
	}

	@Override
	protected void loadData() {
		loadRoomStatusData();
		loadRoomStatusFilterData();
		loadOooReturnTimeData();
		loadRoomData();
		loadRoomTypeData();
	}

	private void loadRoomData() {
		LoadCallback<RoomDto> roomLoadCallback = new LoadCallback<RoomDto>() {
			@Override
			public void onSuccess(LoadResult<RoomDto> loadResult) {
				getView().setRoomData(loadResult.getData());
				if (roomTypeDataSource.getIsLoaded())
					start();
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		roomDataSource.setOnlyActive(true);
		roomDataSource.setHotelKey(currentUser.getCurrentHotel().getWebSafeKey());
		roomDataSource.load(new LoadConfig<RoomDto>(0, 0, null, null), roomLoadCallback);
	}

	private void loadRoomTypeData() {
		roomTypeDataSource.setHotelKey(currentUser.getCurrentHotel().getWebSafeKey());
		LoadCallback<RoomTypeDtor> roomTypeLoadCallback = new LoadCallback<RoomTypeDtor>() {

			@Override
			public void onSuccess(LoadResult<RoomTypeDtor> loadResult) {
				getView().setRoomTypeData(loadResult.getData());
				if (roomDataSource.getIsLoaded())
					start();
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};

		roomTypeDataSource.load(new LoadConfig<RoomTypeDtor>(0, 0, null, null), roomTypeLoadCallback);
	}

	private void loadRoomStatusFilterData() {
		getView().setRoomStatusFilterData(RoomStatus.values());
	}

	private void loadRoomStatusData() {
		getView().setRoomStatusData(RoomStatus.cleaning);
	}

	private void loadOooReturnTimeData() {
		getView().setOooReturnTimeData(OooReturnWhen.values());
	}

	private void start() {
		SetPageTitleEvent.fire(i18n.oooRoomEditorCreateTitle(), i18n.oooRoomEditorCreateSubTitle(),
				MenuItemType.MENU_ITEM, OooRoomCreatorPresenter.this);
		create();
	}

	@Override
	protected OooCreateDto createDto() {
		OooCreateDto dto = new OooCreateDto();
		dto.setHotel(currentUser.getCurrentHotel());
		dto.setCreatedBy(new AppUserDtor(currentUser.getAppUserDto()));
		return dto;
	}

	@Override
	public void save(OooCreateDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<OooCreateDto>() {
			@Override
			public void onSuccess(OooCreateDto dto) {
				placeManager.navigateBack();
			}

			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof CustomActionException) {
					CustomActionException exception = (CustomActionException) caught;
					MessageData message = new MessageData(MessageStyle.ERROR,
							MessageUtils.tranlateTitle(i18nCore, exception.getErrorResponse().getTitleCode()),
							MessageUtils.tranlateMessage(i18nCore, exception.getErrorResponse().getMessageCode(),
									exception.getErrorResponse().getValuesMap(), currentUser.getLocale()));
					getView().showMessage(message);
				}
			}

		}).create(dto);
	}

	@Override
	public void onDisplayMessage(DisplayMessageEvent event) {
	}
}