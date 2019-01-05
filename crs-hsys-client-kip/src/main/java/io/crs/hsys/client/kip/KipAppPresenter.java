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
import io.crs.hsys.shared.dto.menu.MenuItemDto;
import io.crs.hsys.client.kip.gfilter.config.GfilterConfigPresenter;
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
//		this.gfilterDisplayPresenter = gfilterDisplayPresenter;
//		gfilterConfigPresenter = gfilterConfigPresenterFactory.createGfilterConfigPresenter();
	}

	@Override
	protected void onBind() {
		super.onBind();
//		setInSlot(SLOT_MODAL, gfilterConfigPresenter);
		getMenuPresenter().setMenuItems(createMenuitems());
		getMenuPresenter().setProfileBackground(resources.profileBackgroundImg());
//		getMenuPresenter().setNavBarWidget(gfilterDisplayPresenter);
//		gfilterDisplayPresenter.setGfilterConfigPresenter(gfilterConfigPresenter);
	}

	private List<MenuItemDto> createMenuitems() {
		List<MenuItemDto> menuItems = new ArrayList<MenuItemDto>();

		// *******************
		// Dashboard menu item
		// *******************
		MenuItemDto dasboardMenuItem = new MenuItemDto();
		dasboardMenuItem.setIndex(1);
		dasboardMenuItem.setType(MenuItemType.MENU_ITEM);
		dasboardMenuItem.setIcon(IconType.DASHBOARD.name());
		dasboardMenuItem.setText(i18n.mainMenuItemDashboard());
		dasboardMenuItem.setNameToken(KipNameTokens.HOME);
		menuItems.add(dasboardMenuItem);

		// *******************
		// Chat Room menu item
		// *******************
		MenuItemDto chatRoomItem = new MenuItemDto();
		chatRoomItem.setIndex(2);
		chatRoomItem.setType(MenuItemType.MENU_ITEM);
		chatRoomItem.setIcon(IconType.FORUM.name());
		chatRoomItem.setText(i18n.mainMenuItemChatRoom());
		chatRoomItem.setNameToken(KipNameTokens.CHAT_ROOM);
		menuItems.add(chatRoomItem);

		// ***************
		// Tasks menu item
		// ***************
		MenuItemDto tasksItem = new MenuItemDto();
		tasksItem.setIndex(3);
		tasksItem.setType(MenuItemType.MENU_ITEM);
		tasksItem.setIcon(IconType.ASSIGNMENT.name());
		tasksItem.setText(i18n.mainMenuItemTasks());
		tasksItem.setNameToken(KipNameTokens.TASK_MNGR);
		menuItems.add(tasksItem);

		// ********************
		// GuestRooms menu item
		// *********************
		MenuItemDto roomsMenuItem = new MenuItemDto();
		roomsMenuItem.setIndex(4);
		roomsMenuItem.setType(MenuItemType.MENU_ITEM);
		roomsMenuItem.setIcon(IconType.HOTEL.name());
		roomsMenuItem.setText(i18n.mainMenuItemGuestRooms());
		roomsMenuItem.setNameToken(KipNameTokens.GUEST_ROOMS);
		menuItems.add(roomsMenuItem);

		// **********************
		// Public Areas menu item
		// **********************
		MenuItemDto areasMenuItem = new MenuItemDto();
		areasMenuItem.setIndex(5);
		areasMenuItem.setType(MenuItemType.MENU_ITEM);
		areasMenuItem.setIcon(IconType.STORE.name());
		areasMenuItem.setText(i18n.mainMenuGroupPublicAreas());
		areasMenuItem.setNameToken(KipNameTokens.GUEST_ROOMS2);
		menuItems.add(areasMenuItem);

		// *********************
		// Assignment menu group
		// *********************
		MenuItemDto assignmentSubMenu = new MenuItemDto();
		assignmentSubMenu.setIndex(6);
		assignmentSubMenu.setType(MenuItemType.SUB_MENU);
		assignmentSubMenu.setIcon(IconType.ASSIGNMENT_RETURNED.name());
		assignmentSubMenu.setText(i18n.mainMenuGroupAssignment());
		assignmentSubMenu.setItems(new ArrayList<MenuItemDto>());

		MenuItemDto roomAssignMenuItem = new MenuItemDto();
		roomAssignMenuItem.setIndex(1);
		roomAssignMenuItem.setType(MenuItemType.MENU_ITEM);
		roomAssignMenuItem.setText(i18n.mainMenuItemRoomAssignment());
		roomAssignMenuItem.setNameToken(KipNameTokens.GUEST_ROOMS);
		assignmentSubMenu.addItem(roomAssignMenuItem);

		MenuItemDto areaAssigntMenuItem = new MenuItemDto();
		areaAssigntMenuItem.setIndex(2);
		areaAssigntMenuItem.setType(MenuItemType.MENU_ITEM);
		areaAssigntMenuItem.setText(i18n.mainMenuItemAreaAssignment());
		areaAssigntMenuItem.setNameToken(KipNameTokens.HK_ASSIGNMENTS);
		assignmentSubMenu.addItem(areaAssigntMenuItem);

		menuItems.add(assignmentSubMenu);

		// ******************
		// Minibar menu group
		// ******************
		MenuItemDto minibarSubMenu = new MenuItemDto();
		minibarSubMenu.setIndex(7);
		minibarSubMenu.setType(MenuItemType.SUB_MENU);
		minibarSubMenu.setIcon(IconType.KITCHEN.name());
		minibarSubMenu.setText(i18n.mainMenuGroupMinibar());
		minibarSubMenu.setItems(new ArrayList<MenuItemDto>());

		MenuItemDto consumptionMenuItem = new MenuItemDto();
		consumptionMenuItem.setIndex(1);
		consumptionMenuItem.setType(MenuItemType.MENU_ITEM);
		consumptionMenuItem.setText(i18n.mainMenuItemConsumption());
		consumptionMenuItem.setNameToken(KipNameTokens.GUEST_ROOMS);
		minibarSubMenu.addItem(consumptionMenuItem);

		menuItems.add(minibarSubMenu);

		return menuItems;
	}
}
