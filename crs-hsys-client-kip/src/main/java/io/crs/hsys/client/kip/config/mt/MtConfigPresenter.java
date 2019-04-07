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
import io.crs.hsys.client.core.config.AbstractConfigPresenter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.browser.taskgroup.TaskGroupBrowserFactory;
import io.crs.hsys.client.kip.browser.tasktodo.TaskTodoBrowserFactory;
import io.crs.hsys.client.kip.browser.tasktype.TaskTypeBrowserFactory;
import io.crs.hsys.client.kip.i18n.KipMessages;

/**
 * @author robi
 *
 */
public class MtConfigPresenter extends AbstractConfigPresenter<MtConfigPresenter.MyView, MtConfigPresenter.MyProxy>
		implements MtConfigUiHandlers {
	private static Logger logger = Logger.getLogger(MtConfigPresenter.class.getName());

	public static final String TASK_GROUPS = "taskGroups";
	public static final String TASK_TODOS = "taskTodos";
	public static final String TASK_TYPES = "taskTypes";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(KipNameTokens.MAINTENANCE_CONFIG)
//@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<MtConfigPresenter> {
	}

	@Inject
	MtConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			TaskGroupBrowserFactory taskGroupBrowserFactory, TaskTodoBrowserFactory taskTodoBrowserFactory,
			TaskTypeBrowserFactory taskTypeBrowserFactory, CoreMessages i18nCore, KipMessages i18n) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("MtConfigPresenter()");

		setCaption(i18n.mtConfigTitle());
		setDescription(i18n.mtConfigDescription());
		setPlaceToken(KipNameTokens.MAINTENANCE_CONFIG);

		addContent(i18n.mtConfigTaskGroupItem(), taskGroupBrowserFactory.createMtTaskGroupBrowser(), TASK_GROUPS);
		addContent(i18n.mtConfigTaskTodoItem(), taskTodoBrowserFactory.createMtTaskTodoBrowser(), TASK_TODOS);
		addContent(i18n.mtConfigTaskTypeItem(), taskTypeBrowserFactory.createMtTaskTypeBrowser(), TASK_TYPES);

		getView().setUiHandlers(this);
	}
}
