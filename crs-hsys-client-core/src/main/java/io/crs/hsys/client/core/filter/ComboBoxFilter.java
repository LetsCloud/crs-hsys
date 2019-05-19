/**
 * 
 */
package io.crs.hsys.client.core.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.addins.client.combobox.events.SelectItemEvent;
import gwt.material.design.addins.client.combobox.events.UnselectItemEvent;
import gwt.material.design.client.base.MaterialWidget;

import io.crs.hsys.shared.dto.BaseDto;

/**
 * Filter view-ban használt ComboBox és Chips filter páros. A BaseFilter ősnek
 * köszönhetően képes kezelni a választás eredményét kijelző Chips-t.
 * 
 * @author robi
 *
 */
public abstract class ComboBoxFilter<T extends BaseDto> extends BaseFilter {
	private static Logger logger = Logger.getLogger(ComboBoxFilter.class.getName());

	MaterialComboBox<T> comboBox;

	protected ComboBoxFilter() {
		logger.info("ComboBoxFilter()");
		comboBox = new MaterialComboBox<T>();
		initComboBox();
		initWidget(comboBox);
	}

	@Override
	protected MaterialWidget getFilterWidget() {
		return comboBox;
	}

	/**
	 * Az értéket kiválasztó ComboBox label és placeholder paraméterének beállítása.
	 * 
	 * @param label
	 */
	public void setFilterLabel(String label) {
		comboBox.setLabel(label);
		comboBox.setPlaceholder(label);
	}

	/**
	 * Az értéket kiválasztó ComboBox feltöltése adatokkal.
	 * 
	 * @param data
	 */
	public void setComboBoxData(List<T> data) {
		comboBox.clear();
		for (T dto : data)
			comboBox.addItem(createComboBoxItemText(dto), dto);
	}

	/**
	 * A ComboBox elem szövegének összeállítása.
	 * 
	 * @param dto
	 * @return
	 */
	protected abstract String createComboBoxItemText(T dto);

	/**
	 * A ComboBox-val kiválasztott elemek egyedi kulcsainak visszaadása.
	 * 
	 * @return
	 */
	public List<String> getSelectedDataKeys() {
		List<String> result = new ArrayList<String>();
		for (T dto : comboBox.getSelectedValue())
			result.add(dto.getWebSafeKey());
		return result;
	}

	/**
	 * A ComboBox-val kiválasztott elem egyedi kulcsának visszaadása.
	 * 
	 * @return
	 */
	public String getSelectedDataKey() {
		if (comboBox.getSelectedValue().isEmpty())
			return null;
		return comboBox.getSelectedValue().get(0).getWebSafeKey();
	}

	/**
	 * A ComboBox inicializálása értékkel.
	 * 
	 * @param value
	 */
	public void setValue(T value) {
		comboBox.setSingleValue(value);
		comboBox.unselect();
	}

	public void unselect() {
		comboBox.unselect();
	}

	public void setItemKey(String webSafeKey) {
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
			setChipText(createChipText(e.getSelectedValues()));
//			eventBus.fireEvent(new FilterChangeEvent(DataTable.QUOTATION));
		});

		comboBox.addRemoveItemHandler(e -> {
			setChipText(null);
//			eventBus.fireEvent(new FilterChangeEvent(DataTable.QUOTATION));
		});
	}

	public void setMultiple(boolean multiple) {
		comboBox.setMultiple(multiple);
		comboBox.setCloseOnSelect(!multiple);
	}

	public void setAllowClear(boolean allowClear) {
		comboBox.setAllowBlank(allowClear);
		comboBox.setAllowClear(allowClear);
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

	public HandlerRegistration addSelectionHandler(SelectItemEvent.SelectComboHandler<T> selectionHandler) {
		return comboBox.addHandler(selectionHandler, SelectItemEvent.getType());
	}

	public HandlerRegistration addRemoveItemHandler(UnselectItemEvent.UnselectComboHandler<T> handler) {
		return comboBox.addHandler(handler, UnselectItemEvent.getType());
	}
}
