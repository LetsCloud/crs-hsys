/**
 * 
 */
package io.crs.hsys.client.cfg.page.organizations;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import gwt.material.design.client.constants.IconType;
import io.crs.hsys.client.cfg.browser.organization.OrganizationBrowserFactory;
import io.crs.hsys.client.cfg.browser.organization.OrganizationBrowserPresenter;
import io.crs.hsys.client.cfg.config.profile.ProfileConfigPresenter;
import io.crs.hsys.client.cfg.i18n.CfgMessages;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.config.AbstractConfigPresenter;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.model.BreadcrumbConfig;
import io.crs.hsys.client.core.security.LoggedInGatekeeper;
import io.crs.hsys.shared.cnst.MenuItemType;

/**
 * @author robi
 *
 */
public class OrganizationsPagePresenter
		extends Presenter<OrganizationsPagePresenter.MyView, OrganizationsPagePresenter.MyProxy>
		implements OrganizationsPageUiHandlers {
	private static Logger logger = Logger.getLogger(OrganizationsPagePresenter.class.getName());

	public static final SingleSlot<PresenterWidget<?>> SLOT_CONTENT = new SingleSlot<>();

	interface MyView extends View, HasUiHandlers<OrganizationsPageUiHandlers> {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.ORGANIZATIONS)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<OrganizationsPagePresenter> {
	}

	private final OrganizationBrowserPresenter organizationBrowser;
	private final CfgMessages i18n;

	@Inject
	OrganizationsPagePresenter(EventBus eventBus, MyView view, MyProxy proxy,
			OrganizationBrowserFactory organizationFactory, CfgMessages i18n) {
		super(eventBus, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("OrganizationsPagePresenter()");
		this.organizationBrowser = organizationFactory.createOrganisationBrowser();
		this.i18n = i18n;

		organizationBrowser.setBreadcrumbConfig(new BreadcrumbConfig(1, IconType.VIEW_LIST,
				i18n.menuItemOrganizations(), CoreNameTokens.ORGANIZATIONS));

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_CONTENT, organizationBrowser);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(i18n.menuItemOrganizations(), "", MenuItemType.MENU_ITEM,
				OrganizationsPagePresenter.this);
	}
}
