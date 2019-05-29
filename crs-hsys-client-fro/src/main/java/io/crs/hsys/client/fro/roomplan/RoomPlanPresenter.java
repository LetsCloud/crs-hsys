/**
 * 
 */
package io.crs.hsys.client.fro.roomplan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.Window;
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
import io.crs.hsys.client.fro.roomplan.model.HeaderData;
import io.crs.hsys.shared.cnst.MenuItemType;

/**
 * @author CR
 *
 */
public class RoomPlanPresenter extends Presenter<RoomPlanPresenter.MyView, RoomPlanPresenter.MyProxy>
		implements RoomPlanUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(RoomPlanPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<RoomPlanUiHandlers> {
		void showPage();
		void setData(List<HeaderData> data);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.ROOM_PLAN)
// @UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<RoomPlanPresenter> {
	}

	@Inject
	RoomPlanPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "RoomPlanPresenter()");

		addRegisteredHandler(ContentPushEvent.TYPE, this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire("Roomplan", "", MenuItemType.MENU_ITEM, this);
		loadData();
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
	}
	
	private void loadData() {
		List<HeaderData> data = new ArrayList<HeaderData>();
		int clientWidth = Window.getClientWidth();
		int numOfDays = clientWidth / 100;
		for (int i = 0; i < numOfDays; i++) {
			data.add(new HeaderData(i, new Date(), 0f));
		}
		getView().setData(data);
	}
}
