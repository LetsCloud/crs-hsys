/**
 * 
 */
package io.crs.hsys.client.cfg.creator.organization;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import io.crs.hsys.client.cfg.editor.profile.organization.OrganizationEditorFactory;
import io.crs.hsys.client.cfg.editor.profile.organization.OrganizationEditorPresenter;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.message.callback.ErrorHandlerAsyncCallback;
import io.crs.hsys.shared.api.OrganizationResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.dto.profile.OrganizationDto;

/**
 * @author robi
 *
 */
public class OrganizationCreatePresenter
		extends Presenter<OrganizationCreatePresenter.MyView, OrganizationCreatePresenter.MyProxy>
		implements OrganizationCreateUiHandlers {
	private static Logger logger = Logger.getLogger(OrganizationCreatePresenter.class.getName());

	interface MyView extends View, HasUiHandlers<OrganizationCreateUiHandlers> {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.ORGANIZATION_CREATOR)
	interface MyProxy extends ProxyPlace<OrganizationCreatePresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_CONTENT = new SingleSlot<>();

	private final PlaceManager placeManager;

	private final ResourceDelegate<OrganizationResource> resourceDelegate;

	private final OrganizationEditorPresenter editor;

	private final CoreMessages i18nCore;

	@Inject
	OrganizationCreatePresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<OrganizationResource> resourceDelegate, OrganizationEditorFactory editorFactory,
			CoreMessages i18n) {
		super(eventBus, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("CustomerCreatePresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.editor = editorFactory.createOrganizationEditor();
		this.i18nCore = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		editor.setReadOnly(false);
		setInSlot(SLOT_CONTENT, editor);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(i18nCore.organizationCreatorTitle(), i18nCore.organizationCreatorDescription(),
				MenuItemType.MENU_ITEM, this);
	}

	@Override
	public void save(OrganizationDto dto) {
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<OrganizationDto>(this, i18nCore) {
			@Override
			public void onSuccess(OrganizationDto dto) {
				PlaceRequest placeRequest = new Builder().nameToken(CoreNameTokens.SYSTEM_CONFIG).build();
				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
//				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub

	}
}