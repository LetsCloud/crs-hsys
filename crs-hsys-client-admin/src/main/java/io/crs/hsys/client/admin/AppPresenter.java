/**
 * 
 */
package io.crs.hsys.client.admin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;

import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;

import gwt.material.design.client.constants.IconType;
import io.crs.hsys.client.admin.i18n.AdminMessages;
import io.crs.hsys.client.admin.resources.AdminResources;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.app.AppServiceWorkerManager;
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

	private final AdminMessages i18n;
	private final AdminResources resources;

	@ProxyStandard
	interface MyProxy extends Proxy<AppPresenter> {
	}

	@Inject
	AppPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, AdminMessages i18n,
			RestDispatch dispatch, AuthResource authenticationService, CurrentUser currentUser,
			MenuPresenter menuPresenter, AppData appData, AppServiceWorkerManager messagingManager, AdminResources resources) {
		super(eventBus, view, proxy, placeManager, dispatch, authenticationService, menuPresenter, currentUser,
				SubSystem.CFG, messagingManager);

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
		// System configurations
		// *********************
		MenuItemDto commonConfigSubMenu = new MenuItemDto();
		commonConfigSubMenu.setIndex(2);
		commonConfigSubMenu.setType(MenuItemType.SUB_MENU);
		commonConfigSubMenu.setIcon(IconType.SETTINGS.name());
		commonConfigSubMenu.setText(i18n.mainSubMenuConfigs());
		commonConfigSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(commonConfigSubMenu);

		MenuItemDto userConfigMenuItem = new MenuItemDto();
		userConfigMenuItem.setIndex(1);
		userConfigMenuItem.setType(MenuItemType.MENU_ITEM);
		userConfigMenuItem.setText(i18n.menuItemSystemConfigs());
		userConfigMenuItem.setNameToken(NameTokens.SYSTEM_CONFIG);
		commonConfigSubMenu.addItem(userConfigMenuItem);

		return menuItems;
	}
}
