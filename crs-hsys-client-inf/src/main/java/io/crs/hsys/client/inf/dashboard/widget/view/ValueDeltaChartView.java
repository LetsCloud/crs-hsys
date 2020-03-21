/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget.view;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import io.crs.hsys.shared.cnst.cube.DataWidgetFieldType;
import io.crs.hsys.shared.dto.cube.DataWidgetValueM1Dto;
import io.crs.hsys.shared.dto.cube.dw.DataWidgetConfigDto;

/**
 * @author CR
 *
 */
public class ValueDeltaChartView extends ValueDeltaView {
	private static Logger logger = Logger.getLogger(ValueDeltaChartView.class.getName());

	protected SimpleChart chartPanel;

	@Inject
	ValueDeltaChartView() {
		super();
		logger.log(Level.INFO, "ValueDeltaChartView()");
		createChartPanel();
		addContent(chartPanel.asWidget());
	}

	protected void createChartPanel() {
		chartPanel = new LineChartPanel();
	}
	
	@Override
	public void setConfig(DataWidgetConfigDto config) {
		super.setConfig(config);
	}

	@Override
	public void setValues(List<DataWidgetValueM1Dto> values) {
		super.setValues(values);

		List<Double> dataSeriesValues = new ArrayList<Double>();
		for (DataWidgetValueM1Dto value : values) {
			if (value.getFieldType().equals(DataWidgetFieldType.ACTUAL_SERIES))
				dataSeriesValues.add(value.getValue());
		}
		chartPanel.setDataSeries(dataSeriesValues);
	}

	@Override
	public void setHeight(String height1, String height2) {
		setHeight(height2);
		chartPanel.redrawChart();
	}

	@Override
	public void setWidth(String width1, String width2) {
		super.setWidth(width1, width2);
		chartPanel.redrawChart();
	}

}
