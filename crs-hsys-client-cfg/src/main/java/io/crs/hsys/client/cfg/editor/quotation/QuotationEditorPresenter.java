/**
 * 
 */
package io.crs.hsys.client.cfg.editor.quotation;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import io.crs.hsys.client.cfg.CfgNameTokens;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.datasource.OrganizationDataSource2;
import io.crs.hsys.client.core.datasource.QuotationStatusDataSource;
import io.crs.hsys.client.core.editor.AbstractEditorPresenter;
import io.crs.hsys.client.core.editor.AbstractEditorView;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.ApiParameters;
import io.crs.hsys.shared.api.QuotationResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.dto.doc.QuotationDto;
import io.crs.hsys.shared.dto.doc.QuotationStatusDto;
import io.crs.hsys.shared.dto.profile.OrganizationDtor;

/**
 * @author robi
 *
 */
public class QuotationEditorPresenter
		extends AbstractEditorPresenter<QuotationDto, QuotationEditorPresenter.MyView, QuotationEditorPresenter.MyProxy>
		implements QuotationEditorUiHandlers {
	private static Logger logger = Logger.getLogger(QuotationEditorPresenter.class.getName());

	interface MyView extends AbstractEditorView<QuotationDto>, HasUiHandlers<QuotationEditorUiHandlers> {
		void setQuotationStatusData(List<QuotationStatusDto> quotationStatusData);

		void setOrganizationData(List<OrganizationDtor> organizationData);
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.QUOTATION_CREATOR)
	interface MyProxy extends ProxyPlace<QuotationEditorPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<QuotationResource> resourceDelegate;
	private final QuotationStatusDataSource quotationStatusDataSource;
	private final OrganizationDataSource2 organizationDataSource;
	private final CurrentUser currentUser;
	private final CoreMessages i18nCore;

	@Inject
	QuotationEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<QuotationResource> resourceDelegate, QuotationStatusDataSource quotationStatusDataSource,
			OrganizationDataSource2 organizationDataSource, CurrentUser currentUser, CoreMessages i18nCore) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("QuotationEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.quotationStatusDataSource = quotationStatusDataSource;
		this.organizationDataSource = organizationDataSource;
		this.currentUser = currentUser;
		this.i18nCore = i18nCore;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		loadQuotationStatusData();
	}

	private void loadQuotationStatusData() {
		quotationStatusDataSource.setOnlyActive(true);
		LoadCallback<QuotationStatusDto> quotationStatusLoadCallback = new LoadCallback<QuotationStatusDto>() {
			@Override
			public void onSuccess(LoadResult<QuotationStatusDto> loadResult) {
				getView().setQuotationStatusData(loadResult.getData());
				if (organizationDataSource.getIsLoaded())
					start();
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		quotationStatusDataSource.load(new LoadConfig<QuotationStatusDto>(0, 0, null, null),
				quotationStatusLoadCallback);
	}

	private void start() {
		if (isNew()) {
			SetPageTitleEvent.fire(i18nCore.quotationEditorCreateTitle(), i18nCore.quotationEditorCreateDescription(),
					MenuItemType.MENU_ITEM, QuotationEditorPresenter.this);
			create();
		} else {
			SetPageTitleEvent.fire(i18nCore.quotationEditorModifyTitle(), i18nCore.quotationEditorModifyDescription(),
					MenuItemType.MENU_ITEM, QuotationEditorPresenter.this);
			edit(filters.get(WEBSAFEKEY));
		}
	}

	@Override
	protected QuotationDto createDto() {
		logger.info("createDto()->currentUser=" + currentUser);
		QuotationDto dto = new QuotationDto();
		dto.setAccount(currentUser.getAppUserDto().getAccount());
		return dto;
	}

	private void edit(String webSafeKey) {
		resourceDelegate.withCallback(new AsyncCallback<QuotationDto>() {
			@Override
			public void onSuccess(QuotationDto dto) {
				getView().edit(dto);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		}).get(webSafeKey);
	}

	@Override
	public void save(QuotationDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<QuotationDto>() {
			@Override
			public void onSuccess(QuotationDto dto) {
				Builder placeBuilder = new Builder().nameToken(CfgNameTokens.ORGANIZATION_DISPLAY);
				placeBuilder.with(ApiParameters.WEBSAFEKEY, String.valueOf(dto.getWebSafeKey()));
				placeManager.revealPlace(placeBuilder.build());
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		}).saveOrCreate(dto);
	}
}