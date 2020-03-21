/**
 * 
 */
package io.crs.hsys.client.fro.resnew;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.event.ContentPushEvent;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.fro.FroAppPresenter;
import io.crs.hsys.client.fro.NameTokens;
import io.crs.hsys.client.fro.filter.FroFilterFactory;
import io.crs.hsys.client.fro.filter.createres.CreateResFilterPresenter;
import io.crs.hsys.shared.cnst.MenuItemType;

/**
 * @author robi
 *
 */
public class ResNewPresenter extends Presenter<ResNewPresenter.MyView, ResNewPresenter.MyProxy>
		implements ResNewUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(ResNewPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<ResNewUiHandlers> {
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();

	private final CreateResFilterPresenter filter;

	@ProxyCodeSplit
	@NameToken(NameTokens.CREATE_RESERVATION)
	// @UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<ResNewPresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_CONTENT = new SingleSlot<>();

	@Inject
	ResNewPresenter(EventBus eventBus, MyView view, MyProxy proxy, FroFilterFactory filterFactory) {
		super(eventBus, view, proxy, FroAppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "ResNewPresenter()");

		this.filter = filterFactory.createBookingFilter();
		
		getView().setUiHandlers(this);
		addRegisteredHandler(ContentPushEvent.TYPE, this);
	}

	@Override
	public void onBind() {
		super.onBind();
		logger.log(Level.INFO, "onBind()");
		setInSlot(SLOT_FILTER, filter);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		logger.log(Level.INFO, "onReveal()");
		SetPageTitleEvent.fire("Create new reservation", "X", MenuItemType.MENU_ITEM, this);
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		// TODO Auto-generated method stub

	}
}
