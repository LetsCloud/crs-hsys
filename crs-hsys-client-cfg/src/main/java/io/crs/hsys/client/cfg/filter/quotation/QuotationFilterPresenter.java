/**
 * 
 */
package io.crs.hsys.client.cfg.filter.quotation;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import io.crs.hsys.client.core.datasource.OrganizationDataSource2;
import io.crs.hsys.client.core.datasource.QuotationStatusDataSource;
import io.crs.hsys.client.core.filter.AbstractFilterPresenter;
import io.crs.hsys.client.core.filter.AbstractFilterUiHandlers;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.dto.doc.QuotationStatusDto;
import io.crs.hsys.shared.dto.profile.OrganizationDtor;

/**
 * @author robi
 *
 */
public class QuotationFilterPresenter extends AbstractFilterPresenter<QuotationFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(QuotationFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {

		String getCode();

		String getDescription();

		void setQuotationStatusData(List<QuotationStatusDto> data);

		List<String> getSelectedQuotationStatusKeys();

		void setOrganizationData(List<OrganizationDtor> data);

		List<String> getSelectedOrganizationKeys();

		void setOrganizationKey(String webSafeKey);
	}

	private String organizationKey;
	
	private final QuotationStatusDataSource quotationStatusDataSource;
	private final OrganizationDataSource2 organizationDataSource;

	@Inject
	QuotationFilterPresenter(EventBus eventBus, MyView view, CurrentUser currentUser,
			QuotationStatusDataSource quotationStatusDataSource, OrganizationDataSource2 organizationDataSource) {
		super(eventBus, view, currentUser);
		logger.info("QuotationFilterPresenter()");

		this.quotationStatusDataSource = quotationStatusDataSource;
		this.organizationDataSource = organizationDataSource;

		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		loadQuotationStatusData();
		loadOrganizationData();
	}

	private void loadQuotationStatusData() {
		LoadCallback<QuotationStatusDto> quotationStatusLoadCallback = new LoadCallback<QuotationStatusDto>() {

			@Override
			public void onSuccess(LoadResult<QuotationStatusDto> loadResult) {
				getView().setQuotationStatusData(loadResult.getData());
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		};
		quotationStatusDataSource.load(new LoadConfig<QuotationStatusDto>(0, 0, null, null),
				quotationStatusLoadCallback);
	}

	private void loadOrganizationData() {
		LoadCallback<OrganizationDtor> organizationLoadCallback = new LoadCallback<OrganizationDtor>() {

			@Override
			public void onSuccess(LoadResult<OrganizationDtor> loadResult) {
				getView().setOrganizationData(loadResult.getData());
				getView().setOrganizationKey(organizationKey);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		};
		organizationDataSource.load(new LoadConfig<OrganizationDtor>(0, 0, null, null), organizationLoadCallback);
	}

	public String getCode() {
		return getView().getCode();
	}

	public String getDescription() {
		return getView().getDescription();
	}

	public List<String> getQuotationStatusKeys() {
		return getView().getSelectedQuotationStatusKeys();
	}

	public void setOrganizationKey(String webSafeKey) {
		organizationKey = webSafeKey;
	}

	@Override
	public void filterChange() {
		// TODO Auto-generated method stub
		
	}
}
