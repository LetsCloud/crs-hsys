/**
 * 
 */
package io.crs.hsys.client.cfg;

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

import io.crs.hsys.client.cfg.i18n.CfgMessages;
import io.crs.hsys.client.cfg.resources.CfgResources;
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

/**
 * @author CR
 *
 */
public class AppPresenter extends AbstractAppPresenter<AppPresenter.MyProxy> {

	private final CfgMessages i18n;
	private final CfgResources resources;

	@ProxyStandard
	interface MyProxy extends Proxy<AppPresenter> {
	}

	@Inject
	AppPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, CfgMessages i18n,
			RestDispatch dispatch, AuthResource authenticationService,
			ResourceDelegate<GlobalConfigResource> globalConfigResource, CurrentUser currentUser,
			MenuPresenter menuPresenter, AppData appData, AppServiceWorkerManager swManager, CfgResources resources,
			MessagingManager messagingManager) {
		super(eventBus, view, proxy, placeManager, dispatch, authenticationService, globalConfigResource, menuPresenter,
				currentUser, SubSystem.CFG, swManager, messagingManager);

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
		List<MenuItemDto> menuItems = new ArrayList<MenuItemDto>();

		MenuItemDto dasboardMenuItem = new MenuItemDto();
		dasboardMenuItem.setIndex(1);
		dasboardMenuItem.setType(MenuItemType.MENU_ITEM);
		dasboardMenuItem.setIcon(IconType.DASHBOARD.name());
		dasboardMenuItem.setText(i18n.mainMenuItemDashboard());
		dasboardMenuItem.setNameToken(NameTokens.HOME);
		menuItems.add(dasboardMenuItem);

		// *********************
		// Common configurations
		// *********************
		MenuItemDto commonConfigSubMenu = new MenuItemDto();
		commonConfigSubMenu.setIndex(2);
		commonConfigSubMenu.setType(MenuItemType.SUB_MENU);
		commonConfigSubMenu.setIcon(IconType.SETTINGS.name());
		commonConfigSubMenu.setText(i18n.mainMenuCommonConfig());
		commonConfigSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(commonConfigSubMenu);

		MenuItemDto userConfigMenuItem = new MenuItemDto();
		userConfigMenuItem.setIndex(1);
		userConfigMenuItem.setType(MenuItemType.MENU_ITEM);
		userConfigMenuItem.setText(i18n.menuItemUserConfig());
		userConfigMenuItem.setNameToken(NameTokens.SYSTEM_CONFIG);
		commonConfigSubMenu.addItem(userConfigMenuItem);

		MenuItemDto configMenuItem2 = new MenuItemDto();
		configMenuItem2.setIndex(2);
		configMenuItem2.setType(MenuItemType.MENU_ITEM);
		configMenuItem2.setText(i18n.menuItemProfileConfig());
		configMenuItem2.setNameToken(NameTokens.PROFILE_CONFIG);
		commonConfigSubMenu.addItem(configMenuItem2);

		// ***************************
		// Front office configurations
		// ***************************
		MenuItemDto froConfigSubMenu = new MenuItemDto();
		froConfigSubMenu.setIndex(4);
		froConfigSubMenu.setType(MenuItemType.SUB_MENU);
		froConfigSubMenu.setIcon(IconType.HOTEL.name());
		froConfigSubMenu.setText(i18n.mainMenuFroConfig());
		froConfigSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(froConfigSubMenu);

		MenuItemDto configMenuItem3 = new MenuItemDto();
		configMenuItem3.setIndex(3);
		configMenuItem3.setType(MenuItemType.MENU_ITEM);
		configMenuItem3.setText(i18n.menuItemHotelConfig());
		configMenuItem3.setNameToken(NameTokens.HOTEL_CONFIG);
		froConfigSubMenu.addItem(configMenuItem3);

		// ***************************
		// Housekeeping configurations
		// ***************************
		MenuItemDto kipConfigSubMenu = new MenuItemDto();
		kipConfigSubMenu.setIndex(4);
		kipConfigSubMenu.setType(MenuItemType.SUB_MENU);
		kipConfigSubMenu.setIcon(IconType.BRUSH.name());
		kipConfigSubMenu.setText(i18n.mainMenuKipConfig());
		kipConfigSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(kipConfigSubMenu);

		// *************************************
		// Management information configurations
		// *************************************
		MenuItemDto infConfigSubMenu = new MenuItemDto();
		infConfigSubMenu.setIndex(4);
		infConfigSubMenu.setType(MenuItemType.SUB_MENU);
		infConfigSubMenu.setIcon(IconType.INSERT_CHART.name());
		infConfigSubMenu.setText(i18n.mainMenuInfConfig());
		infConfigSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(infConfigSubMenu);

		return menuItems;
	}
}
