/**
 * 
 */
package io.crs.hsys.client.fro.rate.updater;

import java.util.Arrays;
import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialDoubleBox;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.shared.cnst.RatePriceType;
import io.crs.hsys.shared.cnst.RateUpdateOperation;
import io.crs.hsys.shared.dto.rate.update.RoomRateOperationDto;

/**
 * @author robi
 *
 */
public class RateEditor extends Composite implements Editor<RoomRateOperationDto> {

	interface Binder extends UiBinder<Widget, RateEditor> {
	}

	private RatePriceType tempType; 
	
	@Ignore
	@UiField
	MaterialComboBox<RateUpdateOperation> operationCombo;
	TakesValueEditor<RateUpdateOperation> operation;

	TakesValueEditor<RatePriceType> type;

	@UiField
	MaterialDoubleBox value;
	
	/**
	 * 
	 */
	@Inject
	RateEditor(Binder uiBinder, CoreConstants i18nCoreCnst) {
		
		initWidget(uiBinder.createAndBindUi(this));

		initPriceType(i18nCoreCnst.ratePriceType());
		initOperationCombo(i18nCoreCnst.rateUpdateOperationMap());
	}

	private void initPriceType(Map<String, String> i18nMap) {
		type = TakesValueEditor.of(new TakesValue<RatePriceType>() {

		@Override
		public void setValue(RatePriceType value) {
			tempType = value;
			setValueLabel(i18nMap.get(value.toString()));
		}

		@Override
		public RatePriceType getValue() {
			return tempType;
		}
	});
	}

	
	private void initOperationCombo(Map<String, String> i18nMap) {
		Arrays.asList(RateUpdateOperation.values())
				.forEach(st -> operationCombo.addItem(i18nMap.get(st.toString()), st));

		operation = TakesValueEditor.of(new TakesValue<RateUpdateOperation>() {

			@Override
			public void setValue(RateUpdateOperation value) {
				operationCombo.setSingleValue(value);
			}

			@Override
			public RateUpdateOperation getValue() {
				return operationCombo.getSingleValue();
			}
		});
	}

	public void setValueLabel(String text) {
		value.setLabel(text);
		value.setPlaceholder(text);
	}
}
