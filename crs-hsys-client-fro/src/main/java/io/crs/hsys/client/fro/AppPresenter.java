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

//	private final GfilterDisplayPresenter gfilterDisplayPresenter;
//	private GfilterConfigPresenter gfilterConfigPresenter;

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
//		this.gfilterDisplayPresenter = gfilterDisplayPresenter;
//		gfilterConfigPresenter = gfilterConfigPresenterFactory.createGfilterConfigPresenter();
	}

	@Override
	protected void onBind() {
		super.onBind();
		getMenuPresenter().setMenuItems(createMenuitems());
		getMenuPresenter().setProfileBackground(resources.profileBackgroundImg());
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
		dasboardMenuItem.setNameToken(NameTokens.HOME);
		menuItems.add(dasboardMenuItem);

		// *******************
		// Chat Room menu item
		// *******************
		MenuItemDto chatRoomItem = new MenuItemDto();
		chatRoomItem.setIndex(2);
		chatRoomItem.setType(MenuItemType.MENU_ITEM);
		chatRoomItem.setIcon(IconType.FORUM.name());
		chatRoomItem.setText(i18n.mainMenuItemChatRoom());
		chatRoomItem.setNameToken(NameTokens.CHAT_ROOM);
		menuItems.add(chatRoomItem);

		// ***************
		// Tasks menu item
		// ***************
		MenuItemDto tasksItem = new MenuItemDto();
		tasksItem.setIndex(3);
		tasksItem.setType(MenuItemType.MENU_ITEM);
		tasksItem.setIcon(IconType.ASSIGNMENT.name());
		tasksItem.setText(i18n.mainMenuItemTasks());
		tasksItem.setNameToken(NameTokens.TASK_MNGR);
		menuItems.add(tasksItem);

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
		roomAssignMenuItem.setNameToken(NameTokens.GUEST_ROOMS);
		assignmentSubMenu.addItem(roomAssignMenuItem);

		MenuItemDto areaAssigntMenuItem = new MenuItemDto();
		areaAssigntMenuItem.setIndex(2);
		areaAssigntMenuItem.setType(MenuItemType.MENU_ITEM);
		areaAssigntMenuItem.setText(i18n.mainMenuItemAreaAssignment());
		areaAssigntMenuItem.setNameToken(NameTokens.HK_ASSIGNMENTS);
		assignmentSubMenu.addItem(areaAssigntMenuItem);

		menuItems.add(assignmentSubMenu);

		return menuItems;
	}
}
