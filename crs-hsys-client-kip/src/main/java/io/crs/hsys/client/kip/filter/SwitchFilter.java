/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import javax.inject.Inject;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.MaterialSwitch;
import io.crs.hsys.client.core.filter.widget.BaseFilter;

/**
 * @author robi
 *
 */
public class SwitchFilter extends BaseFilter {

	MaterialSwitch materialSwitch = new MaterialSwitch();

	@Inject
	SwitchFilter() {
		initWidget(materialSwitch);
		initSwitch();
	}

	@Override
	protected MaterialWidget getFilterWidget() {
		return materialSwitch;
	}

	public boolean getValue() {
		return materialSwitch.getValue();
	}

	public void setValue(boolean value) {
		materialSwitch.setValue(value);
	}

	public void setFilterOnLabel(String label) {
		materialSwitch.setOnLabel(label);
	}

	public void addValueChangeHandler(final ValueChangeHandler<Boolean> handler) {
		materialSwitch.addValueChangeHandler(handler);
	}

	private void initSwitch() {
		materialSwitch.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue())
					setChipText(chipLabel);
				else
					setChipText(null);
			}
		});
	}
}
