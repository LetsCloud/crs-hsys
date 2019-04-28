/**
 * 
 */
package io.crs.hsys.client.cfg.filter.quotation;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialChip;

import io.crs.hsys.client.cfg.filter.TextFilter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.filter.AbstractFilterView;
import io.crs.hsys.shared.dto.doc.QuotationStatusDto;

/**
 * @author robi
 *
 */
public class QuotationFilterView extends AbstractFilterView implements QuotationFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(QuotationFilterView.class.getName());

	private TextFilter codeFilter, descriptionFilter;
	private MaterialChip quotationStatusChip;
	private MaterialComboBox<QuotationStatusDto> quotationStatusCombo;

	@Inject
	Provider<TextFilter> textFilterProvider;
	
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
		codeFilter.setLabel(i18nCore.quotationFilterCodeLabel());
		
		descriptionFilter = textFilterProvider.get();
		descriptionFilter.setChipPanel(collapsibleHeader);
		descriptionFilter.setChipLabel(i18nCore.quotationDescriptionChipLabel());
		descriptionFilter.setLabel(i18nCore.quotationFilterDescriptionLabel());
		
		initProfileGroupFilter();
	}

	@Override
	protected void createLayout() {
		codeFilter.setGrid("s12 m6");
		controlPanel.add(codeFilter);

		descriptionFilter.setGrid("s12 m6");
		controlPanel.add(descriptionFilter);

		quotationStatusCombo.setGrid("s12 m6");
		controlPanel.add(quotationStatusCombo);
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

	/**
	 * 
	 */
	private void initProfileGroupFilter() {
		quotationStatusChip = new MaterialChip();

		quotationStatusCombo = new MaterialComboBox<QuotationStatusDto>();
		quotationStatusCombo.setMultiple(true);
		quotationStatusCombo.setAllowClear(true);
		quotationStatusCombo.setAllowBlank(true);
		quotationStatusCombo.setCloseOnSelect(false);
		quotationStatusCombo.setMarginTop(25);
		quotationStatusCombo.setLabel(i18nCore.profileFilterProfileGroupLabel());
//		profileGroupCombo.setPlaceholder(i18nCore.roomFilterRoomTypesPlaceholder());
		quotationStatusCombo.addSelectionHandler(e -> {
			String roomTypesText = null;
			for (QuotationStatusDto roomType : e.getSelectedValues()) {
				if (roomTypesText == null) {
					roomTypesText = roomType.getCode();
				} else {
					roomTypesText = roomTypesText + ", " + roomType.getCode();
				}
			}
			setProfileGroupChip(roomTypesText);
			getUiHandlers().filterChange();
		});
		quotationStatusCombo.addRemoveItemHandler(e -> {
			setProfileGroupChip(null);
			getUiHandlers().filterChange();
		});
	}

	private void setProfileGroupChip(String profileGroup) {
		if (quotationStatusChip.isAttached()) {
			if ((profileGroup == null) || (profileGroup.isEmpty())) {
				collapsibleHeader.remove(quotationStatusChip);
				return;
			}
			quotationStatusChip.setText(profileGroup);
		} else {
			if ((profileGroup != null) && (!profileGroup.isEmpty())) {
				quotationStatusChip.setText(profileGroup);
				collapsibleHeader.add(quotationStatusChip);
			}
		}
	}

	@Override
	public void setQuotationStatusData(List<QuotationStatusDto> data) {
		quotationStatusCombo.clear();
		for (QuotationStatusDto dto : data) {
			quotationStatusCombo.addItem(dto.getCode() + "-" + dto.getDescription(), dto);
		}
	}

	@Override
	public List<String> getSelectedQuotationStatusKeys() {
		List<String> result = new ArrayList<String>();
		for (QuotationStatusDto dto : quotationStatusCombo.getSelectedValues())
			result.add(dto.getWebSafeKey());
		return result;
	}

	@Override
	public void reset() {
		logger.info("ProfileFilterView().reset()");
	}
}
