/**
 * 
 */
package io.crs.hsys.client.cfg.display.organization;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import io.crs.hsys.client.cfg.browser.organization.OrganizationBrowserFactory;
import io.crs.hsys.client.cfg.editor.profile.organization.OrganizationEditorFactory;
import io.crs.hsys.client.cfg.i18n.CfgMessages;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.editor.AbstractDisplayPresenterWidget;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.LoggedInGatekeeper;
import io.crs.hsys.client.core.ui.config.AbstractConfigPresenter;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;;

/**
 * @author robi
 *
 */
public class OrganizationConfigPresenter
		extends AbstractConfigPresenter<OrganizationConfigPresenter.MyView, OrganizationConfigPresenter.MyProxy>
		implements OrganizationConfigUiHandlers {
	private static Logger logger = Logger.getLogger(OrganizationConfigPresenter.class.getName());

	private static final String GENERAL_DATA = "generalData";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.ORGANIZATION_DISPLAY)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<OrganizationConfigPresenter> {
	}

	private String webSafeKey;

	@Inject
	OrganizationConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			OrganizationEditorFactory organizationEditorFactory, OrganizationBrowserFactory customerFactory,
			CfgMessages i18n, CoreMessages i18nCore) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("OrganizationConfigPresenter()");
		setCaption(i18nCore.organizationConfigTitle());
		setDescription(i18nCore.organizationConfigDescription());
		setPlaceToken(CoreNameTokens.ORGANIZATION_DISPLAY);

		addContent(i18nCore.organizationEditorDescription(), organizationEditorFactory.createOrganizationEditor(), GENERAL_DATA);
//		addContent(i18nCore.customerBrowserTitle(), customerFactory.createCustomerBrowser());

		getView().setUiHandlers(this);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		webSafeKey = request.getParameter(WEBSAFEKEY, null);
		logger.info("OrganizationConfigPresenter().prepareFromRequest()->webSafeKey=" + webSafeKey);
		
		Integer index = placeParams.indexOf(request.getParameter(PLACE_PARAM, null));
		if (index == -1) index = 0;
		getView().setDesktopMenu(index);
		getView().setMobileButtonText(captions.get(index));
		setInSlot(SLOT_CONTENT, beforeShowContent(browsers.get(index)));
	}

	@Override
	protected PresenterWidget<?> beforeShowContent(PresenterWidget<?> widget) {
		logger.info("OrganizationConfigPresenter().beforeShowContent()");
		((AbstractDisplayPresenterWidget<?>) widget).setReadOnly(true);
		((AbstractDisplayPresenterWidget<?>) widget).setWebSafeKey(webSafeKey);
		return widget;
	}

}
