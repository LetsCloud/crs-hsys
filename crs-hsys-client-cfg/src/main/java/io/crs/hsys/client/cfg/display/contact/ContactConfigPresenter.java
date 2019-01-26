/**
 * 
 */
package io.crs.hsys.client.cfg.display.contact;

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

import io.crs.hsys.client.cfg.editor.AbstractDisplayPresenterWidget;
import io.crs.hsys.client.cfg.editor.profile.contact.ContactEditorFactory;
import io.crs.hsys.client.cfg.i18n.CfgMessages;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.config.AbstractConfigPresenter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.LoggedInGatekeeper;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

/**
 * @author robi
 *
 */
public class ContactConfigPresenter
		extends AbstractConfigPresenter<ContactConfigPresenter.MyView, ContactConfigPresenter.MyProxy>
		implements ContactConfigUiHandlers {
	private static Logger logger = Logger.getLogger(ContactConfigPresenter.class.getName());

	private static final String GENERAL_DATA = "generalData";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.CONTACT_DISPLAY)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<ContactConfigPresenter> {
	}

	private String webSafeKey;

	@Inject
	ContactConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ContactEditorFactory contactEditorFactory, CfgMessages i18n, CoreMessages i18nCore) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("ContactConfigPresenter()");

		setCaption(i18nCore.contactDisplayTitle());
		setDescription(i18nCore.contactDisplayDescription());
		setPlaceToken(CoreNameTokens.CONTACT_DISPLAY);

		addContent(i18nCore.contactEditorDescription(), contactEditorFactory.createContactEditor(), GENERAL_DATA);

		getView().setUiHandlers(this);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		webSafeKey = request.getParameter(WEBSAFEKEY, null);
		
		Integer index = placeParams.indexOf(request.getParameter(PLACE_PARAM, null));
		if (index == -1) index = 0;
		getView().setDesktopMenu(index);
		getView().setMobileButtonText(captions.get(index));
		setInSlot(SLOT_CONTENT, beforeShowContent(browsers.get(index)));
	}

	@Override
	protected PresenterWidget<?> beforeShowContent(PresenterWidget<?> widget) {
		((AbstractDisplayPresenterWidget<?>) widget).setReadOnly(true);
		((AbstractDisplayPresenterWidget<?>) widget).setWebSafeKey(webSafeKey);
		return widget;
	}

}
