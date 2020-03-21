/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget.view;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.ComboChart;
import com.googlecode.gwt.charts.client.corechart.ComboChartOptions;
import com.googlecode.gwt.charts.client.corechart.ComboChartSeries;
import com.googlecode.gwt.charts.client.options.AxisTitlesPosition;
import com.googlecode.gwt.charts.client.options.ChartArea;
import com.googlecode.gwt.charts.client.options.CurveType;
import com.googlecode.gwt.charts.client.options.Gridlines;
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.Legend;
import com.googlecode.gwt.charts.client.options.LegendPosition;
import com.googlecode.gwt.charts.client.options.SeriesType;
import com.googlecode.gwt.charts.client.options.TextPosition;
import com.googlecode.gwt.charts.client.options.VAxis;

import gwt.material.design.client.ui.MaterialPanel;

/**
 * @author CR
 *
 */
public class ComboChartSimple extends Composite implements SimpleChart {
	private static Logger logger = Logger.getLogger(ComboChartSimple.class.getName());

	private MaterialPanel panel;
	private ComboChart chart;
	private boolean isReady;

	public ComboChartSimple() {
		logger.log(Level.INFO, "ComboChartSimple()");
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
				chart = new ComboChart();
				panel.add(chart);
				isReady = true;
			}
		});
	}

	@Override
	public void setDataSeries(List<Double> factValues, List<Double> baseValues) {

		Timer timer = new Timer() {
			public void run() {
				if (isReady) {
					drawChart(factValues, baseValues);
					cancel();
				}
			}
		};
		timer.scheduleRepeating(500);
	}

	private void drawChart(List<Double> factValues, List<Double> baseValues) {
		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "");
		dataTable.addColumn(ColumnType.NUMBER, "");

		if ((baseValues != null) && (baseValues.size() > 0)) {
			dataTable.addColumn(ColumnType.NUMBER, "");
		}

		if ((factValues != null) && (factValues.size() > 0)) {
			dataTable.addRows(factValues.size());
			for (int i = 0; i < factValues.size(); i++) {
				int day = i + 1;
				if (day < 10)
					dataTable.setValue(i, 0, "2017.07.0" + day);
				if (day > 9)
					dataTable.setValue(i, 0, "2017.07." + day);
			}
			for (int i = 0; i < factValues.size(); i++) {
				dataTable.setValue(i, 1, factValues.get(i));
			}
		}

		if ((baseValues != null) && (baseValues.size() > 0)) {
			for (int i = 0; i < baseValues.size(); i++) {
				dataTable.setValue(i, 2, baseValues.get(i));
			}
		}

		// Draw the chart
		chart.draw(dataTable, getOptions());
	}

	private ComboChartOptions getOptions() {
		// Set options
		ComboChartOptions options = ComboChartOptions.create();
		options.setFontName("Tahoma");
		// options.setWidth(140);
		options.setHeight(140);
		options.setCurveType(CurveType.FUNCTION);
		options.setAxisTitlesPosition(AxisTitlesPosition.NONE);
		options.setColors("#ffa726");

		ChartArea ca = ChartArea.create();
		// ca.setTop("0px");
		// ca.setLeft("0px");
		ca.setWidth("100%");
		ca.setHeight("100%");
		options.setChartArea(ca);

		options.setLegend(Legend.create(LegendPosition.NONE));

		Gridlines gridlines = Gridlines.create();
		gridlines.setColor("white");

		VAxis vAxis = VAxis.create();
		vAxis.setMinValue(0);
		vAxis.setTextPosition(TextPosition.NONE);
		vAxis.setGridlines(gridlines);
		vAxis.setBaseline(0);
		vAxis.setBaselineColor("white");
		options.setVAxis(vAxis);

		HAxis hAxis = HAxis.create();
		hAxis.setTextPosition(TextPosition.NONE);
		hAxis.setGridlines(gridlines);
		hAxis.setBaseline(0);
		hAxis.setBaselineColor("white");
		options.setHAxis(hAxis);

		options.setSeriesType(SeriesType.LINE);

		ComboChartSeries series = ComboChartSeries.create();
		series.setType(SeriesType.AREA);
		series.setColor("#b2dfdb"); // TEAL_LIGHTEN_4
		series.setLineWidth(0);
		options.setSeries(1, series);

		return options;
	}

	@Override
	public void setDataSeries(List<Double> factValues) {
		// TODO Auto-generated method stub

	}

	@Override
	public void redrawChart() {
		if (isReady)
			chart.redraw();
	}

	@Override
	public void setAxisSeriesData(List<String> axisValues, List<Double> actualValues) {
		// TODO Auto-generated method stub
		
	}
}
