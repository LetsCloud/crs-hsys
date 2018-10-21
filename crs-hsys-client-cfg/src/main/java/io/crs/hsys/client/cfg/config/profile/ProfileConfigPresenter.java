/**
 * 
 */
package io.crs.hsys.client.cfg.config.profile;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.cfg.browser.contact.ContactBrowserFactory;
import io.crs.hsys.client.cfg.browser.organization.OrganizationBrowserFactory;
import io.crs.hsys.client.cfg.browser.profilegroup.ProfileGroupBrowserFactory;
import io.crs.hsys.client.cfg.browser.relationship.RelationshipBrowserFactory;
import io.crs.hsys.client.cfg.config.AbstractConfigPresenter;
import io.crs.hsys.client.cfg.i18n.FroMessages;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.LoggedInGatekeeper;

/**
 * @author robi
 *
 */
public class ProfileConfigPresenter
		extends AbstractConfigPresenter<ProfileConfigPresenter.MyView, ProfileConfigPresenter.MyProxy>
		implements ProfileConfigUiHandlers {
	private static Logger logger = Logger.getLogger(ProfileConfigPresenter.class.getName());

	public static final String PROFILE_GROUPS = "profileGroups";
	public static final String RELATIONSHIPS = "relationships";
	public static final String ORGANIZATIONS = "organizations";
	public static final String CONTACTS = "contacts";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.PROFILE_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<ProfileConfigPresenter> {
	}

	@Inject
	ProfileConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ProfileGroupBrowserFactory profileGroupFactory, RelationshipBrowserFactory relationshipFactory,
			OrganizationBrowserFactory organizationFactory, ContactBrowserFactory contactFactory, FroMessages i18n,
			CoreMessages i18nCore) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);

		logger.info("ProfileConfigPresenter()");

		setCaption(i18nCore.profileConfigTitle());
		setDescription(i18nCore.profileConfigDescription());
		setPlaceToken(CoreNameTokens.PROFILE_CONFIG);

		addContent(i18nCore.profileGroupBrowserTitle(), profileGroupFactory.createProfileGroupBrowser(),
				PROFILE_GROUPS);
		addContent("Kapcsolat típusok", relationshipFactory.createRelationshipBrowser(), RELATIONSHIPS);
		addContent(i18nCore.organizationBrowserTitle(), organizationFactory.createOrganisationBrowser(), ORGANIZATIONS);
		addContent(i18nCore.contactBrowserTitle(), contactFactory.createContactBrowser(), CONTACTS);

		getView().setUiHandlers(this);
	}

}
