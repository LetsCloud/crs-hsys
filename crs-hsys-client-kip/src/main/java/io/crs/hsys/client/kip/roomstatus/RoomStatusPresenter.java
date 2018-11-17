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
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.security.LoggedInGatekeeper;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusFilterEvent;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusFilterEvent.RoomStatusFilterHandler;
import io.crs.hsys.client.kip.roomstatus.filter.RoomStatusFilterPresenter;
import io.crs.hsys.client.kip.roomstatus.list.RoomStatusListPresenter;

/**
 * @author CR
 *
 */
public class RoomStatusPresenter extends Presenter<RoomStatusPresenter.MyView, RoomStatusPresenter.MyProxy>
		implements RoomStatusUiHandlers, RoomStatusFilterHandler {
	private static final Logger LOGGER = Logger.getLogger(RoomStatusPresenter.class.getName());

	public static final NestedSlot SLOT_LIST = new NestedSlot();
	public static final NestedSlot SLOT_FILTER = new NestedSlot();

	interface MyView extends View, HasUiHandlers<RoomStatusUiHandlers> {
		void doFilter(RoomStatusFilterEvent event);
	}

	@ProxyStandard
	@NameToken(KipNameTokens.HK_CHANGE_STATUS)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<RoomStatusPresenter> {
	}

	private RoomStatusListPresenter listPresenter;
	private RoomStatusFilterPresenter filterPresenter;

	@Inject
	RoomStatusPresenter(EventBus eventBus, MyView view, MyProxy proxy, RoomStatusListPresenter listPresenter,
			RoomStatusFilterPresenter filterPresenter) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		LOGGER.log(Level.INFO, "RoomStatusPresenter()");

		this.listPresenter = listPresenter;
		this.filterPresenter = filterPresenter;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		LOGGER.log(Level.INFO, "onBind()");
	}

	@Override
	protected void onReveal() {
		LOGGER.log(Level.INFO, "onReveal()");
		listPresenter.forceReveal();
		filterPresenter.forceReveal();
	}

	@ProxyEvent
	@Override
	public void onFilter(RoomStatusFilterEvent event) {
		LOGGER.log(Level.INFO, "onFilter()");
		getView().doFilter(event);
	}
}
