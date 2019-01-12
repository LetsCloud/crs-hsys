/**
 * 
 */
package io.crs.hsys.client.fro.dashboard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import io.crs.hsys.client.fro.NameTokens;
import io.crs.hsys.shared.constans.MenuItemType;
import io.crs.hsys.shared.dto.reservation.ReservationSearchDto;

/**
 * @author CR
 *
 */
public class DashboardPresenter extends Presenter<DashboardPresenter.MyView, DashboardPresenter.MyProxy>
		implements DashboardUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(DashboardPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<DashboardUiHandlers> {
		void showCards();

		void setReservationSearchData(List<ReservationSearchDto> data);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.HOME)
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
		SetPageTitleEvent.fire("Dashboard", "", MenuItemType.MENU_ITEM, this);
		getView().showCards();
		loadData();
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		// TODO Auto-generated method stub

	}

	private void loadData() {
		List<ReservationSearchDto> data = new ArrayList<ReservationSearchDto>();

		data.add(ReservationSearchDto.builder().webSafeKey("1").id(1l).name("Siemens csoport").arrival(new Date())
				.departure(new Date()).numOfRooms(10).numOfGuests(20).customer("Siemens").build());
		data.add(ReservationSearchDto.builder().webSafeKey("2").id(2l).name("Telecom csoport").arrival(new Date())
				.departure(new Date()).numOfRooms(10).numOfGuests(20).customer("Telecom").build());
		
		getView().setReservationSearchData(data);
	}
}
