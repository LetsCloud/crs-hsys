/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget.view;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import io.crs.hsys.client.inf.dashboard.widget.AbstractDataWidgetView;
import io.crs.hsys.shared.cnst.cube.DataWidgetFieldType;
import io.crs.hsys.shared.dto.cube.DataWidgetValueM1Dto;
import io.crs.hsys.shared.dto.cube.dw.DataWidgetConfigDto;

/**
 * @author CR
 *
 */
public class GeoView extends AbstractDataWidgetView {
	private static Logger logger = Logger.getLogger(GeoView.class.getName());

	protected Double factValue;

	private GeoChartPanel panel;

	@Inject
	protected GeoView() {
		super();
		logger.log(Level.INFO, "GeoView()");
		panel = new GeoChartPanel();
		addContent(panel);
	}

	@Override
	public void setConfig(DataWidgetConfigDto config) {
		super.setConfig(config);
	}

	@Override
	public void setValues(List<DataWidgetValueM1Dto> values) {
		if (values == null)
			return;
		
		List<String> xAxisValues = new ArrayList<String>();
		List<Double> serieValues = new ArrayList<Double>();

		for (DataWidgetValueM1Dto value : values) {
			if (value.getFieldType().equals(DataWidgetFieldType.VALUE)) {
				xAxisValues.add(value.getLabel());
				serieValues.add(value.getValue());
			}
		}
		panel.setAxisSeriesData(xAxisValues, serieValues);
	}

	public GeoChartPanel getValuePanel() {
		return panel;
	}

	public void addStyleName(String style) {
		panel.addStyleName(style);
	}

	@Override
	public void setHeight(String height1, String height2) {
		setHeight(height2);
	}

	@Override
	public void setWidth(String height1, String height2) {
		setWidth(height2);
		panel.redrawChart();
	}

	public Double getFactValue() {
		return factValue;
	}
}
