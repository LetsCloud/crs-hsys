/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import javax.inject.Inject;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.MaterialCheckBox;

import io.crs.hsys.client.core.filter.BaseFilter;

/**
 * @author CR
 *
 */
public class CheckBoxFilter extends BaseFilter {

	MaterialCheckBox checkBox = new MaterialCheckBox();

	@Inject
	CheckBoxFilter() {
		initWidget(checkBox);
		initCheckBox();
	}

	@Override
	protected MaterialWidget getFilterWidget() {
		return checkBox;
	}

	public boolean getValue() {
		return checkBox.getValue();
	}

	public void setValue(boolean value) {
		checkBox.setValue(value);
	}

	public void setFilterLabel(String label) {
		checkBox.setText(label);
	}

	public void addValueChangeHandler(final ValueChangeHandler<Boolean> handler) {
		checkBox.addValueChangeHandler(handler);
	}

	private void initCheckBox() {
		checkBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue())
					setChipText(chipLabel);
				else
					setChipText(null);
			}
		});
	}
	
	public void setFilterMarginTop(double margin) {
		checkBox.setMarginTop(margin);
	}
}
