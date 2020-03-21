/**
 * 
 */
package io.crs.hsys.client.inf.app;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import io.crs.hsys.client.inf.InfNameTokens;
import io.crs.hsys.client.inf.gps.config.GpsConfigPresenter;
import io.crs.hsys.client.inf.gps.config.GpsConfigWidgetsFactory;
import io.crs.hsys.client.inf.gps.display.GpsDisplayPresenter;
import io.crs.hsys.client.inf.i18n.InfMessages;
import io.crs.hsys.shared.api.AuthResource;
import io.crs.hsys.shared.api.GlobalConfigResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.cnst.SubSystem;
import io.crs.hsys.shared.dto.menu.MenuItemDto;

/**
 * @author CR
 *
 */
public class InfAppPresenter extends AbstractAppPresenter<InfAppPresenter.MyProxy> {
	private static Logger logger = Logger.getLogger(InfAppPresenter.class.getName());

	private final InfMessages i18n;
	private final GpsDisplayPresenter gpsDisplayPresenter;
	private GpsConfigPresenter gpsConfigPresenter;

	@ProxyStandard
	interface MyProxy extends Proxy<InfAppPresenter> {
	}

	@Inject
	InfAppPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, InfMessages i18n,
			RestDispatch dispatch, AuthResource authenticationService,
			ResourceDelegate<GlobalConfigResource> globalConfigResource, CurrentUser currentUser,
			MenuPresenter menuPresenter, GpsDisplayPresenter gpsDisplayPresenter,
			GpsConfigWidgetsFactory gpsConfigWidgetsFactory, AppData appData, AppServiceWorkerManager swManager,
			MessagingManager messagingManager) {
		super(eventBus, view, proxy, placeManager, dispatch, authenticationService, globalConfigResource, menuPresenter,
				currentUser, SubSystem.INF, swManager, messagingManager);

		this.i18n = i18n;
		this.gpsDisplayPresenter = gpsDisplayPresenter;

		gpsConfigPresenter = gpsConfigWidgetsFactory.createGpsConfig();
	}

	@Override
	protected void onBind() {
		super.onBind();
		logger.log(Level.INFO, "InfAppPresenter.onBind()");
		setInSlot(SLOT_MODAL, gpsConfigPresenter);
		getMenuPresenter().setMenuItems(createMenuitems());
		getMenuPresenter().setNavBarWidget(gpsDisplayPresenter);
		gpsDisplayPresenter.setGpsConfigPresenter(gpsConfigPresenter);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		logger.log(Level.INFO, "InfAppPresenter.onReveal()");
	}

	private List<MenuItemDto> createMenuitems() {
		List<MenuItemDto> menuItems = new ArrayList<MenuItemDto>();

		MenuItemDto dashboardItem = new MenuItemDto();
		dashboardItem.setIndex(1);
		dashboardItem.setType(MenuItemType.MENU_ITEM);
		dashboardItem.setIcon(IconType.DASHBOARD.name());
		dashboardItem.setText(i18n.mainMenuItemDashhboard());
		dashboardItem.setNameToken(CoreNameTokens.HOME);
		menuItems.add(dashboardItem);

		// Perfomance

		MenuItemDto perfSubMenu = new MenuItemDto();
		perfSubMenu.setIndex(2);
		perfSubMenu.setType(MenuItemType.SUB_MENU);
		perfSubMenu.setIcon(IconType.AV_TIMER.name());
		perfSubMenu.setText(i18n.mainMenuGroupPerformance());
		perfSubMenu.setItems(new ArrayList<MenuItemDto>());

		MenuItemDto perfMenuItem1 = new MenuItemDto();
		perfMenuItem1.setIndex(1);
		perfMenuItem1.setType(MenuItemType.MENU_ITEM);
		perfMenuItem1.setText("Occupancy");
		perfMenuItem1.setNameToken(InfNameTokens.PERF1);
		perfSubMenu.addItem(perfMenuItem1);

		MenuItemDto perfMenuItem2 = new MenuItemDto();
		perfMenuItem2.setIndex(2);
		perfMenuItem2.setType(MenuItemType.MENU_ITEM);
		perfMenuItem2.setText("Revenue");
		perfMenuItem2.setNameToken(InfNameTokens.ANALYTICS);
		perfSubMenu.addItem(perfMenuItem2);

		menuItems.add(perfSubMenu);

		// Pickup

		MenuItemDto pickUpSubMenu = new MenuItemDto();
		pickUpSubMenu.setIndex(3);
		pickUpSubMenu.setType(MenuItemType.SUB_MENU);
		pickUpSubMenu.setIcon(IconType.SHOPPING_CART.name());
		pickUpSubMenu.setText(i18n.mainMenuGroupPickup());
		pickUpSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(pickUpSubMenu);

		// Pace

		MenuItemDto paceSubMenu = new MenuItemDto();
		paceSubMenu.setIndex(4);
		paceSubMenu.setType(MenuItemType.SUB_MENU);
		paceSubMenu.setIcon(IconType.TIMER.name());
		paceSubMenu.setText(i18n.mainMenuGroupPace());
		paceSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(paceSubMenu);

		// Curve

		MenuItemDto curveSubMenu = new MenuItemDto();
		curveSubMenu.setIndex(5);
		curveSubMenu.setType(MenuItemType.SUB_MENU);
		curveSubMenu.setIcon(IconType.TRENDING_UP.name());
		curveSubMenu.setText(i18n.mainMenuGroupCurve());
		curveSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(curveSubMenu);

		// Web

		MenuItemDto webSubMenu = new MenuItemDto();
		webSubMenu.setIndex(6);
		webSubMenu.setType(MenuItemType.SUB_MENU);
		webSubMenu.setIcon(IconType.WEB.name());
		webSubMenu.setText(i18n.mainMenuGroupWeb());
		webSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(webSubMenu);

		// Social

		MenuItemDto socialSubMenu = new MenuItemDto();
		socialSubMenu.setIndex(7);
		socialSubMenu.setType(MenuItemType.SUB_MENU);
		socialSubMenu.setIcon(IconType.SHARE.name());
		socialSubMenu.setText(i18n.mainMenuGroupSocial());
		socialSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(socialSubMenu);

		// Satisfaction

		MenuItemDto satisfactionSubMenu = new MenuItemDto();
		satisfactionSubMenu.setIndex(8);
		satisfactionSubMenu.setType(MenuItemType.SUB_MENU);
		satisfactionSubMenu.setIcon(IconType.MOOD.name());
		satisfactionSubMenu.setText(i18n.mainMenuGroupSatisfaction());
		satisfactionSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(satisfactionSubMenu);

		// Benchmark

		MenuItemDto benchmarkSubMenu = new MenuItemDto();
		benchmarkSubMenu.setIndex(9);
		benchmarkSubMenu.setType(MenuItemType.SUB_MENU);
		benchmarkSubMenu.setIcon(IconType.COMPARE_ARROWS.name());
		benchmarkSubMenu.setText(i18n.mainMenuGroupBenchmark());
		benchmarkSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(benchmarkSubMenu);

		return menuItems;
	}
}
