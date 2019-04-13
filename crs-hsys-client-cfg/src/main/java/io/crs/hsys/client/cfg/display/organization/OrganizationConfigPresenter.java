/**
 * 
 */
package io.crs.hsys.client.cfg.display.organization;

import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import io.crs.hsys.client.cfg.browser.quotation.QuotationBrowserFactory;
import io.crs.hsys.client.cfg.editor.profile.organization.OrganizationEditorFactory;
import io.crs.hsys.client.cfg.i18n.CfgMessages;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.config.AbstractConfigPresenter;
import io.crs.hsys.client.core.editor.AbstractDisplayPresenterWidget;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.LoggedInGatekeeper;
import io.crs.hsys.shared.api.OrganizationResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.dto.profile.OrganizationDto;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;;

/**
 * @author robi
 *
 */
public class OrganizationConfigPresenter
		extends AbstractConfigPresenter<OrganizationConfigPresenter.MyView, OrganizationConfigPresenter.MyProxy>
		implements OrganizationConfigUiHandlers {
	private static Logger logger = Logger.getLogger(OrganizationConfigPresenter.class.getName());

	public static final String GENERAL_DATA = "generalData";
	public static final String QUOTATIONS = "quotattions";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.ORGANIZATION_DISPLAY)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<OrganizationConfigPresenter> {
	}

	private String webSafeKey;
	private String title;
	private String description;
	private final ResourceDelegate<OrganizationResource> resourceDelegate;

	@Inject
	OrganizationConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<OrganizationResource> resourceDelegate,
			OrganizationEditorFactory organizationEditorFactory, QuotationBrowserFactory quotationsFactory,
			CfgMessages i18n, CoreMessages i18nCore) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("OrganizationConfigPresenter()");
		this.resourceDelegate = resourceDelegate;

		setCaption(i18nCore.organizationConfigTitle());
		setDescription(i18nCore.organizationConfigDescription());
		setPlaceToken(CoreNameTokens.ORGANIZATION_DISPLAY);

		addContent(i18nCore.organizationEditorDescription(), organizationEditorFactory.createOrganizationEditor(),
				GENERAL_DATA);
		addContent(i18nCore.organizationEditorQuotations(), quotationsFactory.createQuotationBrowser(), QUOTATIONS);

		getView().setUiHandlers(this);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		webSafeKey = request.getParameter(WEBSAFEKEY, null);
		logger.info("OrganizationConfigPresenter().prepareFromRequest()->webSafeKey=" + webSafeKey);

		Integer index = placeParams.indexOf(request.getParameter(PLACE_PARAM, null));
		if (index == -1)
			index = 0;
		getView().setDesktopMenu(index);
		getView().setMobileButtonText(captions.get(index));
		setInSlot(SLOT_CONTENT, beforeShowContent(browsers.get(index)));
	}

	@Override
	protected PresenterWidget<?> beforeShowContent(PresenterWidget<?> widget) {
		logger.info("OrganizationConfigPresenter().beforeShowContent()");
		((AbstractDisplayPresenterWidget<?>) widget).setReadOnly(true);
		((AbstractDisplayPresenterWidget<?>) widget).setWebSafeKey(webSafeKey);
		((AbstractDisplayPresenterWidget<?>) widget).setTitle(title);
		((AbstractDisplayPresenterWidget<?>) widget).setDescription(description);
		return widget;
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		logger.info("OrganizationConfigPresenter().onReveal()");
		loadOrganizationData();
	}

	private void loadOrganizationData() {
		logger.info("OrganizationConfigPresenter().loadOrganizationData()->webSafeKey=" + webSafeKey);
		resourceDelegate.withCallback(new AsyncCallback<OrganizationDto>() {
			@Override
			public void onSuccess(OrganizationDto dto) {
				logger.info("OrganizationConfigPresenter().loadOrganizationData().onSuccess()");
				title = dto.getCode();
				description = dto.getName();
				SetPageTitleEvent.fire(title, description, MenuItemType.MENU_ITEM, OrganizationConfigPresenter.this);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		}).read(webSafeKey);
	}

	@Override
	protected void buildPlaceRequest(PlaceRequest.Builder builder) {
		if (webSafeKey != null)
			builder.with("webSafeKey", webSafeKey);
	}

}
