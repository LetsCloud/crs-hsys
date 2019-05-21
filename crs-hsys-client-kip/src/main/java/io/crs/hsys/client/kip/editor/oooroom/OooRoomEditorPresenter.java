/**
 * 
 */
package io.crs.hsys.client.kip.editor.oooroom;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

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
import io.crs.hsys.client.core.editor.AbstractEditorPresenter;
import io.crs.hsys.client.core.editor.AbstractEditorView;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.shared.api.OooRoomResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.cnst.OooReturnTime;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.hotel.OooRoomDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
public class OooRoomEditorPresenter
		extends AbstractEditorPresenter<OooRoomDto, OooRoomEditorPresenter.MyView, OooRoomEditorPresenter.MyProxy>
		implements OooRoomEditorUiHandlers {
	private static Logger logger = Logger.getLogger(OooRoomEditorPresenter.class.getName());

	public interface MyView extends AbstractEditorView<OooRoomDto>, HasUiHandlers<OooRoomEditorUiHandlers> {
		void setRoomData(List<RoomDto> data);

		void setOooReturnTimeData(OooReturnTime[] data);

		void setRoomStatusData(RoomStatus[] data);

		void displayError(EntityPropertyCode code, String message);
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.OOO_ROOM_EDITOR)
	interface MyProxy extends ProxyPlace<OooRoomEditorPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<OooRoomResource> resourceDelegate;
	private final RoomDataSource roomDataSource;
	private final CurrentUser currentUser;
	private final CoreMessages i18nCore;
	private final KipMessages i18n;

	@Inject
	OooRoomEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<OooRoomResource> resourceDelegate, RoomDataSource roomDataSource, CurrentUser currentUser,
			CoreMessages i18nCore, KipMessages i18n) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("OooRoomEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.roomDataSource = roomDataSource;
		this.currentUser = currentUser;
		this.i18nCore = i18nCore;
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void loadData() {
		loadRoomStatusData();
		loadOooReturnTimeData();
		loadRoomData();
	}

	private void loadRoomData() {
		LoadCallback<RoomDto> roomLoadCallback = new LoadCallback<RoomDto>() {
			@Override
			public void onSuccess(LoadResult<RoomDto> loadResult) {
				getView().setRoomData(loadResult.getData());
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

	private void loadRoomStatusData() {
		getView().setRoomStatusData(RoomStatus.cleaning);
	}

	private void loadOooReturnTimeData() {
		getView().setOooReturnTimeData(OooReturnTime.values());
	}

	private void start() {
		if (isNew()) {
			SetPageTitleEvent.fire(i18n.taskEditorCreateTitle(), i18n.taskEditorCreateSubTitle(),
					MenuItemType.MENU_ITEM, OooRoomEditorPresenter.this);
			create();
		} else {
			SetPageTitleEvent.fire(i18n.taskEditorModifyTitle(), i18n.taskEditorModifySubTitle(),
					MenuItemType.MENU_ITEM, OooRoomEditorPresenter.this);
			edit(filters.get(WEBSAFEKEY));
		}
	}

	@Override
	protected OooRoomDto createDto() {
		OooRoomDto dto = new OooRoomDto();
		dto.setHotel(currentUser.getCurrentHotel());
		return dto;
	}

	private void edit(String webSafeKey) {
		resourceDelegate.withCallback(new AsyncCallback<OooRoomDto>() {
			@Override
			public void onSuccess(OooRoomDto dto) {
				SetPageTitleEvent.fire(i18nCore.taskTypeEditorModifyTitle(), "", MenuItemType.MENU_ITEM,
						OooRoomEditorPresenter.this);
				getView().edit(dto);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).get(webSafeKey);
	}

	@Override
	public void save(OooRoomDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<OooRoomDto>() {
			@Override
			public void onSuccess(OooRoomDto dto) {
				placeManager.navigateBack();
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}
}