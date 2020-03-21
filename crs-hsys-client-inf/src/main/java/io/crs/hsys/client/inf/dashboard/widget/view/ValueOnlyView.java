/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget.view;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import io.crs.hsys.client.inf.dashboard.widget.AbstractDataWidgetView;
import io.crs.hsys.shared.cnst.cube.DataWidgetFieldType;
import io.crs.hsys.shared.dto.cube.DataWidgetValueM1Dto;
import io.crs.hsys.shared.dto.cube.dw.DataWidgetConfigDto;
import io.crs.hsys.shared.dto.cube.dw.DataWidgetFieldDto;

/**
 * @author CR
 *
 */
public class ValueOnlyView extends AbstractDataWidgetView {
	private static Logger logger = Logger.getLogger(ValueOnlyView.class.getName());

	protected Double factValue;

	private ValuePanel valuePanel;

	@Inject
	protected ValueOnlyView() {
		super();
		logger.log(Level.INFO, "ValueOnlyView()");
		valuePanel = new ValuePanel();
		addContent(valuePanel);
	}

	@Override
	public void setConfig(DataWidgetConfigDto config) {
		super.setConfig(config);

		for (DataWidgetFieldDto field : config.getFields()) {
			// Ha a
			if (field.getFieldType().equals(DataWidgetFieldType.VALUE)) {
				valuePanel.setFormat(field.getFormat());
				valuePanel.setPrefix(field.getPrefix());
				valuePanel.setSufix(field.getSufix());
				valuePanel.setMultiplier(field.getMultiplier());
				if (field.getRemark() != null)
					valuePanel.setRemark(field.getRemark());
				return;
			}
		}
	}

	@Override
	public void setValues(List<DataWidgetValueM1Dto> values) {
		if (values == null)
			return;

		for (DataWidgetValueM1Dto value : values) {
			if (value.getFieldType().equals(DataWidgetFieldType.VALUE)) {
				factValue = value.getValue();
				valuePanel.setValue(factValue);
				return;
			}
		}
	}

	public ValuePanel getValuePanel() {
		return valuePanel;
	}

	public void addStyleName(String style) {
		valuePanel.addStyleName(style);
	}

	@Override
	public void setHeight(String height1, String height2) {
		setHeight(height1);
	}

	@Override
	public void setWidth(String height1, String height2) {
		setWidth(height1);
	}

	public Double getFactValue() {
		return factValue;
	}
}
