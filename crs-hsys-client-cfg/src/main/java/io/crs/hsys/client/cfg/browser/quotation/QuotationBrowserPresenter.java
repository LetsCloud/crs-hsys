/**
 * 
 */
package io.crs.hsys.client.cfg.browser.quotation;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import gwt.material.design.client.constants.IconType;
import io.crs.hsys.client.cfg.display.organization.OrganizationConfigPresenter;
import io.crs.hsys.client.cfg.editor.quotation.QuotationEditorPresenter;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.config.AbstractConfigPresenter;
import io.crs.hsys.client.core.event.SetBreadcrumbsEvent;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.event.RefreshTableEvent.TableType;
import io.crs.hsys.client.core.filter.FilterPresenterFactory;
import io.crs.hsys.client.core.filter.profile.ProfileFilterPresenter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.message.callback.AbstractAsyncCallback;
import io.crs.hsys.client.core.model.BreadcrumbConfig;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.shared.api.ApiParameters;
import io.crs.hsys.shared.api.QuotationResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.dto.doc.QuotationDto;

/**
 * @author robi
 *
 */
public class QuotationBrowserPresenter extends AbstractBrowserPresenter<QuotationDto, QuotationBrowserPresenter.MyView>
		implements QuotationBrowserUiHandlers, FilterChangeEvent.FilterChangeHandler {
	private static Logger logger = Logger.getLogger(QuotationBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<QuotationBrowserUiHandlers> {
		void setData(List<QuotationDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<QuotationResource> resourceDelegate;
	private final ProfileFilterPresenter filter;
	private final CoreMessages i18nCore;

	@Inject
	QuotationBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<QuotationResource> resourceDelegate, CurrentUser currentUser,
			FilterPresenterFactory filterFactory, CoreMessages i18nCore) {
		super(eventBus, view, placeManager);
		logger.info("QuotationBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterFactory.createProfileFilterPresenter();
		this.i18nCore = i18nCore;

		addVisibleHandler(FilterChangeEvent.TYPE, this);

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_FILTER, filter);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
	}

	private String createTargetHistory(String webSafeKey) {
		return CoreNameTokens.ORGANIZATION_DISPLAY + "?" + ApiParameters.WEBSAFEKEY + "=" + webSafeKey + "&"
				+ AbstractConfigPresenter.PLACE_PARAM + "=" + OrganizationConfigPresenter.QUOTATIONS;
	}

	private BreadcrumbConfig createBreadcrumbConfig(String targetHistory) {
		return new BreadcrumbConfig(3, IconType.VIEW_LIST, i18nCore.quotationBrowserTitle(), targetHistory);
	}

	@Override
	protected void loadData() {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<QuotationDto>>() {
			@Override
			public void onSuccess(List<QuotationDto> result) {
				SetPageTitleEvent.fire(getTitle(), getDescription(), MenuItemType.MENU_ITEM,
						QuotationBrowserPresenter.this);
				SetBreadcrumbsEvent.fire(createBreadcrumbConfig(createTargetHistory(getWebSafeKey())),
						QuotationBrowserPresenter.this);
				/*
				 * if ((filter.getCode() != null) && (!filter.getCode().isEmpty())) result =
				 * result.stream().filter(org -> org.getCode().contains(filter.getCode()))
				 * .collect(Collectors.toList()); if ((filter.getName() != null) &&
				 * (!filter.getName().isEmpty())) result = result.stream().filter(org ->
				 * org.getName().contains(filter.getName())) .collect(Collectors.toList()); if
				 * (!filter.getProfileGroupKeys().isEmpty()) result = result.stream()
				 * .filter(org ->
				 * filter.getProfileGroupKeys().contains(org.getProfileGroup().getWebSafeKey()))
				 * .collect(Collectors.toList());
				 */
				getView().setData(result);
			}
		}).getAll(getWebSafeKey());
	}

	@Override
	protected String getCreatorNameToken() {
		logger.info("QuotationBrowserPresenter().getCreatorNameToken()");
		return CoreNameTokens.QUOTATION_CREATOR;
	}

	@Override
	protected Builder configCreatePlaceBuilder(Builder placeBuilder) {
		if (getFilter(WEBSAFEKEY) == null)
			return placeBuilder;
		placeBuilder.with(QuotationEditorPresenter.ORGANIZATION_KEY, String.valueOf(getFilter(WEBSAFEKEY)));
		return placeBuilder;
	}

	@Override
	protected String getEditorNameToken() {
		logger.info("QuotationBrowserPresenter().getEditorNameToken()");
		return CoreNameTokens.QUOTATION_CREATOR;
	}

	@Override
	protected Builder configEditPlaceBuilder(Builder placeBuilder, QuotationDto dto) {
		if (dto.getOrganization() == null)
			return placeBuilder;

		placeBuilder.with(QuotationEditorPresenter.ORGANIZATION_KEY,
				String.valueOf(dto.getOrganization().getWebSafeKey()));
		return placeBuilder;
	}

	@Override
	protected void deleteData(String webSafeKey) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				loadData();
			}
		}).delete(webSafeKey);
	}

	@Override
	public void onFilterChange(FilterChangeEvent event) {
		loadData();
	}

	@Override
	protected TableType getTableType() {
		return TableType.QUOTATION;
	}
}