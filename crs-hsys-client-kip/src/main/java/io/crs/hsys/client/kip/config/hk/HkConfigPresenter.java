package io.crs.hsys.client.kip.config.hk;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.browser.room.RoomBrowserFactory;
import io.crs.hsys.client.core.browser.roomtype.RoomTypeBrowserFactory;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.config.AbstractConfigPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.browser.hktaskgroup.HkTaskGroupBrowserFactory;
import io.crs.hsys.client.kip.i18n.KipMessages;

public class HkConfigPresenter extends AbstractConfigPresenter<HkConfigPresenter.MyView, HkConfigPresenter.MyProxy>
		implements HkConfigUiHandlers {
	private static Logger logger = Logger.getLogger(HkConfigPresenter.class.getName());

	public static final String TASK_GROUPS = "taskGroups";
	public static final String ROOM_TYPES = "roomTypes";
	public static final String ROOMS = "rooms";
	public static final String MARKET_GROUPS = "marketGroups";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(KipNameTokens.HOUSEKEEPING_CONFIG)
//	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<HkConfigPresenter> {
	}

	@Inject
	HkConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			HkTaskGroupBrowserFactory hkTaskGroupBrowserFactory, RoomTypeBrowserFactory roomTypeBrowserFactory,
			RoomBrowserFactory roomBrowserFactory, CoreMessages i18nCore, KipMessages i18n) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("HotelConfigPresenter()");

		setCaption(i18n.housekeepingConfigTitle());
		setDescription(i18n.housekeepingConfigDescription());
		setPlaceToken(CoreNameTokens.HOTEL_CONFIG);

		addContent(i18n.hkTaskGroupBrowserTitle(), hkTaskGroupBrowserFactory.createHkTaskGroupBrowser(), TASK_GROUPS);
		addContent(i18nCore.roomTypeBrowserTitle(), roomTypeBrowserFactory.createRoomTypeTablePresenter(), ROOM_TYPES);
		addContent(i18nCore.roomBrowserTitle(), roomBrowserFactory.createRoomTablePresenter(), ROOMS);

		getView().setUiHandlers(this);
	}
}
