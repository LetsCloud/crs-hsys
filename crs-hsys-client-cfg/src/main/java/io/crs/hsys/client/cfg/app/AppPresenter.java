/**
 * 
 */
package io.crs.hsys.client.cfg.app;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;

import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;

import gwt.material.design.client.constants.IconType;

import io.crs.hsys.client.cfg.i18n.FroMessages;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.app.AppServiceWorkerManager;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.menu.MenuPresenter;
import io.crs.hsys.client.core.security.AppData;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.AuthResource;
import io.crs.hsys.shared.constans.MenuItemType;
import io.crs.hsys.shared.constans.SubSystem;
import io.crs.hsys.shared.dto.menu.MenuItemDto;

/**
 * @author CR
 *
 */
public class AppPresenter extends AbstractAppPresenter<AppPresenter.MyProxy> {

	private final FroMessages i18n;

	private final CoreMessages i18nCore;

	@ProxyStandard
	interface MyProxy extends Proxy<AppPresenter> {
	}

	@Inject
	AppPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, FroMessages i18n,
			RestDispatch dispatch, AuthResource authenticationService, CurrentUser currentUser,
			MenuPresenter menuPresenter, AppData appData, AppServiceWorkerManager messagingManager,CoreMessages i18nCore) {
		super(eventBus, view, proxy, placeManager, dispatch, authenticationService, menuPresenter, currentUser,
				SubSystem.CFG, messagingManager);

		this.i18n = i18n;
		this.i18nCore = i18nCore;
	}

	@Override
	protected void onBind() {
		super.onBind();
		getMenuPresenter().setMenuItems(createMenuitems());
	}

	private List<MenuItemDto> createMenuitems() {
		List<MenuItemDto> menuItems = new ArrayList<MenuItemDto>();

		MenuItemDto atendantsItem = new MenuItemDto();
		atendantsItem.setIndex(1);
		atendantsItem.setType(MenuItemType.MENU_ITEM);
		atendantsItem.setIcon(IconType.HOTEL.name());
		atendantsItem.setText(i18n.mainMenuItemDashboard());
		atendantsItem.setNameToken(CoreNameTokens.HOME);
		menuItems.add(atendantsItem);

		MenuItemDto reservationItem = new MenuItemDto();
		reservationItem.setIndex(2);
		reservationItem.setType(MenuItemType.MENU_ITEM);
		reservationItem.setIcon(IconType.HOTEL.name());
		reservationItem.setText("Reservation");
		reservationItem.setNameToken(CoreNameTokens.RESERVATION);
		menuItems.add(reservationItem);

		MenuItemDto customerCreateItem = new MenuItemDto();
		customerCreateItem.setIndex(2);
		customerCreateItem.setType(MenuItemType.MENU_ITEM);
		customerCreateItem.setIcon(IconType.HOTEL.name());
		customerCreateItem.setText("Create Customer");
		customerCreateItem.setNameToken(CoreNameTokens.ORGANIZATION_CREATOR);
		menuItems.add(customerCreateItem);

		// Reservation

		MenuItemDto resSubMenu = new MenuItemDto();
		resSubMenu.setIndex(2);
		resSubMenu.setType(MenuItemType.SUB_MENU);
		resSubMenu.setIcon(IconType.ROOM_SERVICE.name());
		resSubMenu.setText(i18n.mainMenuGroupReception());
		resSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(resSubMenu);

		MenuItemDto resMenuItem1 = new MenuItemDto();
		resMenuItem1.setIndex(1);
		resMenuItem1.setType(MenuItemType.MENU_ITEM);
		resMenuItem1.setText(i18n.mainMenuItemReservation());
		resMenuItem1.setNameToken(CoreNameTokens.COMMON_CONFIG);
		resSubMenu.addItem(resMenuItem1);

		MenuItemDto resMenuItem2 = new MenuItemDto();
		resMenuItem2.setIndex(2);
		resMenuItem2.setType(MenuItemType.MENU_ITEM);
		resMenuItem2.setText(i18n.mainMenuItemRoomplan());
		resMenuItem2.setNameToken(CoreNameTokens.COMMON_CONFIG);
		resSubMenu.addItem(resMenuItem2);

		// Cashier

		MenuItemDto cashSubMenu = new MenuItemDto();
		cashSubMenu.setIndex(3);
		cashSubMenu.setType(MenuItemType.SUB_MENU);
		cashSubMenu.setIcon(IconType.EURO_SYMBOL.name());
		cashSubMenu.setText(i18n.mainMenuGroupCashier());
		cashSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(cashSubMenu);

		MenuItemDto cashMenuItem1 = new MenuItemDto();
		cashMenuItem1.setIndex(1);
		cashMenuItem1.setType(MenuItemType.MENU_ITEM);
		cashMenuItem1.setText(i18n.mainMenuItemBilling());
		cashMenuItem1.setNameToken(CoreNameTokens.COMMON_CONFIG);
		cashSubMenu.addItem(cashMenuItem1);

		// Configuration menu

		MenuItemDto configSubMenu = new MenuItemDto();
		configSubMenu.setIndex(4);
		configSubMenu.setType(MenuItemType.SUB_MENU);
		configSubMenu.setIcon(IconType.SETTINGS.name());
		configSubMenu.setText(i18n.mainMenuGroupConfiguration());
		configSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(configSubMenu);

		MenuItemDto configMenuItem1 = new MenuItemDto();
		configMenuItem1.setIndex(1);
		configMenuItem1.setType(MenuItemType.MENU_ITEM);
		configMenuItem1.setText(i18nCore.systemConfigTitle());
		configMenuItem1.setNameToken(CoreNameTokens.SYSTEM_CONFIG);
		configSubMenu.addItem(configMenuItem1);

		MenuItemDto configMenuItem2 = new MenuItemDto();
		configMenuItem2.setIndex(2);
		configMenuItem2.setType(MenuItemType.MENU_ITEM);
		configMenuItem2.setText(i18nCore.profileConfigTitle());
		configMenuItem2.setNameToken(CoreNameTokens.PROFILE_CONFIG);
		configSubMenu.addItem(configMenuItem2);

		MenuItemDto configMenuItem3 = new MenuItemDto();
		configMenuItem3.setIndex(3);
		configMenuItem3.setType(MenuItemType.MENU_ITEM);
		configMenuItem3.setText(i18nCore.hotelConfigTitle());
		configMenuItem3.setNameToken(CoreNameTokens.HOTEL_CONFIG);
		configSubMenu.addItem(configMenuItem3);

		return menuItems;
	}
}
