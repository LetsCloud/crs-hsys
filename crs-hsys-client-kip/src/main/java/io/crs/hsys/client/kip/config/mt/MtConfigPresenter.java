/**
 * 
 */
package io.crs.hsys.client.kip.config.mt;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.browser.room.RoomBrowserFactory;
import io.crs.hsys.client.core.browser.roomtype.RoomTypeBrowserFactory;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.config.AbstractConfigPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.browser.taskgroup.TaskGroupBrowserFactory;
import io.crs.hsys.client.kip.i18n.KipMessages;

/**
 * @author robi
 *
 */
public class MtConfigPresenter extends AbstractConfigPresenter<MtConfigPresenter.MyView, MtConfigPresenter.MyProxy>
		implements MtConfigUiHandlers {
	private static Logger logger = Logger.getLogger(MtConfigPresenter.class.getName());

	public static final String TASK_GROUPS = "taskGroups";
	public static final String ROOM_TYPES = "roomTypes";
	public static final String ROOMS = "rooms";
	public static final String MARKET_GROUPS = "marketGroups";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(KipNameTokens.MAINTENANCE_CONFIG)
//@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<MtConfigPresenter> {
	}

	@Inject
	MtConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			TaskGroupBrowserFactory taskGroupBrowserFactory, RoomTypeBrowserFactory roomTypeBrowserFactory,
			RoomBrowserFactory roomBrowserFactory, CoreMessages i18nCore, KipMessages i18n) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("MtConfigPresenter()");

		setCaption(i18n.mtConfigTitle());
		setDescription(i18n.mtConfigDescription());
		setPlaceToken(KipNameTokens.MAINTENANCE_CONFIG);

		addContent(i18n.mtTaskGroupBrowserTitle(), taskGroupBrowserFactory.createMtTaskGroupBrowser(), TASK_GROUPS);
		addContent(i18nCore.roomTypeBrowserTitle(), roomTypeBrowserFactory.createRoomTypeTablePresenter(), ROOM_TYPES);
		addContent(i18nCore.roomBrowserTitle(), roomBrowserFactory.createRoomTablePresenter(), ROOMS);

		getView().setUiHandlers(this);
	}
}
