/**
 * 
 */
package io.crs.hsys.client.kip.browser.oooroom;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.filter.KipFilterPresenterFactory;
import io.crs.hsys.client.kip.filter.oooroom.OooRoomFilterPresenter;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.shared.api.OooRoomResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.dto.hotel.OooRoomDto;

/**
 * @author CR
 *
 */
public class OooRoomBrowserPresenter extends Presenter<OooRoomBrowserPresenter.MyView, OooRoomBrowserPresenter.MyProxy>
		implements OooRoomBrowserUiHandlers {
	private static Logger logger = Logger.getLogger(OooRoomBrowserPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<OooRoomBrowserUiHandlers> {
		void setData(List<OooRoomDto> data);

		void reConfigColumns();
	}

	@ProxyStandard
	@NameToken(KipNameTokens.OOO_ROOMS)
	interface MyProxy extends ProxyPlace<OooRoomBrowserPresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> FILTER_SLOT = new SingleSlot<>();
	public static final Slot<PresenterWidget<?>> SLOT_TASKS = new Slot<>();

	private final PlaceManager placeManager;
	private final ResourceDelegate<OooRoomResource> resourceDelegate;
	private final OooRoomFilterPresenter filter;
	private final CurrentUser currentUser;
	private final KipMessages i18n;

	@Inject
	OooRoomBrowserPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager,
			ResourceDelegate<OooRoomResource> resourceDelegate, KipFilterPresenterFactory filterFactory,
			CurrentUser currentUser, KipMessages i18n) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		logger.info("OooRoomPresenter()");
		this.placeManager = placeManager;
		this.filter = filterFactory.createOooRoomFilter();
		this.currentUser = currentUser;
		this.resourceDelegate = resourceDelegate;
		this.i18n = i18n;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(FILTER_SLOT, filter);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(i18n.oooRoomBrowserTitle(), i18n.oooRoomBrowserSubTitle(), MenuItemType.MENU_ITEM, this);

		getView().reConfigColumns();
		
		loadTasks();
	}

	private void loadTasks() {
		resourceDelegate.withCallback(new AsyncCallback<List<OooRoomDto>>() {
			@Override
			public void onSuccess(List<OooRoomDto> result) {
				getView().setData(result);
			}

			@Override
			public void onFailure(Throwable caught) {
//		getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).getByHotel(currentUser.getCurrentHotel().getWebSafeKey());
	}

	@Override
	public void createItem() {
		Builder placeBuilder = new Builder().nameToken(CoreNameTokens.OOO_ROOM_CREATOR);
//		Builder placeBuilder = new Builder().nameToken(CoreNameTokens.OOO_ROOM_EDITOR);
		placeManager.revealPlace(placeBuilder.build());
	}

	@Override
	public void editItem(OooRoomDto item) {
		Builder placeBuilder = new Builder().nameToken(CoreNameTokens.OOO_ROOM_EDITOR);
		placeBuilder.with(WEBSAFEKEY, String.valueOf(item.getWebSafeKey()));
		placeManager.revealPlace(placeBuilder.build());
	}

	@Override
	public void deleteItems(List<OooRoomDto> items) {
		// TODO Auto-generated method stub

	}
}
