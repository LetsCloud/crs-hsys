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
import io.crs.hsys.client.core.CoreNameTokens;
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
import io.crs.hsys.shared.dto.menu.MenuItemDto.MenuItemCode;
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
		getMenuPresenter().setMenuItems(createMenuitems(currentUser.getAppUserDto().getPermissions()));
	}

	private List<MenuItemDto> createMenuitems(List<UserPerm> permissions) {
		List<MenuItemDto> menuItems = new ArrayList<MenuItemDto>();

		for (UserPerm permission : permissions) {
			if (permission.equals(UserPerm.UP_HKSUPERVISOR))
				createHousekeepingSupervisorMenu(menuItems);
			if (permission.equals(UserPerm.UP_HOUSEKEEPER))
				createHousekeeperMenu(menuItems);
			if (permission.equals(UserPerm.UP_MAINTMANAGER))
				createChiefEngineerMenu(menuItems);
			if (permission.equals(UserPerm.UP_TECHNICIAN))
				createMechanicMenu(menuItems);
			if (permission.equals(UserPerm.UP_RECEPTIONIST))
				createReceptionistMenu(menuItems);
			if (permission.equals(UserPerm.UP_ADMIN))
				createAdminstratorMenu(menuItems);
		}
		return menuItems;
	}

	/**
	 * Gondnoknői menü
	 * 
	 * @return
	 */
	private List<MenuItemDto> createHousekeepingSupervisorMenu(List<MenuItemDto> menuItems) {
		addDashboardMenuItem(menuItems, 1);
		addTaskManagerMenuItem(menuItems, 2);
		addChatRoomMenuItem(menuItems, 3);
		addGuestRoomMenuItem(menuItems, 4);
		addOooRoomMenuItem(menuItems, 5);
		addPublicAreaMenuItem(menuItems, 6);

		MenuItemDto assigments = addAssignmentMenuItem(menuItems, 7);
		addRoomAssignmentMenuItem(assigments, 1);
		addPublicAreaAssignmentMenuItem(assigments, 2);

		addMinibarPostsMenuItem(menuItems, 8);

		MenuItemDto configs = addConfigMenuItem(menuItems, 8);
		addHousekeepingConfigMenuItem(configs, 1);

		return menuItems;
	}

	/**
	 * Szobalány menü
	 * 
	 * @return
	 */
	private List<MenuItemDto> createHousekeeperMenu(List<MenuItemDto> menuItems) {
		addTaskManagerMenuItem(menuItems, 1);
		addChatRoomMenuItem(menuItems, 2);
		addGuestRoomMenuItem(menuItems, 3);
		addPublicAreaMenuItem(menuItems, 4);
		return menuItems;
	}

	/**
	 * Műszaki vezető menü
	 * 
	 * @return
	 */
	private List<MenuItemDto> createChiefEngineerMenu(List<MenuItemDto> menuItems) {
		addDashboardMenuItem(menuItems, 1);
		addTaskManagerMenuItem(menuItems, 2);
		addChatRoomMenuItem(menuItems, 3);
		addGuestRoomMenuItem(menuItems, 4);
		addPublicAreaMenuItem(menuItems, 5);

		MenuItemDto assigments = addAssignmentMenuItem(menuItems, 6);
		addMaintenanceAssignmentMenuItem(assigments, 1);

		MenuItemDto configs = addConfigMenuItem(menuItems, 7);
		addMaintenaceConfigMenuItem(configs, 1);

		return menuItems;
	}

	/**
	 * Műszakos menü
	 * 
	 * @return
	 */
	private List<MenuItemDto> createMechanicMenu(List<MenuItemDto> menuItems) {
		addTaskManagerMenuItem(menuItems, 1);
		addChatRoomMenuItem(menuItems, 2);
		addGuestRoomMenuItem(menuItems, 3);
		addPublicAreaMenuItem(menuItems, 4);
		return menuItems;
	}

	/**
	 * Recepciós menü
	 * 
	 * @return
	 */
	private List<MenuItemDto> createReceptionistMenu(List<MenuItemDto> menuItems) {
		addDashboardMenuItem(menuItems, 1);
		addTaskManagerMenuItem(menuItems, 2);
		addChatRoomMenuItem(menuItems, 3);
		addGuestRoomMenuItem(menuItems, 4);
		addOooRoomMenuItem(menuItems, 5);
		addMinibarPostsMenuItem(menuItems, 6);
		return menuItems;
	}

	/**
	 * Admin menü
	 * 
	 * @return
	 */
	private List<MenuItemDto> createAdminstratorMenu(List<MenuItemDto> menuItems) {
		addDashboardMenuItem(menuItems, 1);
		
		MenuItemDto configs = addConfigMenuItem(menuItems, 1);
		addUserConfigMenuItem(configs, 1);
		addHotelConfigMenuItem(configs, 2);
		addHousekeepingConfigMenuItem(configs, 3);
		addMaintenaceConfigMenuItem(configs, 4);
		
		return menuItems;
	}

	private void addDashboardMenuItem(List<MenuItemDto> menuItems, Integer index) {
		addMenuItem(menuItems, MenuItemCode.KIP_DASHBOARD, index, MenuItemType.MENU_ITEM, IconType.DASHBOARD.name(),
				"Műszerfal", KipNameTokens.HOME);
	}

	private void addTaskManagerMenuItem(List<MenuItemDto> menuItems, Integer index) {
		addMenuItem(menuItems, MenuItemCode.TASK_MANAGER, index, MenuItemType.MENU_ITEM, IconType.ASSIGNMENT.name(),
				i18n.mainMenuItemTasks(), KipNameTokens.TASK_MANAGER);
	}

	private void addChatRoomMenuItem(List<MenuItemDto> menuItems, Integer index) {
		addMenuItem(menuItems, MenuItemCode.CHAT_ROOM, index, MenuItemType.MENU_ITEM, IconType.FORUM.name(),
				i18n.mainMenuItemChatRoom(), KipNameTokens.CHAT_ROOM);
	}

	private void addGuestRoomMenuItem(List<MenuItemDto> menuItems, Integer index) {
		addMenuItem(menuItems, MenuItemCode.ROOM_BROWSER, index, MenuItemType.MENU_ITEM, IconType.HOTEL.name(),
				i18n.mainMenuItemGuestRooms(), KipNameTokens.GUEST_ROOMS);
	}

	private void addOooRoomMenuItem(List<MenuItemDto> menuItems, Integer index) {
		addMenuItem(menuItems, MenuItemCode.OOO_BROWSER, index, MenuItemType.MENU_ITEM, IconType.SETTINGS.name(),
				i18n.mainMenuItemGuestOoo(), KipNameTokens.OOO_ROOMS);
	}

	private void addPublicAreaMenuItem(List<MenuItemDto> menuItems, Integer index) {
		addMenuItem(menuItems, MenuItemCode.PUBLICAREA_BROWSER, index, MenuItemType.MENU_ITEM,
				IconType.ZOOM_OUT_MAP.name(), i18n.mainMenuItemPublicAreas(), KipNameTokens.PUBLIC_AREAS);
	}

	private MenuItemDto addAssignmentMenuItem(List<MenuItemDto> menuItems, Integer index) {
		return addMenuItem(menuItems, MenuItemCode.ASSIGNMENT_GROUP, index, MenuItemType.SUB_MENU,
				IconType.ASSIGNMENT_RETURNED.name(), i18n.mainMenuGroupTaskAssignment(), null);
	}

	private void addRoomAssignmentMenuItem(MenuItemDto group, Integer index) {
		addMenuItem(group.getItems(), MenuItemCode.ROOM_ASSIGNMENT, index, MenuItemType.MENU_ITEM, null,
				i18n.mainMenuItemRoomTasksAssignment(), KipNameTokens.ROOM_TASK_ASSIGNMENT);
	}

	private void addPublicAreaAssignmentMenuItem(MenuItemDto group, Integer index) {
		addMenuItem(group.getItems(), MenuItemCode.PUBLICAREA_ASSIGNMENT, index, MenuItemType.MENU_ITEM, null,
				i18n.mainMenuItemAreaTasksAssignment(), KipNameTokens.AREA_TASK_ASSIGNMENT);
	}

	private void addMaintenanceAssignmentMenuItem(MenuItemDto group, Integer index) {
		addMenuItem(group.getItems(), MenuItemCode.MAINTENANCE_ASSIGNMENT, index, MenuItemType.MENU_ITEM, null,
				i18n.mainMenuItemMaintenanceAssignment(), KipNameTokens.MAINTENANCE_TASK_ASSIGNMENT);
	}

	private void addMinibarPostsMenuItem(List<MenuItemDto> menuItems, Integer index) {
		addMenuItem(menuItems, MenuItemCode.MINIBAR_POSTS, index, MenuItemType.MENU_ITEM, IconType.KITCHEN.name(),
				i18n.mainMenuGroupMinibar(), KipNameTokens.MINIBAR_CONSUMPTION);
	}

	private MenuItemDto addConfigMenuItem(List<MenuItemDto> menuItems, Integer index) {
		return addMenuItem(menuItems, MenuItemCode.CONFIG_GROUP, index, MenuItemType.SUB_MENU,
				IconType.BUILD.name(), i18n.mainMenuGroupConfig(), null);
	}

	private void addUserConfigMenuItem(MenuItemDto group, Integer index) {
		addMenuItem(group.getItems(), MenuItemCode.USER_CONFIG, index, MenuItemType.MENU_ITEM, null,
				i18n.mainMenuItemUsersConfig(), CoreNameTokens.SYSTEM_CONFIG);
	}

	private void addHotelConfigMenuItem(MenuItemDto group, Integer index) {
		addMenuItem(group.getItems(), MenuItemCode.HOTEL_CONFIG, index, MenuItemType.MENU_ITEM, null,
				i18n.mainMenuItemHotelConfig(), CoreNameTokens.HOTEL_CONFIG);
	}

	private void addHousekeepingConfigMenuItem(MenuItemDto group, Integer index) {
		addMenuItem(group.getItems(), MenuItemCode.HOUSEKEEPING_CONFIG, index, MenuItemType.MENU_ITEM, null,
				i18n.mainMenuItemHousekeepingConfig(), KipNameTokens.ROOM_TASK_ASSIGNMENT);
	}

	private void addMaintenaceConfigMenuItem(MenuItemDto group, Integer index) {
		addMenuItem(group.getItems(), MenuItemCode.MAINTENANCE_CONFIG, index, MenuItemType.MENU_ITEM, null,
				i18n.mainMenuItemMaintenanceConfig(), KipNameTokens.MAINTENANCE_CONFIG);
	}

	private MenuItemDto addMenuItem(List<MenuItemDto> menuItems, MenuItemCode code, Integer index, MenuItemType type,
			String icon, String caption, String token) {
		if (menuItems.stream().anyMatch(o -> o.getCode().equals(code)))
			return null;
		MenuItemDto result = MenuItemDto.builder().code(MenuItemCode.KIP_DASHBOARD).index(index).type(type).icon(icon)
				.text(caption).nameToken(token).build();
		menuItems.add(result);
		return result;
	}
}
