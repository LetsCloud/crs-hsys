/**
 * 
 */
package io.crs.hsys.client.cfg.filter;

import javax.inject.Inject;

import com.google.common.base.Strings;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialTextBox;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent.DataTable;

/**
 * @author robi
 *
 */
public class TextFilter extends Composite {

	private Panel chipPanel;
	private MaterialTextBox textBox = new MaterialTextBox();;
	private MaterialChip chip = new MaterialChip();
	private String chipLabel = "";

	private final EventBus eventBus;

	@Inject
	TextFilter(EventBus eventBus) {
		this.eventBus = eventBus;
		initWidget(textBox);
		initTextBox();
	}

	public void setChipLabel(String label) {
		chipLabel = label;
	}

	public void setLabel(String label) {
		textBox.setLabel(label);
	}

	public String getValue() {
		return textBox.getValue();
	}

	public void setValue(String value) {
		textBox.setValue(value);
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

	private void setChipText(String text) {
		if (chip.isAttached()) {
			if ((text == null) || (text.isEmpty())) {
				chipPanel.remove(chip);
				return;
			}
			chip.setText(text);
		} else {
			if ((text != null) && (!text.isEmpty())) {
				chip.setText(text);
				chipPanel.add(chip);
			}
		}
	}

	public void setGrid(String grid) {
		textBox.setGrid(grid);
	}

	public void setChipPanel(Panel panel) {
		this.chipPanel = panel;
	}
}
