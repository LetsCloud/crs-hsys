/**
 * 
 */
package io.crs.hsys.client.cfg.filter.quotation;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.common.base.Strings;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialTextBox;

import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.filter.AbstractFilterView;
import io.crs.hsys.shared.dto.doc.QuotationStatusDto;

/**
 * @author robi
 *
 */
public class QuotationFilterView extends AbstractFilterView implements QuotationFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(QuotationFilterView.class.getName());

	private MaterialChip codeChip, descriptionChip, quotationStatusChip;
	private MaterialTextBox codeTextBox, descriptionTextBox;
	private MaterialComboBox<QuotationStatusDto> quotationStatusCombo;

	@Inject
	QuotationFilterView(CoreMessages i18nCore) {
		super(i18nCore);
		logger.info("QuotationFilterView()");
	}

	@Override
	protected void initView() {
		createCodeFilter();
		createDescriptionFilter();
		initProfileGroupFilter();
	}

	@Override
	protected void createLayout() {
		codeTextBox.setGrid("s12 m6");
		controlPanel.add(codeTextBox);

		descriptionTextBox.setGrid("s12 m6");
		controlPanel.add(descriptionTextBox);

		quotationStatusCombo.setGrid("s12 m6");
		controlPanel.add(quotationStatusCombo);
	}

	/***
	 * Creating the CODE FILTER
	 */
	private void createCodeFilter() {
		codeChip = new MaterialChip();

		codeTextBox = new MaterialTextBox();
		codeTextBox.setLabel(i18nCore.profileFilterCodeLabel());
		codeTextBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (Strings.isNullOrEmpty(event.getValue()))
					setCodeChip("");
				else
					setCodeChip(i18nCore.profileFilterCode() + event.getValue());
				getUiHandlers().filterChange();
			}
		});
	}

	/**
	 * Display the selected code
	 * 
	 * @param code
	 */
	private void setCodeChip(String code) {
		if (codeChip.isAttached()) {
			if ((code == null) || (code.isEmpty())) {
				collapsibleHeader.remove(codeChip);
				return;
			}
			codeChip.setText(code);
		} else {
			if ((code != null) && (!code.isEmpty())) {
				codeChip.setText(code);
				collapsibleHeader.add(codeChip);
			}
		}
	}

	/**
	 * Return the selected code
	 */
	@Override
	public String getCode() {
		return codeTextBox.getValue();
	}

	/**
	 * Creating the DESCRIPTION FILTER
	 */
	private void createDescriptionFilter() {
		descriptionChip = new MaterialChip();

		descriptionTextBox = new MaterialTextBox();
		descriptionTextBox.setLabel(i18nCore.profileFilterNameLabel());
		descriptionTextBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (Strings.isNullOrEmpty(event.getValue()))
					setNameChip("");
				else
					setNameChip(i18nCore.profileFilterNameLabel() + event.getValue());
				getUiHandlers().filterChange();
			}
		});
	}

	private void setNameChip(String name) {
		if (descriptionChip.isAttached()) {
			if ((name == null) || (name.isEmpty())) {
				collapsibleHeader.remove(descriptionChip);
				return;
			}
			descriptionChip.setText(name);
		} else {
			if ((name != null) && (!name.isEmpty())) {
				descriptionChip.setText(name);
				collapsibleHeader.add(descriptionChip);
			}
		}
	}

	@Override
	public String getDescription() {
		return descriptionTextBox.getValue();
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
