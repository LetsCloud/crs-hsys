/**
 * 
 */
package io.crs.hsys.client.fro.rate.updater;

import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialIntegerBox;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialSwitch;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.shared.cnst.RateRestrictionType;
import io.crs.hsys.shared.dto.rate.RateRestrictionDto;

/**
 * @author robi
 *
 */
public class RestrictionEditor extends Composite implements Editor<RateRestrictionDto> {
	private static Logger logger = Logger.getLogger(RestrictionEditor.class.getName());

	interface Binder extends UiBinder<Widget, RestrictionEditor> {
	}

	private RateRestrictionType tempType;

	@Ignore
	@UiField
	MaterialColumn firstColumn;

	@Ignore
	@UiField
	MaterialLabel label;

	TakesValueEditor<RateRestrictionType> type;

	@UiField
	MaterialIntegerBox value;

	@UiField
	MaterialSwitch applied;

	/**
	 * 
	 */
	@Inject
	RestrictionEditor(Binder uiBinder, CoreConstants i18nCoreCnst) {
		initWidget(uiBinder.createAndBindUi(this));
		initRestrictionType(i18nCoreCnst.rateRestrictionType());
		showValuBox(false);
		applied.addValueChangeHandler(e -> refreshValueBox(e));
	}

	private void initRestrictionType(Map<String, String> i18nMap) {
		type = TakesValueEditor.of(new TakesValue<RateRestrictionType>() {

			@Override
			public void setValue(RateRestrictionType value) {
				tempType = value;
				setValueLabel(i18nMap.get(value.toString()));
			}

			@Override
			public RateRestrictionType getValue() {
				return tempType;
			}
		});
	}

	public void setValueLabel(String text) {
		label.setText(text);
	}

	private void refreshValueBox(ValueChangeEvent<Boolean> event) {
		logger.info("RestrictionEditor().refreshValueBox().event.getValue()=" + event.getValue());

		if (((tempType == RateRestrictionType.RRT_MAXAB) || (tempType == RateRestrictionType.RRT_MAXLOS)
				|| (tempType == RateRestrictionType.RRT_MINAB) || (tempType == RateRestrictionType.RRT_MINLOS)
				|| (tempType == RateRestrictionType.RRT_MINST)) && (event.getValue())) {
			logger.info("RestrictionEditor().refreshValueBox(treu)");
			showValuBox(true);
		} else {
			logger.info("RestrictionEditor().refreshValueBox(false)");
			showValuBox(false);
		}
	}

	private void showValuBox(boolean show) {
		logger.info("RestrictionEditor().showValuBox()");
		value.setVisible(show);
		if (show) {
			logger.info("RestrictionEditor().showValuBox(show)");
			firstColumn.removeStyleName("s10 l9 col");
			firstColumn.setGrid("s8 l6");
		} else {
			logger.info("RestrictionEditor().showValuBox(hide)");
			firstColumn.removeStyleName("s8 l6 col");
			firstColumn.setGrid("s10 l9");
		}
	}
}
