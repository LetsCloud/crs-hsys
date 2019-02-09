/**
 * 
 */
package io.crs.hsys.client.cfg.config.hotel;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.cfg.browser.hotel.HotelBrowserFactory;
import io.crs.hsys.client.cfg.browser.marketgroup.MarketGroupBrowserFactory;
import io.crs.hsys.client.cfg.browser.room.RoomBrowserFactory;
import io.crs.hsys.client.cfg.browser.roomtype.RoomTypeBrowserFactory;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.LoggedInGatekeeper;
import io.crs.hsys.client.core.ui.config.AbstractConfigPresenter;

/**
 * @author robi
 *
 */
public class HotelConfigPresenter
		extends AbstractConfigPresenter<HotelConfigPresenter.MyView, HotelConfigPresenter.MyProxy>
		implements HotelConfigUiHandlers {
	private static Logger logger = Logger.getLogger(HotelConfigPresenter.class.getName());

	public static final String HOTELS = "hotels";
	public static final String ROOM_TYPES = "roomTypes";
	public static final String ROOMS = "rooms";
	public static final String MARKET_GROUPS = "marketGroups";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.HOTEL_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<HotelConfigPresenter> {
	}

	@Inject
	HotelConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			HotelBrowserFactory hotelBrowserFactory, RoomTypeBrowserFactory roomTypeBrowserFactory,
			RoomBrowserFactory roomBrowserFactory, MarketGroupBrowserFactory marketGroupBrowserFactory,
			CoreMessages i18n) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("HotelConfigPresenter()");

		setCaption(i18n.hotelConfigTitle());
		setDescription(i18n.hotelConfigDescription());
		setPlaceToken(CoreNameTokens.HOTEL_CONFIG);

		addContent(i18n.hotelBrowserTitle(), hotelBrowserFactory.createHotelTablePresenter(), HOTELS);
		addContent(i18n.roomTypeBrowserTitle(), roomTypeBrowserFactory.createRoomTypeTablePresenter(), ROOM_TYPES);
		addContent(i18n.roomBrowserTitle(), roomBrowserFactory.createRoomTablePresenter(), ROOMS);
		addContent(i18n.marketGroupBrowserTitle(), marketGroupBrowserFactory.createMarketGroupBrowser(), MARKET_GROUPS);

		getView().setUiHandlers(this);
	}
}
