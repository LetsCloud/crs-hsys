/**
 * 
 */
package io.crs.hsys.client.kip.dashboard;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
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
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.shared.constans.MenuItemType;

/**
 * @author CR
 *
 */
public class DashboardPresenter extends Presenter<DashboardPresenter.MyView, DashboardPresenter.MyProxy>
		implements DashboardUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(DashboardPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<DashboardUiHandlers> {
		void showCards();
	}

	@ProxyCodeSplit
	@NameToken(KipNameTokens.HOME)
	// @UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<DashboardPresenter> {
	}

	@Inject
	DashboardPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "DashboardPresenter()");

		addRegisteredHandler(ContentPushEvent.TYPE, this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire("Műszerfal", "Gondnoknők részére", MenuItemType.MENU_ITEM, this);
		getView().showCards();
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		// TODO Auto-generated method stub

	}

}
