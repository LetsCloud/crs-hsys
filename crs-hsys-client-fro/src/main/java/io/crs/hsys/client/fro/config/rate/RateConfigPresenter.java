/**
 * 
 */
package io.crs.hsys.client.fro.config.rate;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.config.AbstractConfigPresenter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.fro.NameTokens;
import io.crs.hsys.client.fro.browser.ratecode.RateCodeBrowserFactory;
import io.crs.hsys.client.fro.i18n.FroMessages;

/**
 * @author robi
 *
 */
public class RateConfigPresenter
		extends AbstractConfigPresenter<RateConfigPresenter.MyView, RateConfigPresenter.MyProxy>
		implements RateConfigUiHandlers {
	private static Logger logger = Logger.getLogger(RateConfigPresenter.class.getName());

	public static final String RATE_CODES = "rateCodes";
	public static final String MARKET_GROUPS = "marketGroups";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.RATE_CONFIGURATION)
//	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<RateConfigPresenter> {
	}

	@Inject
	RateConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			RateCodeBrowserFactory rateCodeBrowserFactory, CoreMessages i18nCore, FroMessages i18n) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("HotelConfigPresenter()");

		setCaption(i18n.rateConfigTitle());
		setDescription(i18n.rateConfigDescription());
		setPlaceToken(NameTokens.RATE_CONFIGURATION);

		addContent(i18n.rateCodeBrowserTitle(), rateCodeBrowserFactory.createRateCodeBrowser(), RATE_CODES);
//addContent(i18n.marketGroupBrowserTitle(), marketGroupBrowserFactory.createMarketGroupBrowser(), MARKET_GROUPS);

		getView().setUiHandlers(this);
	}
}
