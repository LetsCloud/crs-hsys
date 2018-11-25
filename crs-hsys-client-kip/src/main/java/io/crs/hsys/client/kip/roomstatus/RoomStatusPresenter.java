/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusFilterEvent;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusFilterEvent.RoomStatusFilterHandler;
import io.crs.hsys.client.kip.roomstatus.filter.RoomStatusFilterPresenter;
import io.crs.hsys.client.kip.roomstatus.list.RoomStatusListPresenter;
import io.crs.hsys.shared.constans.MenuItemType;

/**
 * @author CR
 *
 */
public class RoomStatusPresenter extends Presenter<RoomStatusPresenter.MyView, RoomStatusPresenter.MyProxy>
		implements RoomStatusUiHandlers, RoomStatusFilterHandler {
	private static final Logger logger = Logger.getLogger(RoomStatusPresenter.class.getName());

	public static final NestedSlot SLOT_LIST = new NestedSlot();
	public static final NestedSlot SLOT_FILTER = new NestedSlot();

	interface MyView extends View, HasUiHandlers<RoomStatusUiHandlers> {
		void doFilter(RoomStatusFilterEvent event);
	}

	@ProxyStandard
	@NameToken(KipNameTokens.GUEST_ROOMS)
	interface MyProxy extends ProxyPlace<RoomStatusPresenter> {
	}

	private RoomStatusListPresenter listPresenter;
	private RoomStatusFilterPresenter filterPresenter;

	@Inject
	RoomStatusPresenter(EventBus eventBus, MyView view, MyProxy proxy, RoomStatusListPresenter listPresenter,
			RoomStatusFilterPresenter filterPresenter) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "RoomStatusPresenter()");

//		this.listPresenter = listPresenter;
//		this.filterPresenter = filterPresenter;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		logger.log(Level.INFO, "onBind()");
	}

	@Override
	protected void onReveal() {
		logger.log(Level.INFO, "onReveal()");
		SetPageTitleEvent.fire("Vendégszobák", "A gondnoknők kedvence...", MenuItemType.MENU_ITEM, this);
//		listPresenter.forceReveal();
//		filterPresenter.forceReveal();
	}

	@ProxyEvent
	@Override
	public void onFilter(RoomStatusFilterEvent event) {
		logger.log(Level.INFO, "onFilter()");
		getView().doFilter(event);
	}
}
