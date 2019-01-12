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
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.app.AppServiceWorkerManager;
import io.crs.hsys.client.core.firebase.messaging.MessagingManager;
import io.crs.hsys.client.core.menu.MenuPresenter;
import io.crs.hsys.client.core.security.AppData;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.fro.i18n.FroMessages;
import io.crs.hsys.client.fro.resources.FroResources;
import io.crs.hsys.shared.api.AuthResource;
import io.crs.hsys.shared.api.GlobalConfigResource;
import io.crs.hsys.shared.constans.MenuItemType;
import io.crs.hsys.shared.constans.SubSystem;
import io.crs.hsys.shared.dto.menu.MenuItemDto;

/**
 * @author CR
 *
 */
public class AppPresenter extends AbstractAppPresenter<AppPresenter.MyProxy> {

	private final FroMessages i18n;
	private final FroResources resources;

	@ProxyStandard
	interface MyProxy extends Proxy<AppPresenter> {
	}

	@Inject
	AppPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, FroMessages i18n,
			FroResources resources, RestDispatch dispatch, AuthResource authenticationService,
			ResourceDelegate<GlobalConfigResource> globalConfigResource, CurrentUser currentUser,
			MenuPresenter menuPresenter, AppData appData, AppServiceWorkerManager swManager,
			MessagingManager messagingManager) {
		super(eventBus, view, proxy, placeManager, dispatch, authenticationService, globalConfigResource, menuPresenter,
				currentUser, SubSystem.FRO, swManager, messagingManager);

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
		menuItems.add(new MenuItemDto.Builder().index(index++).type(MenuItemType.MENU_ITEM)
				.icon(IconType.DASHBOARD.name()).text(i18n.mainMenuItemDashboard()).nameToken(NameTokens.HOME).build());

		// Reservation menu item
		menuItems.add(new MenuItemDto.Builder().index(index++).type(MenuItemType.MENU_ITEM)
				.icon(IconType.EVENT_AVAILABLE.name()).text(i18n.mainMenuItemReservation())
				.nameToken(NameTokens.RESERVATION).build());

		// Chat Room menu item
		menuItems.add(new MenuItemDto.Builder().index(index++).type(MenuItemType.MENU_ITEM).icon(IconType.FORUM.name())
				.text(i18n.mainMenuItemChatRoom()).nameToken(NameTokens.CHAT_ROOM).build());

		// Tasks menu item

		// Chat Room menu item
		menuItems.add(
				new MenuItemDto.Builder().index(index++).type(MenuItemType.MENU_ITEM).icon(IconType.ASSIGNMENT.name())
						.text(i18n.mainMenuItemTasks()).nameToken(NameTokens.TASK_MNGR).build());

		// Assignment menu group
		menuItems.add(new MenuItemDto.Builder().index(index++).type(MenuItemType.SUB_MENU)
				.icon(IconType.ASSIGNMENT_RETURNED.name()).text(i18n.mainMenuGroupAssignment())
				.addItem(new MenuItemDto.Builder().type(MenuItemType.MENU_ITEM).text(i18n.mainMenuItemRoomAssignment())
						.nameToken(NameTokens.HK_ASSIGNMENTS).build())
				.addItem(new MenuItemDto.Builder().type(MenuItemType.MENU_ITEM).text(i18n.mainMenuItemAreaAssignment())
						.nameToken(NameTokens.HK_ASSIGNMENTS).build())
				.build());

		return menuItems;
	}
}
