/**
 * 
 */
package io.crs.hsys.client.fro.reservation;

import java.util.HashMap;
import java.util.Map;
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
import io.crs.hsys.client.fro.AppPresenter;
import io.crs.hsys.client.fro.NameTokens;
import io.crs.hsys.shared.cnst.MenuItemType;

/**
 * @author robi
 *
 */
public class ReservationPresenter extends Presenter<ReservationPresenter.MyView, ReservationPresenter.MyProxy>
		implements ReservationUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(ReservationPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<ReservationUiHandlers> {
		void buildMenu();

		void setMobileView(Boolean show);

		void setDesktopMenu(Integer index);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.RESERVATION)
	// @UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<ReservationPresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_CONTENT = new SingleSlot<>();

	private Integer activeWidget;
	private Map<Integer, AbstractResWidget<?>> widgetsMap = new HashMap<Integer, AbstractResWidget<?>>();

	@Inject
	ReservationPresenter(EventBus eventBus, MyView view, MyProxy proxy, ResWidgetPresenterFactory widgetFactgory) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "ReservationPresenter()");

		widgetsMap.put(1, widgetFactgory.createMainResPresenter());
		widgetsMap.put(2, widgetFactgory.createRoomResPresenter());
		widgetsMap.put(3, widgetFactgory.createExtraResPresenter());
		widgetsMap.put(4, widgetFactgory.createGuestResPresenter());

		getView().setUiHandlers(this);
		addRegisteredHandler(ContentPushEvent.TYPE, this);
	}

	@Override
	public void onBind() {
		super.onBind();
		logger.log(Level.INFO, "onBind()");
		getView().buildMenu();
	}

	@Override
	public void onReveal() {
		super.onReveal();
		logger.log(Level.INFO, "onReveal()");
		SetPageTitleEvent.fire("R-No: 465465 / Szoba#: 205", "Mr. John Smith", MenuItemType.MENU_ITEM, this);
		showTable(1);
	}

	@Override
	public Map<Integer, AbstractResWidget<?>> getWidgetsMap() {
		return widgetsMap;
	}

	@Override
	public void showTable(Integer index) {
		activeWidget = index;
		getView().setDesktopMenu(index);
		setInSlot(SLOT_CONTENT, widgetsMap.get(index));
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		// TODO Auto-generated method stub

	}
}
