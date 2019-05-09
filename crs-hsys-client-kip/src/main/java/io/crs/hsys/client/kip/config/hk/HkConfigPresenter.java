package io.crs.hsys.client.kip.config.hk;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.config.AbstractConfigPresenter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.browser.taskgroup.TaskGroupBrowserFactory;
import io.crs.hsys.client.kip.browser.tasktodo.TaskTodoBrowserFactory;
import io.crs.hsys.client.kip.browser.tasktype.TaskTypeBrowserFactory;
import io.crs.hsys.client.kip.i18n.KipMessages;

public class HkConfigPresenter extends AbstractConfigPresenter<HkConfigPresenter.MyView, HkConfigPresenter.MyProxy>
		implements HkConfigUiHandlers {
	private static Logger logger = Logger.getLogger(HkConfigPresenter.class.getName());

	public static final String TASK_GROUPS = "taskGroups";
	public static final String TASK_TODOS = "taskTodos";
	public static final String TASK_TYPES = "taskTypes";
	public static final String STANDARD_CLEANING_TYPES = "standardCleaningTypes";
	public static final String PUBLIC_AREA_CLEANING_PARAMS = "publicAreaCleaningParams";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(KipNameTokens.HOUSEKEEPING_CONFIG)
//	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<HkConfigPresenter> {
	}

	@Inject
	HkConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			TaskGroupBrowserFactory hkTaskGroupBrowserFactory, TaskTodoBrowserFactory hkTaskTodoBrowserFactory,
			TaskTypeBrowserFactory taskTypeBrowserFactory, CoreMessages i18nCore, KipMessages i18n) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("HkConfigPresenter()");

		setCaption(i18n.housekeepingConfigTitle());
		setDescription(i18n.housekeepingConfigDescription());
		setPlaceToken(KipNameTokens.HOUSEKEEPING_CONFIG);

		addContent(i18n.hkTaskGroupBrowserTitle(), hkTaskGroupBrowserFactory.createHkTaskGroupBrowser(), TASK_GROUPS);
		addContent(i18n.hkTaskTodoBrowserTitle(), hkTaskTodoBrowserFactory.createHkTaskTodoBrowser(), TASK_TODOS);
		addContent(i18n.hkTaskTypeBrowserTitle(), taskTypeBrowserFactory.createHkTaskTypeBrowser(), TASK_TYPES);
//		addContent(i18n.hkConfigStandardTypesItem(), taskTypeBrowserFactory.createHkTaskTypeBrowser(), STANDARD_CLEANING_TYPES);
//		addContent(i18n.hkConfigPublicAreaCleaningSetup(), taskTypeBrowserFactory.createHkTaskTypeBrowser(), PUBLIC_AREA_CLEANING_PARAMS);

		getView().setUiHandlers(this);
	}
}
