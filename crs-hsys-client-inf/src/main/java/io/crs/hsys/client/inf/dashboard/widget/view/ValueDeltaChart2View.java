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

/**
 * @author CR
 *
 */
public class ValueDeltaChart2View extends ValueDeltaChartView {
	private static Logger logger = Logger.getLogger(ValueDeltaChart2View.class.getName());

	@Inject
	ValueDeltaChart2View() {
		super();
		logger.log(Level.INFO, "ValueChangeChart2View()");
		createChartPanel();
		addContent(chartPanel.asWidget());
	}

	@Override
	protected void createChartPanel() {
		chartPanel = new ComboChartSimple();
	}

	@Override
	protected void init() {
		super.init();
		setWidgetWidth(WIDGET_WIDTHx2);
	}

	@Override
	public void setValues(List<DataWidgetValueM1Dto> values) {
		super.setValues(values);

		List<Double> dataSeriesValues = new ArrayList<Double>();
		for (DataWidgetValueM1Dto value : values) {
			if (value.getFieldType().equals(DataWidgetFieldType.ACTUAL_SERIES))
				dataSeriesValues.add(value.getValue());
		}

		List<Double> baseDataSeries = new ArrayList<Double>();
		for (DataWidgetValueM1Dto value : values) {
			if (value.getFieldType().equals(DataWidgetFieldType.BASE_SERIES)) {
				baseDataSeries.add(value.getValue());
			}
		}
		chartPanel.setDataSeries(dataSeriesValues, baseDataSeries);
	}

	@Override
	public void setHeight(String height1, String height2) {
		setHeight(height2);
		chartPanel.redrawChart();
	}

	@Override
	public void setWidth(String width1, String width2) {
		setWidth(width2);
		chartPanel.redrawChart();
	}

}
