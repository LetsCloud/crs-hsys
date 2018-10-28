/**
 * 
 */
package io.crs.hsys.client.admin.config.system;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.admin.NameTokens;
import io.crs.hsys.client.admin.browser.globalconfig.GlobalConfigBrowserFactory;
import io.crs.hsys.client.admin.i18n.AdminMessages;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.ui.config.AbstractConfigPresenter;

/**
 * @author robi
 *
 */
public class SystemConfigPresenter
		extends AbstractConfigPresenter<SystemConfigPresenter.MyView, SystemConfigPresenter.MyProxy>
		implements SystemConfigUiHandlers {
	private static Logger logger = Logger.getLogger(SystemConfigPresenter.class.getName());

	public static final String GLOBAL_CONFIGS = "globalConfigs";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.SYSTEM_CONFIG)
	interface MyProxy extends ProxyPlace<SystemConfigPresenter> {
	}

	@Inject
	SystemConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			GlobalConfigBrowserFactory globalConfigBrowserFactory, AdminMessages i18n) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("SystemConfigPresenter()");

		setCaption(i18n.systemConfigTitle());
		setDescription(i18n.systemConfigDescription());
		setPlaceToken(NameTokens.SYSTEM_CONFIG);

		addContent(i18n.globalConfigBrowserTitle(), globalConfigBrowserFactory.createFirebaseBrowser(), GLOBAL_CONFIGS);

		getView().setUiHandlers(this);
	}
}
