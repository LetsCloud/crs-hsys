/**
 * 
 */
package io.crs.hsys.client.kip;

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
import io.crs.hsys.shared.api.AuthResource;
import io.crs.hsys.shared.api.GlobalConfigResource;
import io.crs.hsys.shared.constans.MenuItemType;
import io.crs.hsys.shared.constans.SubSystem;
import io.crs.hsys.shared.constans.UserPerm;
import io.crs.hsys.shared.dto.menu.MenuItemDto;
import io.crs.hsys.client.kip.gfilter.config.GfilterConfigPresenterFactory;
import io.crs.hsys.client.kip.gfilter.display.GfilterDisplayPresenter;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.client.kip.resources.KipResources;

/**
 * @author CR
 *
 */
public class KipAppPresenter extends AbstractAppPresenter<KipAppPresenter.MyProxy> {

	private final KipMessages i18n;
	private final KipResources resources;
	private final CurrentUser currentUser;

	@ProxyStandard
	interface MyProxy extends Proxy<KipAppPresenter> {
	}

//	private final GfilterDisplayPresenter gfilterDisplayPresenter;
//	private GfilterConfigPresenter gfilterConfigPresenter;

	@Inject
	KipAppPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, KipMessages i18n,
			KipResources resources, RestDispatch dispatch, AuthResource authenticationService,
			ResourceDelegate<GlobalConfigResource> globalConfigResource, CurrentUser currentUser,
			MenuPresenter menuPresenter, GfilterDisplayPresenter gfilterDisplayPresenter,
			GfilterConfigPresenterFactory gfilterConfigPresenterFactory, AppData appData,
			AppServiceWorkerManager swManager, MessagingManager messagingManager) {
		super(eventBus, view, proxy, placeManager, dispatch, authenticationService, globalConfigResource, menuPresenter,
				currentUser, SubSystem.KIP, swManager, messagingManager);

		this.i18n = i18n;
		this.resources = resources;
		this.currentUser = currentUser;
//		this.gfilterDisplayPresenter = gfilterDisplayPresenter;
//		gfilterConfigPresenter = gfilterConfigPresenterFactory.createGfilterConfigPresenter();
	}

	@Override
	protected void onBind() {
		super.onBind();
//		setInSlot(SLOT_MODAL, gfilterConfigPresenter);
		getMenuPresenter().setProfileBackground(resources.profileBackgroundImg());
//		getMenuPresenter().setNavBarWidget(gfilterDisplayPresenter);
//		gfilterDisplayPresenter.setGfilterConfigPresenter(gfilterConfigPresenter);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		getMenuPresenter().setMenuItems(createMenuitems(currentUser.getAppUserDto().getPermissions().get(0)));
	}

	private List<MenuItemDto> createMenuitems(UserPerm permission) {
		switch (permission) {
		case UP_HKSUPERVISOR:
			return createHkSvMenu();
		case UP_HOUSEKEEPER:
			return createHkMenu();
		case UP_MAINTMANAGER:
			return createHkSvMenu();
		case UP_TECHNICIAN:
			return createHkSvMenu();
		case UP_RECEPTIONIST:
			return createHkSvMenu();
		case UP_ADMIN:
			return createHkSvMenu();
		default:
			return createHkSvMenu();
		}
	}

	private List<MenuItemDto> createHkSvMenu() {
		List<MenuItemDto> menuItems = new ArrayList<MenuItemDto>();

		// Dashboard menu item
		menuItems.add(MenuItemDto.builder().index(1).type(MenuItemType.MENU_ITEM).icon(IconType.DASHBOARD.name())
				.text(i18n.mainMenuItemDashboard()).nameToken(KipNameTokens.HOME).build());

		// Tasks menu item
		menuItems.add(MenuItemDto.builder().index(2).type(MenuItemType.MENU_ITEM).icon(IconType.ASSIGNMENT.name())
				.text(i18n.mainMenuItemTasks()).nameToken(KipNameTokens.TASK_MNGR).build());

		// Chat Room menu item
		menuItems.add(MenuItemDto.builder().index(3).type(MenuItemType.MENU_ITEM).icon(IconType.FORUM.name())
				.text(i18n.mainMenuItemChatRoom()).nameToken(KipNameTokens.CHAT_ROOM).build());

		// GuestRooms menu item
		menuItems.add(MenuItemDto.builder().index(4).type(MenuItemType.MENU_ITEM).icon(IconType.HOTEL.name())
				.text(i18n.mainMenuItemGuestRooms()).nameToken(KipNameTokens.GUEST_ROOMS).build());

		// Public Areas menu item
		menuItems.add(MenuItemDto.builder().index(5).type(MenuItemType.MENU_ITEM).icon(IconType.ZOOM_OUT_MAP.name())
				.text(i18n.mainMenuGroupPublicAreas()).nameToken(KipNameTokens.GUEST_ROOMS2).build());

		// Assignment menu group
		menuItems.add(MenuItemDto.builder()
				.index(6)
				.type(MenuItemType.SUB_MENU)
				.icon(IconType.ASSIGNMENT_RETURNED.name())
				.text(i18n.mainMenuGroupAssignment())
				.addItem(MenuItemDto.builder()
						.index(1).type(MenuItemType.MENU_ITEM)
						.text(i18n.mainMenuItemRoomAssignment())
						.nameToken(KipNameTokens.GUEST_ROOMS)
						.build())
				.addItem(MenuItemDto.builder()
						.index(2).type(MenuItemType.MENU_ITEM)
						.text(i18n.mainMenuItemAreaAssignment())
						.nameToken(KipNameTokens.HK_ASSIGNMENTS)
						.build())
				.build());

		// Public Areas menu item
		menuItems.add(MenuItemDto.builder()
				.index(7)
				.type(MenuItemType.MENU_ITEM)
				.icon(IconType.KITCHEN.name())
				.text(i18n.mainMenuGroupMinibar())
				.nameToken(KipNameTokens.GUEST_ROOMS)
				.build());

		// Config menu group
		menuItems.add(MenuItemDto.builder()
				.index(8)
				.type(MenuItemType.SUB_MENU)
				.icon(IconType.SETTINGS.name())
				.text(i18n.mainMenuGroupConfig())
				.addItem(MenuItemDto.builder()
						.index(1).type(MenuItemType.MENU_ITEM)
						.text(i18n.mainMenuItemHousekeepingConfig())
						.nameToken(KipNameTokens.GUEST_ROOMS)
						.build())
				.build());

		return menuItems;
	}

	private List<MenuItemDto> createHkMenu() {
		List<MenuItemDto> menuItems = new ArrayList<MenuItemDto>();

		// Tasks menu item
		menuItems.add(MenuItemDto.builder().index(1).type(MenuItemType.MENU_ITEM).icon(IconType.ASSIGNMENT.name())
				.text(i18n.mainMenuItemTasks()).nameToken(KipNameTokens.TASK_MNGR).build());

		// Chat Room menu item
		menuItems.add(MenuItemDto.builder().index(2).type(MenuItemType.MENU_ITEM).icon(IconType.FORUM.name())
				.text(i18n.mainMenuItemChatRoom()).nameToken(KipNameTokens.CHAT_ROOM).build());

		// GuestRooms menu item
		menuItems.add(MenuItemDto.builder().index(3).type(MenuItemType.MENU_ITEM).icon(IconType.HOTEL.name())
				.text(i18n.mainMenuItemGuestRooms()).nameToken(KipNameTokens.GUEST_ROOMS).build());

		// Public Areas menu item
		menuItems.add(MenuItemDto.builder().index(4).type(MenuItemType.MENU_ITEM).icon(IconType.ZOOM_OUT_MAP.name())
				.text(i18n.mainMenuGroupPublicAreas()).nameToken(KipNameTokens.GUEST_ROOMS2).build());

		return menuItems;
	}

}
