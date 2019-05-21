/**
 * 
 */
package io.crs.hsys.client.kip.browser.oooroom;

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

import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.filter.KipFilterPresenterFactory;
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
	}

	@ProxyStandard
	@NameToken(KipNameTokens.OOO_ROOMS)
	interface MyProxy extends ProxyPlace<OooRoomBrowserPresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> FILTER_SLOT = new SingleSlot<>();
	public static final Slot<PresenterWidget<?>> SLOT_TASKS = new Slot<>();

	private final PlaceManager placeManager;
	private final ResourceDelegate<OooRoomResource> resourceDelegate;
	private final CurrentUser currentUser;
	private final KipMessages i18n;

	@Inject
	OooRoomBrowserPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager,
			ResourceDelegate<OooRoomResource> resourceDelegate, KipFilterPresenterFactory filterFactory,
			CurrentUser currentUser, KipMessages i18n) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		logger.info("OooRoomPresenter()");
		this.placeManager = placeManager;
		this.currentUser = currentUser;
		this.resourceDelegate = resourceDelegate;
		this.i18n = i18n;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(i18n.tasksTitle(), i18n.tasksSubTitle(), MenuItemType.MENU_ITEM, this);

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editItem(OooRoomDto item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteItems(List<OooRoomDto> items) {
		// TODO Auto-generated method stub
		
	}
}
