package io.crs.hsys.client.kip.config.hk;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.browser.hotel.HotelBrowserFactory;
import io.crs.hsys.client.core.browser.room.RoomBrowserFactory;
import io.crs.hsys.client.core.browser.roomtype.RoomTypeBrowserFactory;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.LoggedInGatekeeper;
import io.crs.hsys.client.core.ui.config.AbstractConfigPresenter;
import io.crs.hsys.client.kip.KipNameTokens;

public class HkConfigPresenter extends AbstractConfigPresenter<HkConfigPresenter.MyView, HkConfigPresenter.MyProxy>
		implements HkConfigUiHandlers {
	private static Logger logger = Logger.getLogger(HkConfigPresenter.class.getName());

	public static final String HOTELS = "hotels";
	public static final String ROOM_TYPES = "roomTypes";
	public static final String ROOMS = "rooms";
	public static final String MARKET_GROUPS = "marketGroups";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(KipNameTokens.HOUSEKEEPING_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<HkConfigPresenter> {
	}

	@Inject
	HkConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			HotelBrowserFactory hotelBrowserFactory, RoomTypeBrowserFactory roomTypeBrowserFactory,
			RoomBrowserFactory roomBrowserFactory, CoreMessages i18n) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("HotelConfigPresenter()");

		setCaption(i18n.hotelConfigTitle());
		setDescription(i18n.hotelConfigDescription());
		setPlaceToken(CoreNameTokens.HOTEL_CONFIG);

		addContent(i18n.hotelBrowserTitle(), hotelBrowserFactory.createHotelTablePresenter(), HOTELS);
		addContent(i18n.roomTypeBrowserTitle(), roomTypeBrowserFactory.createRoomTypeTablePresenter(), ROOM_TYPES);
		addContent(i18n.roomBrowserTitle(), roomBrowserFactory.createRoomTablePresenter(), ROOMS);
//addContent(i18n.marketGroupBrowserTitle(), marketGroupBrowserFactory.createMarketGroupBrowser(), MARKET_GROUPS);

		getView().setUiHandlers(this);
	}
}
