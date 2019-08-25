/**
 * 
 */
package io.crs.hsys.client.fro;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;

import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;

import gwt.material.design.client.constants.IconType;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.app.AppServiceWorkerManager;
import io.crs.hsys.client.core.firebase.messaging.MessagingManager;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.menu.MenuPresenter;
import io.crs.hsys.client.core.security.AppData;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.fro.i18n.FroMessages;
import io.crs.hsys.client.fro.resources.FroResources;
import io.crs.hsys.shared.api.AuthResource;
import io.crs.hsys.shared.api.GlobalConfigResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.cnst.SubSystem;
import io.crs.hsys.shared.dto.menu.MenuItemDto;

/**
 * @author CR
 *
 */
public class FroAppPresenter extends AbstractAppPresenter<FroAppPresenter.MyProxy> {

	private final CoreMessages i18nCore;
	private final FroMessages i18n;
	private final FroResources resources;

	@ProxyStandard
	interface MyProxy extends Proxy<FroAppPresenter> {
	}

	@Inject
	FroAppPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, FroMessages i18n,
			FroResources resources, RestDispatch dispatch, AuthResource authenticationService,
			ResourceDelegate<GlobalConfigResource> globalConfigResource, CurrentUser currentUser,
			MenuPresenter menuPresenter, AppData appData, AppServiceWorkerManager swManager,
			MessagingManager messagingManager, CoreMessages i18nCore) {
		super(eventBus, view, proxy, placeManager, dispatch, authenticationService, globalConfigResource, menuPresenter,
				currentUser, SubSystem.FRO, swManager, messagingManager);

		this.i18nCore = i18nCore;
		this.i18n = i18n;
		this.resources = resources;
	}

	@Override
	protected void onBind() {
		super.onBind();
		getMenuPresenter().setMenuItems(createMenuitems());
		getMenuPresenter().setProfileBackground(resources.profileBackgroundImg());
	}

	private List<MenuItemDto> createMenuitems() {
		int index = 0;
		List<MenuItemDto> menuItems = new ArrayList<MenuItemDto>();

		// Dashboard menu item
		menuItems.add(MenuItemDto.builder().index(index++).type(MenuItemType.MENU_ITEM)
				.icon(IconType.DASHBOARD.name()).text(i18n.mainMenuItemDashboard()).nameToken(NameTokens.HOME).build());

		menuItems.add(MenuItemDto.builder().index(index++).type(MenuItemType.MENU_ITEM)
				.icon(IconType.EVENT_AVAILABLE.name()).text("Calendar").nameToken(NameTokens.CALENDAR).build());

		// Reservation menu item
		menuItems.add(MenuItemDto.builder().index(index++).type(MenuItemType.MENU_ITEM)
				.icon(IconType.EVENT_AVAILABLE.name()).text(i18n.mainMenuItemReservation())
				.nameToken(NameTokens.RESERVATION).build());

		// RoomPlan menu item
		menuItems.add(MenuItemDto.builder().index(index++).type(MenuItemType.MENU_ITEM)
				.icon(IconType.EVENT_BUSY.name()).text("RoomPlan")
				.nameToken(NameTokens.ROOM_PLAN).build());

		// Chat Room menu item
		menuItems.add(MenuItemDto.builder().index(index++).type(MenuItemType.MENU_ITEM).icon(IconType.FORUM.name())
				.text(i18n.mainMenuItemChatRoom()).nameToken(NameTokens.CREATE_RESERVATION).build());

		// Tasks menu item

		// Chat Room menu item
		menuItems.add(
				MenuItemDto.builder().index(index++).type(MenuItemType.MENU_ITEM).icon(IconType.ASSIGNMENT.name())
						.text(i18n.mainMenuItemTasks()).nameToken(NameTokens.TASK_MNGR).build());

		// Assignment menu group
		menuItems.add(MenuItemDto.builder().index(index++).type(MenuItemType.SUB_MENU)
				.icon(IconType.BUILD.name()).text(i18n.mainMenuGroupConfiguration())
				.addItem(MenuItemDto.builder().type(MenuItemType.MENU_ITEM).text(i18n.mainMenuItemHotelConfiguration())
						.nameToken(CoreNameTokens.HOTEL_CONFIG).build())
				.addItem(MenuItemDto.builder().type(MenuItemType.MENU_ITEM).text(i18n.mainMenuItemRateConfiguration())
						.nameToken(NameTokens.RATE_CONFIGURATION).build())
				.build());

		return menuItems;
	}
}
