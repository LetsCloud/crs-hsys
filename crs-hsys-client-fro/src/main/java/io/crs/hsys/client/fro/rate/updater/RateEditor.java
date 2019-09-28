/**
 * 
 */
package io.crs.hsys.client.fro.rate.updater;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialTextBox;
import io.crs.hsys.shared.dto.rate.update.RoomRateOperationDto;

/**
 * @author robi
 *
 */
public class RateEditor extends Composite implements Editor<RoomRateOperationDto> {

	interface Binder extends UiBinder<Widget, RateEditor> {
	}

	@UiField
	MaterialComboBox operationCombo, valueTypeCombo;
	
	@UiField
	MaterialTextBox valueBox;
	
	/**
	 * 
	 */
	@Inject
	RateEditor(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
