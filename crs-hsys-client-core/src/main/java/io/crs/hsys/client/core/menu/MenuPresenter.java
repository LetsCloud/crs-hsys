/**
 * 
 */
package io.crs.hsys.client.core.menu;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.common.base.Strings;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.core.event.ContentPushEvent;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.event.ContentPushEvent.MenuState;
import io.crs.hsys.client.core.event.SetPageTitleEvent.SetPageTitleHandler;
import io.crs.hsys.client.core.security.AppData;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.security.HasPermissionsGatekeeper;
import io.crs.hsys.shared.api.AuthResource;
import io.crs.hsys.shared.constans.MenuItemType;
import io.crs.hsys.shared.dto.hotel.HotelDtor;
import io.crs.hsys.shared.dto.menu.MenuItemDto;

/**
 * @author CR
 *
 */
public class MenuPresenter extends PresenterWidget<MenuPresenter.MyView>
		implements MenuUiHandlers, SetPageTitleHandler {
	private static Logger logger = Logger.getLogger(MenuPresenter.class.getName());

	public static final SingleSlot<PresenterWidget<?>> SLOT_NAVBAR = new SingleSlot<>();

	private final HasPermissionsGatekeeper menItemGatekeeper;

	interface MyView extends View, HasUiHandlers<MenuUiHandlers> {
		void checkPermittedWidgets();

		void setPageTitle(String title, String description);

		void setProfileBackground(ImageResource resource);

		void setAccountName(String accountName);

		void setUserName(String userName);

		void setUserImageUrl(String url);

		void setHotelName(String hotelName);

		void setPermittedHotels(List<HotelDtor> hotels);

		void setBusinessDate(Date businessDate);

		void setMenuItems(List<MenuItemDto> menuItems);

		void inactivateSingleLinks();

		void closeCollapisbles();

		void setAppCode(String appCode);
	}

	private final RestDispatch dispatcher;
	private final AuthResource authService;
	private final CurrentUser currentUser;
	private final AppData appData;

	@Inject
	MenuPresenter(EventBus eventBus, MyView view, PlaceManager placeManager, RestDispatch dispatcher,
			AuthResource authService, CurrentUser currentUser, AppData appData,
			HasPermissionsGatekeeper menItemGatekeeper) {
		super(eventBus, view);
		logger.info("MenuPresenter()");

		this.dispatcher = dispatcher;
		this.authService = authService;
		this.currentUser = currentUser;
		this.appData = appData;
		this.menItemGatekeeper = menItemGatekeeper;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(SetPageTitleEvent.TYPE, this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();

		if (appData.getAppCode() != null) {
			getView().setAppCode(appData.getAppCode());
		}
		/*
		 * getView().checkPermittedWidgets();
		 * getView().setAccountName(currentUser.getAppUserDto().getAccountDto().getName(
		 * )); getView().setHotelName(currentUser.getCurrentHotelDto().getName());
		 * getView().setUserName(currentUser.getAppUserDto().getUsername());
		 * getView().setBusinessDate(currentUser.getCurrentHotelDto().getBusinessDate())
		 * ;
		 */
	}

	@Override
	public void onSetPageTitle(SetPageTitleEvent event) {
		getView().setPageTitle(event.getTitle(), event.getDescription());
	}

	@Override
	public Boolean canReveal(String permission) {
		String[] permissions = { permission };
		menItemGatekeeper.withParams(permissions);
		return menItemGatekeeper.canReveal();
	}

	@Override
	public void setContentPush(MenuState menuState) {
		ContentPushEvent.fire(this, menuState);
	}

	public void setNavBarWidget(PresenterWidget<?> widget) {
		setInSlot(SLOT_NAVBAR, widget);

	}

	public void setMenuItems(List<MenuItemDto> menuItems) {
		getView().setMenuItems(menuItems);
	}

	public void adjustMenuItems(MenuItemType triggerItem) {

		if (triggerItem.equals(MenuItemType.MENU_ITEM)) {
			getView().inactivateSingleLinks();
		} else {
			getView().closeCollapisbles();
		}

	}

	@Override
	public void logout() {
		dispatcher.execute(authService.logout(), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
//				currentUser.setLoggedIn(false);
//				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.LOGIN).build();
//				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

	@Override
	public void referesh() {
		getView().setAccountName(currentUser.getAppUserDto().getAccount().getName());
		if (currentUser.getCurrentHotel() != null) {
			if (!Strings.isNullOrEmpty(currentUser.getCurrentHotel().getName()))
				getView().setHotelName(currentUser.getCurrentHotel().getName());
		}
		getView().setPermittedHotels(currentUser.getAppUserDto().getAvailableHotels());
		getView().setUserImageUrl(currentUser.getAppUserDto().getPicture());
		getView().setUserName(currentUser.getAppUserDto().getName());
	}

	@Override
	public void setCurrentHotel(HotelDtor hotel) {
		currentUser.setCurrentHotel(hotel);
		getView().setHotelName(hotel.getName());
	}

	public void setProfileBackground(ImageResource resource) {
		getView().setProfileBackground(resource);
	}
}