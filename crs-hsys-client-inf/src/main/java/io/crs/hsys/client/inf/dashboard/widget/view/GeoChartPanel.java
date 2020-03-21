/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget.view;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;

import gwt.material.design.client.ui.MaterialPanel;

/**
 * @author CR
 *
 */
public class GeoChartPanel extends Composite implements SimpleChart {
	private static Logger logger = Logger.getLogger(GeoChartPanel.class.getName());

	private MaterialPanel panel;
	private GeoChart chart;
	private boolean isReady;

	/**
	 */
	public GeoChartPanel() {
		logger.log(Level.INFO, "GeoChartPanel()");
		panel = new MaterialPanel();
		initWidget(panel);
		panel.getElement().getStyle().setPaddingLeft(5, Unit.PX);
		panel.getElement().getStyle().setPaddingRight(5, Unit.PX);
		isReady = false;
		initialize();
	}

	private void initialize() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				// Create and attach the chart
				chart = new GeoChart();
				panel.add(chart);
				isReady = true;
			}
		});
	}

	public void setDataSeries(List<Double> actualValues, List<Double> baseValues) {
	}

	public void setDataSeries(List<Double> actualValues) {
	}

	private void drawChart(List<String> axisValues, List<Double> actualValues) {
		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Country");
		dataTable.addColumn(ColumnType.NUMBER, "Guests");

		if ((actualValues != null) && (actualValues.size() > 0)) {
			dataTable.addRows(actualValues.size());
			for (int i = 0; i < actualValues.size(); i++) {
				dataTable.setValue(i, 0, axisValues.get(i));
				dataTable.setValue(i, 1, actualValues.get(i));
			}
		}

		// Draw the chart
		chart.draw(dataTable, getOptions());
	}

	private GeoChartOptions getOptions() {
		// Set options
		GeoChartOptions options = GeoChartOptions.create();
		// options.setEnableRegionInteractivity(true);
		// options.setRegion("150");
		options.hideLegend();
		GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
		geoChartColorAxis.setColors(getNativeArray());
		options.setColorAxis(geoChartColorAxis);
		options.setDatalessRegionColor("fafafa");
		return options;
	}

	@Override
	public void redrawChart() {
		if (isReady)
			chart.redraw();
	}

	private native JsArrayString getNativeArray() /*-{
													return ["fff3e0", "ffe0b2", "ffcc80", "ffb74d", "ffa726", "ff9800", "fb8c00", "f57c00", "ef6c00", "e65100"];
													}-*/;

	@Override
	public void setAxisSeriesData(List<String> axisValues, List<Double> actualValues) {
		Timer timer = new Timer() {
			public void run() {
				if (isReady) {
					drawChart(axisValues, actualValues);
					cancel();
				}
			}
		};
		timer.scheduleRepeating(500);
	}

}
