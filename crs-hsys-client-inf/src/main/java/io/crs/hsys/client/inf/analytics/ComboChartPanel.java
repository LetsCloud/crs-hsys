/**
 * 
 */
package io.crs.hsys.client.inf.analytics;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
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
import com.googlecode.gwt.charts.client.options.TitlePosition;
import com.googlecode.gwt.charts.client.options.VAxis;

import gwt.material.design.client.ui.MaterialPanel;
import io.crs.hsys.client.inf.ana.ChartPanel;
import io.crs.hsys.client.inf.dashboard.widget.view.ComboChartSimple;

/**
 * @author CR
 *
 */
public class ComboChartPanel extends Composite implements ChartPanel {
	private static final Logger LOGGER = Logger.getLogger(ComboChartSimple.class.getName());

	private MaterialPanel panel;
	private Label titleLabel;
	private ComboChart chart;
	private Boolean isReady = false;
	private Boolean simpleView = false;
	private Boolean fullRedraw = false;
	private Integer height = 200;
	private List<Double> actualValues, baseValues;

	public ComboChartPanel() {
		panel = new MaterialPanel();
		initWidget(panel);
		panel.getElement().getStyle().setPaddingLeft(5, Unit.PX);
		panel.getElement().getStyle().setPaddingRight(5, Unit.PX);

		titleLabel = new Label();
//		panel.add(titleLabel);

		initialize();
	}

	private void initialize() {
		LOGGER.log(Level.INFO, "initialize()");
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
	public void setTitle(String title) {
		this.titleLabel.setText(title);
	}

	@Override
	public void setDataSeries(List<Double> actualValues, List<Double> baseValues) {
		this.actualValues = actualValues;
		this.baseValues = baseValues;
		runChart();
	}

	private void drawChart(List<Double> actualValues) {
		this.actualValues = actualValues;
		runChart();
	}

	private void runChart() {

		Timer timer = new Timer() {
			public void run() {
				if (isReady) {
					drawChart();
					cancel();
				}
			}
		};
		timer.scheduleRepeating(100);
	}

	private void drawChart() {
		LOGGER.log(Level.INFO, "drawChart()");
		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "");
		dataTable.addColumn(ColumnType.NUMBER, "Tény");

		if ((baseValues != null) && (baseValues.size() > 0)) {
			LOGGER.log(Level.INFO, "((baseValues != null) && (baseValues.size() > 0))");
			dataTable.addColumn(ColumnType.NUMBER, "Bázis");
		}

		if ((actualValues != null) && (actualValues.size() > 0)) {
			LOGGER.log(Level.INFO, "((baseValues != null) && (baseValues.size() > 0))");
			dataTable.addRows(actualValues.size());
			for (int i = 0; i < actualValues.size(); i++) {
				int day = i + 1;
				if (day < 10)
					dataTable.setValue(i, 0, "2017.07.0" + day);
				if (day > 9)
					dataTable.setValue(i, 0, "2017.07." + day);
			}
			for (int i = 0; i < actualValues.size(); i++) {
				dataTable.setValue(i, 1, actualValues.get(i));
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
		LOGGER.log(Level.INFO, "getOptions()");
		// Set options
		ComboChartOptions options = ComboChartOptions.create();
		options.setTitlePosition(TitlePosition.NONE);
		options.setFontName("Tahoma");
		// options.setWidth(140);
		options.setHeight(height);
		options.setCurveType(CurveType.FUNCTION);
		options.setAxisTitlesPosition(AxisTitlesPosition.OUT);
		options.setColors("#ffa726");

		options.setLegend(Legend.create(LegendPosition.RIGHT));
		// options.setIsStacked(true);
		Gridlines gridlines = Gridlines.create();
		gridlines.setColor("grey");

		if (simpleView) {
			gridlines.setColor("white");

			ChartArea chartArea = ChartArea.create();
			chartArea.setTop("10px");
			chartArea.setLeft("10px");
			chartArea.setWidth("100%");
			chartArea.setHeight("100%");
			// chartArea.setLeft("10%");
			options.setChartArea(chartArea);
		}

		VAxis vAxis = VAxis.create();
		vAxis.setMinValue(0);
		vAxis.setTextPosition(TextPosition.OUT);
		vAxis.setGridlines(gridlines);
		vAxis.setBaseline(1);
		vAxis.setBaselineColor("grey");
		vAxis.setTitle("Szobaéj");
		options.setVAxis(vAxis);

		HAxis hAxis = HAxis.create();
		hAxis.setTextPosition(TextPosition.OUT);
		hAxis.setGridlines(gridlines);
		hAxis.setBaseline(1);
		hAxis.setBaselineColor("grey");
		hAxis.setTitle("Tartózkodás");
		hAxis.setSlantedText(false);
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

		Timer timer = new Timer() {
			public void run() {
				if (isReady) {
					drawChart(factValues);
					cancel();
				}
			}
		};
		timer.scheduleRepeating(100);
	}

	@Override
	public void redrawChart() {
		LOGGER.log(Level.INFO, "redrawChart()");
		if (isReady) {
			if (fullRedraw) {
				runChart();
			} else {
				chart.redraw();
			}
		}
	}

	@Override
	public void setSimpleView(Boolean isSimple) {
		LOGGER.log(Level.INFO, "setSimpleView()");
		if (this.simpleView != isSimple)
			fullRedraw = true;
		this.simpleView = isSimple;
	}

	@Override
	public void setHeight(Integer height) {
		if (this.height != height)
			fullRedraw = true;
		this.height = height;
	}
}
