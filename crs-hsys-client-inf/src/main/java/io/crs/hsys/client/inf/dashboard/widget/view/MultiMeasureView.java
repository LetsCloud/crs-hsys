/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import io.crs.hsys.client.inf.dashboard.widget.AbstractDataWidgetView;
import io.crs.hsys.client.inf.dashboard.widget.DataWidgetPresenter.DataWidgetView;
import io.crs.hsys.shared.cnst.cube.DataWidgetFieldType;
import io.crs.hsys.shared.dto.cube.DataWidgetValueM1Dto;
import io.crs.hsys.shared.dto.cube.dw.DataWidgetConfigDto;
import io.crs.hsys.shared.dto.cube.dw.DataWidgetFieldDto;

/**
 * @author CR
 *
 */
public class MultiMeasureView extends AbstractDataWidgetView {
	private static Logger logger = Logger.getLogger(MultiMeasureView.class.getName());

	private MultiMeasurePanel multiMeasurePanel;

	private Map<DataWidgetFieldType, MultiMeasureField> fields = new HashMap<DataWidgetFieldType, MultiMeasureField>();

	@Inject
	MultiMeasureView() {
		super();
		logger.log(Level.INFO, "MultiMeasureView()");
	}

	@Override
	protected void init() {
		super.init();
		setWidgetHeight(CARD_H2X);
		setCardTitleHeight("40px");
		multiMeasurePanel = new MultiMeasurePanel();
		addContent(multiMeasurePanel);
	}

	@Override
	public void setConfig(DataWidgetConfigDto config) {
		super.setConfig(config);
		fields.clear();
		multiMeasurePanel.clear();
		for (DataWidgetFieldDto fieldConfig : config.getFields()) {
			MultiMeasureField field = new MultiMeasureField();
			field.setLabelText(fieldConfig.getTitle());
			field.setFormat(fieldConfig.getFormat());
			field.setPrefix(fieldConfig.getPrefix());
			field.setSufix(fieldConfig.getSufix());
			field.setMultiplier(fieldConfig.getMultiplier());
			fields.put(fieldConfig.getFieldType(), field);
			multiMeasurePanel.addMeasure(field);
		}
	}

	@Override
	public void setHeight(String height1, String height2) {
		setHeight(height2);
	}

	@Override
	public void setWidth(String height1, String height2) {
		setWidth(height1);
	}

	@Override
	public void setValues(List<DataWidgetValueM1Dto> values) {
		if (values == null)
			return;

		for (DataWidgetValueM1Dto value : values) {
			MultiMeasureField field = fields.get(value.getFieldType());
			field.setValue(value.getValue());
		}
	}

}
