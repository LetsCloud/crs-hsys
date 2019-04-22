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

import io.crs.hsys.client.core.datasource.QuotationStatusDataSource;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.ui.filter.AbstractFilterPresenter;
import io.crs.hsys.client.core.ui.filter.AbstractFilterUiHandlers;
import io.crs.hsys.shared.dto.doc.QuotationStatusDto;

/**
 * @author robi
 *
 */
public class QuotationFilterPresenter extends AbstractFilterPresenter<QuotationFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(QuotationFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {

		String getCode();

		String getDescription();

		void setQuotationStatusData(List<QuotationStatusDto> quotationStatusGroupData);

		List<String> getSelectedQuotationStatusKeys();
	}

	private final CurrentUser currentUser;
	private final QuotationStatusDataSource quotationStatusDataSource;

	@Inject
	QuotationFilterPresenter(EventBus eventBus, MyView view, CurrentUser currentUser,
			QuotationStatusDataSource quotationStatusDataSource) {
		super(eventBus, view, currentUser);
		logger.info("QuotationFilterPresenter()");

		this.currentUser = currentUser;
		this.quotationStatusDataSource = quotationStatusDataSource;

		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		loadQuotationStatusData();
	}

	private void loadQuotationStatusData() {
		LoadCallback<QuotationStatusDto> quotationStatusLoadCallback = new LoadCallback<QuotationStatusDto>() {

			@Override
			public void onSuccess(LoadResult<QuotationStatusDto> loadResult) {
				getView().setQuotationStatusData(loadResult.getData());
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		};
		quotationStatusDataSource.load(new LoadConfig<QuotationStatusDto>(0, 0, null, null),
				quotationStatusLoadCallback);
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
}
