/**
 * 
 */
package io.crs.hsys.client.core.menu;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.common.base.Strings;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

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
import io.crs.hsys.client.core.util.UrlUtils;
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

	public static final String LOGOUT_URL = "/logout";
	public static final String LOGOUT_SUCCESS_URL = "/login.html?logout";

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

	private final CurrentUser currentUser;
	private final AppData appData;

	@Inject
	MenuPresenter(EventBus eventBus, MyView view, PlaceManager placeManager, CurrentUser currentUser, AppData appData,
			HasPermissionsGatekeeper menItemGatekeeper) {
		super(eventBus, view);
		logger.info("MenuPresenter()");

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
		final String baseUrl = UrlUtils.getBaseUrl();
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, baseUrl + LOGOUT_URL);

		try {
			builder.sendRequest("", new RequestCallback() {
				public void onResponseReceived(Request request, Response response) {
					Window.Location.replace(baseUrl + LOGOUT_SUCCESS_URL);
				}

				public void onError(Request request, Throwable exception) {
				}
			});
		} catch (RequestException e) {
		}
	}

	@Override
	public void referesh() {
		if (currentUser.getAppUserDto().getAccount() != null) {
			getView().setAccountName(currentUser.getAppUserDto().getAccount().getName());
		}

		if (currentUser.getCurrentHotel() != null) {
			if (!Strings.isNullOrEmpty(currentUser.getCurrentHotel().getName()))
				getView().setHotelName(currentUser.getCurrentHotel().getName());
		}
		getView().setPermittedHotels(currentUser.getAppUserDto().getAvailableHotels());
		if (currentUser.getAppUserDto().getPicture() != null) {
			getView().setUserImageUrl(currentUser.getAppUserDto().getPicture());
		}
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