/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.filter;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.security.LoggedInGatekeeper;
import io.crs.hsys.shared.dto.filter.RoomStatusFilterDto;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.roomstatus.RoomStatusPresenter;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusFilterEvent;

/**
 * @author CR
 *
 */
public class RoomStatusFilterPresenter
		extends Presenter<RoomStatusFilterPresenter.MyView, RoomStatusFilterPresenter.MyProxy>
		implements RoomStatusFilterUiHandlers {
	private static final Logger LOGGER = Logger.getLogger(RoomStatusFilterPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<RoomStatusFilterUiHandlers> {
		void createFilter();
	}

	@ProxyStandard
	@NameToken(KipNameTokens.HK_CHANGE_STATUS)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<RoomStatusFilterPresenter> {
	}

	@Inject
	RoomStatusFilterPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RoomStatusPresenter.SLOT_FILTER);
		LOGGER.log(Level.INFO, "RoomStatusFilterPresenter()");
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		LOGGER.log(Level.INFO, "onBind()");
	}

	@Override
	protected void onReveal() {
		LOGGER.log(Level.INFO, "onReveal()");
		getView().createFilter();
	}

	@Override
	public void doFilter(RoomStatusFilterDto filterDto) {
		LOGGER.log(Level.INFO, "doFilter()");
		RoomStatusFilterEvent.fire(RoomStatusFilterPresenter.this, filterDto);
	}

}
