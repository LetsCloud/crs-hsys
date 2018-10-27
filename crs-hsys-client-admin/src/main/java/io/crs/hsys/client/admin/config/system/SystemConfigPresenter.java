/**
 * 
 */
package io.crs.hsys.client.admin.config.system;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.admin.browser.firebase.FirebaseBrowserFactory;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.LoggedInGatekeeper;
import io.crs.hsys.client.core.ui.config.AbstractConfigPresenter;

/**
 * @author robi
 *
 */
public class SystemConfigPresenter
		extends AbstractConfigPresenter<SystemConfigPresenter.MyView, SystemConfigPresenter.MyProxy>
		implements SystemConfigUiHandlers {
	private static Logger logger = Logger.getLogger(SystemConfigPresenter.class.getName());

	public static final String MARKET_GROUPS = "marketGroups";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.HOTEL_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<SystemConfigPresenter> {
	}

	@Inject
	SystemConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy, FirebaseBrowserFactory marketGroupBrowserFactory,
			CoreMessages i18n) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("HotelConfigPresenter()");

		setCaption(i18n.hotelConfigTitle());
		setDescription(i18n.hotelConfigDescription());
		setPlaceToken(CoreNameTokens.HOTEL_CONFIG);

		addContent(i18n.marketGroupBrowserTitle(), marketGroupBrowserFactory.createFirebaseBrowser(), MARKET_GROUPS);

		getView().setUiHandlers(this);
	}
}
