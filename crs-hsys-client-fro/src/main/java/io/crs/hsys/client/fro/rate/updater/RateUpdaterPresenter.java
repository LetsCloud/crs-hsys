/**
 * 
 */
package io.crs.hsys.client.fro.rate.updater;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.event.ContentPushEvent;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.filter.FilterChangeEvent;
import io.crs.hsys.client.fro.NameTokens;
import io.crs.hsys.shared.cnst.MenuItemType;

/**
 * @author robi
 *
 */
public class RateUpdaterPresenter extends Presenter<RateUpdaterPresenter.MyView, RateUpdaterPresenter.MyProxy>
		implements RateUpdaterUiHandlers, FilterChangeEvent.FilterChangeHandler, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(RateUpdaterPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<RateUpdaterUiHandlers> {
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.RATE_UPDATER)
//@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<RateUpdaterPresenter> {
	}

	@Inject
	RateUpdaterPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("RateUpdaterPresenter()");

		addRegisteredHandler(ContentPushEvent.TYPE, this);
		addVisibleHandler(FilterChangeEvent.TYPE, this);

		getView().setUiHandlers(this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire("Rate Updater", "", MenuItemType.MENU_ITEM, this);
	}

	@Override
	public void onFilterChange(FilterChangeEvent event) {
		logger.info("RateBrowserPresenter()->onFilterChange()");
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
	}
}
