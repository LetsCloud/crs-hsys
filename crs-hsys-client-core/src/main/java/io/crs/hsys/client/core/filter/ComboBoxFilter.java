/**
 * 
 */
package io.crs.hsys.client.core.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.base.MaterialWidget;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent.DataTable;
import io.crs.hsys.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public abstract class ComboBoxFilter<T extends BaseDto> extends BaseFilter {
	private static Logger logger = Logger.getLogger(ComboBoxFilter.class.getName());

	MaterialComboBox<T> comboBox;

	protected ComboBoxFilter(EventBus eventBus) {
		super(eventBus);
		logger.info("ComboBoxFilter()");
		comboBox = new MaterialComboBox<T>();
		initComboBox();
		initWidget(comboBox);
	}

	@Override
	protected MaterialWidget getFilterWidget() {
		return comboBox;
	}

	public void setFilterLabel(String label) {
		comboBox.setLabel(label);
		comboBox.setPlaceholder(label);
	}

	/**
	 * Feltölti a ComboBox-ot adatokkal.
	 * 
	 * @param data
	 */
	public void setComboBoxData(List<T> data) {
		comboBox.clear();
		for (T dto : data)
			comboBox.addItem(createComboBoxItemText(dto), dto);
	}

	protected abstract String createComboBoxItemText(T dto);

	public List<String> getSelectedDataKeys() {
		logger.info("ComboBoxFilter().getSelectedDataKeys()");
		List<String> result = new ArrayList<String>();
		for (T dto : comboBox.getSelectedValue())
			result.add(dto.getWebSafeKey());
		return result;
	}

	public void setValue(T value) {
		comboBox.setSingleValue(value);
		comboBox.unselect();
	}

	public void unselect() {
		comboBox.unselect();
	}

	public void setAllowClear(boolean allowClear) {
		comboBox.setAllowBlank(allowClear);
		comboBox.setAllowClear(allowClear);
	}

	public void setItemKey(String webSafeKey) {
		logger.info("ComboBoxFilter().setItemKey()->webSafeKey=" + webSafeKey);
		List<T> values = comboBox.getValues();
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i).getWebSafeKey().equals(webSafeKey)) {
				comboBox.setSelectedIndex(i);
				setChipText(createChipText(comboBox.getSelectedValue()));
				return;
			}
		}
	}

	/**
	 * Inicializáció
	 */
	protected void initComboBox() {
		comboBox.setMultiple(true);
		comboBox.setAllowClear(true);
		comboBox.setAllowBlank(true);
		comboBox.setCloseOnSelect(false);
		comboBox.setMarginTop(25);

		comboBox.addSelectionHandler(e -> {
			logger.info("ComboBoxFilter().initComboBox().addSelectionHandler()");
			setChipText(createChipText(e.getSelectedValues()));
			eventBus.fireEvent(new FilterChangeEvent(DataTable.QUOTATION));
		});

		comboBox.addRemoveItemHandler(e -> {
			logger.info("ComboBoxFilter().initComboBox().addRemoveItemHandler()");
			setChipText(null);
			eventBus.fireEvent(new FilterChangeEvent(DataTable.QUOTATION));
		});
	}

	public void setMultiple(boolean multiple) {
		comboBox.setMultiple(multiple);
		comboBox.setCloseOnSelect(!multiple);
	}

	public void setMarginLeft(double margin) {
		comboBox.setMarginLeft(margin);
	}

	public void setMarginRight(double margin) {
		comboBox.setMarginRight(margin);
	}

	public void setEnabled(boolean enabled) {
		logger.info("ComboBoxFilter()->enabled=" + enabled);
		comboBox.setEnabled(enabled);
	}

	protected abstract String createChipText(List<T> selectedItems);
}
