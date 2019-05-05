/**
 * 
 */
package io.crs.hsys.client.cfg.filter.quotation;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import io.crs.hsys.client.cfg.filter.OrganizationFilter;
import io.crs.hsys.client.cfg.filter.QuotationStatusFilter;
import io.crs.hsys.client.cfg.filter.TextFilter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.filter.AbstractFilterView;
import io.crs.hsys.shared.dto.doc.QuotationStatusDto;
import io.crs.hsys.shared.dto.profile.OrganizationDtor;

/**
 * @author robi
 *
 */
public class QuotationFilterView extends AbstractFilterView implements QuotationFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(QuotationFilterView.class.getName());

	private TextFilter codeFilter, descriptionFilter;
	private QuotationStatusFilter quotationFilter;
	private OrganizationFilter organizationFilter;

	@Inject
	Provider<TextFilter> textFilterProvider;

	@Inject
	Provider<QuotationStatusFilter> quotationStatusFilterProvider;

	@Inject
	Provider<OrganizationFilter> organizationFilterProvider;

	@Inject
	QuotationFilterView(CoreMessages i18nCore) {
		super(i18nCore);
		logger.info("QuotationFilterView()");
	}

	@Override
	protected void initView() {
		codeFilter = textFilterProvider.get();
		codeFilter.setChipPanel(collapsibleHeader);
		codeFilter.setChipLabel(i18nCore.quotationFilterCodeChipLabel());
		codeFilter.setFilterLabel(i18nCore.quotationFilterCodeLabel());

		descriptionFilter = textFilterProvider.get();
		descriptionFilter.setChipPanel(collapsibleHeader);
		descriptionFilter.setChipLabel(i18nCore.quotationDescriptionChipLabel());
		descriptionFilter.setFilterLabel(i18nCore.quotationFilterDescriptionLabel());

		quotationFilter = quotationStatusFilterProvider.get();
		quotationFilter.setChipPanel(collapsibleHeader);
		quotationFilter.setFilterLabel(i18nCore.quotationFilterStatusLabel());

		organizationFilter = organizationFilterProvider.get();
		organizationFilter.setChipPanel(collapsibleHeader);
		organizationFilter.setFilterLabel(i18nCore.quotationFilterStatusLabel());
	}

	@Override
	protected void createLayout() {
		codeFilter.setGrid("s12 m6");
		controlPanel.add(codeFilter);

		descriptionFilter.setGrid("s12 m6");
		controlPanel.add(descriptionFilter);

		quotationFilter.setGrid("s12 m6");
		controlPanel.add(quotationFilter);

		organizationFilter.setGrid("s12 m6");
		controlPanel.add(organizationFilter);
	}

	/**
	 * Return the selected code
	 */
	@Override
	public String getCode() {
		return codeFilter.getValue();
	}

	@Override
	public String getDescription() {
		return descriptionFilter.getValue();
	}

	@Override
	public void setQuotationStatusData(List<QuotationStatusDto> data) {
		quotationFilter.setComboBoxData(data);
	}

	@Override
	public List<String> getSelectedQuotationStatusKeys() {
		return quotationFilter.getSelectedDataKeys();
	}

	@Override
	public void reset() {
		logger.info("ProfileFilterView().reset()");
	}

	@Override
	public void setOrganizationData(List<OrganizationDtor> data) {
		organizationFilter.setComboBoxData(data);
	}

	@Override
	public List<String> getSelectedOrganizationKeys() {
		return organizationFilter.getSelectedDataKeys();
	}

	@Override
	public void setOrganizationKey(String webSafeKey) {
		organizationFilter.setItemKey(webSafeKey);
	}
}