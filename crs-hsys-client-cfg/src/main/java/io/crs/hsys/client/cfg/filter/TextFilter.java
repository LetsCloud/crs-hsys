/**
 * 
 */
package io.crs.hsys.client.cfg.filter;

import javax.inject.Inject;

import com.google.common.base.Strings;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.MaterialTextBox;

import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent.DataTable;

/**
 * @author robi
 *
 */
public class TextFilter extends BaseFilter {

	MaterialTextBox textBox = new MaterialTextBox();
	
	@Inject
	TextFilter(EventBus eventBus) {
		super(eventBus);
		initWidget(textBox);
		initTextBox();
	}

	@Override
	protected MaterialWidget getFilterWidget() {
		return textBox;
	}

	public String getValue() {
		return textBox.getValue();
	}

	public void setValue(String value) {
		textBox.setValue(value);
	}

	public void setFilterLabel(String label) {
		textBox.setLabel(label);
	}

	private void initTextBox() {
		textBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (Strings.isNullOrEmpty(event.getValue()))
					setChipText("");
				else
					setChipText(chipLabel + event.getValue());

				eventBus.fireEvent(new FilterChangeEvent(DataTable.QUOTATION));
			}
		});
	}
}
