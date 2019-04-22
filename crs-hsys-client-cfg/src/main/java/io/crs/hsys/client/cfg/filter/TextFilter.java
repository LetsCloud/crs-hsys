/**
 * 
 */
package io.crs.hsys.client.cfg.filter;

import com.google.common.base.Strings;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Composite;

import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialTextBox;

/**
 * @author robi
 *
 */
public class TextFilter extends Composite {

	private ComplexPanel header;
	private MaterialTextBox textBox = new MaterialTextBox();;
	private MaterialChip chip = new MaterialChip();
	private String chipLabel = "";
	
	public TextFilter() {
		initWidget(textBox);
		initTextBox();
	}
	
	public TextFilter(String label) {
		this();
		textBox.setLabel(label);
	}
	
	private void initTextBox() {
		textBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (Strings.isNullOrEmpty(event.getValue()))
					setCodeChip("");
				else
					setCodeChip(chipLabel + event.getValue());
				
				FilterChangeEvent.fire(TextFilter.this);
			}
		});
	}
	
	private void setCodeChip(String code) {
		if (chip.isAttached()) {
			if ((code == null) || (code.isEmpty())) {
				header.remove(chip);
				return;
			}
			chip.setText(code);
		} else {
			if ((code != null) && (!code.isEmpty())) {
				chip.setText(code);
				header.add(chip);
			}
		}
	}

}
