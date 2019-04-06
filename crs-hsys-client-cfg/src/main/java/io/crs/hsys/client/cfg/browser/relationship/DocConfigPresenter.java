/**
 * 
 */
package io.crs.hsys.client.cfg.browser.relationship;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.cfg.browser.profilegroup.ProfileGroupBrowserFactory;
import io.crs.hsys.client.cfg.config.profile.ProfileConfigUiHandlers;
import io.crs.hsys.client.cfg.i18n.CfgMessages;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.LoggedInGatekeeper;
import io.crs.hsys.client.core.ui.config.AbstractConfigPresenter;

/**
 * @author robi
 *
 */
public class DocConfigPresenter extends AbstractConfigPresenter<DocConfigPresenter.MyView, DocConfigPresenter.MyProxy>
		implements ProfileConfigUiHandlers {
	private static Logger logger = Logger.getLogger(DocConfigPresenter.class.getName());

	public static final String QUOTATION_STATUS = "quotationStatus";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.DOC_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<DocConfigPresenter> {
	}

	@Inject
	DocConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ProfileGroupBrowserFactory profileGroupFactory, CfgMessages i18n,
			CoreMessages i18nCore) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);

		logger.info("DocConfigPresenter()");

		setCaption(i18nCore.profileConfigTitle());
		setDescription(i18nCore.profileConfigDescription());
		setPlaceToken(CoreNameTokens.PROFILE_CONFIG);

		addContent(i18nCore.profileGroupBrowserTitle(), profileGroupFactory.createProfileGroupBrowser(),
				QUOTATION_STATUS);

		getView().setUiHandlers(this);
	}

	@Override
	protected PresenterWidget<?> beforeShowContent(PresenterWidget<?> widget) {
		AbstractBrowserPresenter<?, ?> bp = (AbstractBrowserPresenter<?, ?>) widget;
		bp.refresh();
		return widget;
	}

}
