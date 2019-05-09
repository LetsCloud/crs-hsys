/**
 * 
 */
package io.crs.hsys.client.cfg.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.base.MaterialWidget;
import io.crs.hsys.client.core.filter.BaseFilter;
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
	}

	/**
	 * Feltölti a ComboBox-ot adatokkal.
	 * 
	 * @param data
	 */
	public void setComboBoxData(List<T> data) {
		comboBox.clear();
		for (T dto : data)
			comboBox.addItem(getComboBoxItemText(dto), dto);
	}

	protected abstract String getComboBoxItemText(T dto);

	public List<String> getSelectedDataKeys() {
		List<String> result = new ArrayList<String>();
		for (T dto : comboBox.getSelectedValues())
			result.add(dto.getWebSafeKey());
		return result;
	}

	public void setValue(T value) {
		comboBox.setSingleValue(value);
	}

	public void setItemKey(String webSafeKey) {
		List<T> values = comboBox.getValues();
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i).getWebSafeKey().equals(webSafeKey)) {
				comboBox.setSelectedIndex(i);
				setChipText(getChipText(comboBox.getSelectedValues()));
				return;
			}
		}
	}

	/**
	 * Inicializáció
	 */
	private void initComboBox() {
		comboBox.setMultiple(true);
//		comboBox.setAllowClear(true);
//		comboBox.setAllowBlank(true);
		comboBox.setCloseOnSelect(false);
		comboBox.setMarginTop(25);

		comboBox.addSelectionHandler(e -> {
			setChipText(getChipText(e.getSelectedValues()));
			eventBus.fireEvent(new FilterChangeEvent(DataTable.QUOTATION));
		});

		comboBox.addRemoveItemHandler(e -> {
			setChipText(null);
			eventBus.fireEvent(new FilterChangeEvent(DataTable.QUOTATION));
		});
	}

	protected abstract String getChipText(List<T> selectedItems);
}
