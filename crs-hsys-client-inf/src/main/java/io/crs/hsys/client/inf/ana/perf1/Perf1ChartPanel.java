/**
 * 
 */
package io.crs.hsys.client.inf.ana.perf1;

import java.util.ArrayList;
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
import io.crs.hsys.shared.dto.cube.Perf1Dto;

/**
 * @author CR
 *
 */
public class Perf1ChartPanel extends Composite {
	private static final Logger LOGGER = Logger.getLogger(Perf1ChartPanel.class.getName());

	private MaterialPanel panel;
	private Label titleLabel;
	private ComboChart chart;
	private Boolean isReady = false;
	private Boolean simpleView = false;
	private Boolean fullRedraw = false;
	private Integer height = 200;
	private List<String> dimValues;
	private List<Double> occActValues, adrActValues, revparActValues, occVsValues, adrVsValues,
			revparVsValues;

	public Perf1ChartPanel() {
		panel = new MaterialPanel();
		initWidget(panel);
		panel.getElement().getStyle().setPaddingLeft(5, Unit.PX);
		panel.getElement().getStyle().setPaddingRight(5, Unit.PX);

		titleLabel = new Label();
		// panel.add(titleLabel);
		dimValues = new ArrayList<String>();
		occActValues = new ArrayList<Double>();
		adrActValues = new ArrayList<Double>();
		revparActValues = new ArrayList<Double>();
		occVsValues = new ArrayList<Double>();
		adrVsValues = new ArrayList<Double>();
		revparVsValues = new ArrayList<Double>();

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

	public void setData(List<Perf1Dto> data) {
		LOGGER.log(Level.INFO, "Perf1ChartPanel.setData()-start");
		dimValues.clear();
		occActValues.clear();
		adrActValues.clear();
		revparActValues.clear();
		occVsValues.clear();
		adrVsValues.clear();
		revparVsValues.clear();
		for (Perf1Dto dto : data) {
			dimValues.add(dto.getDim());
			occActValues.add(dto.getOccAct());
			adrActValues.add(dto.getAdrAct());
			revparActValues.add(dto.getRevparAct());
			occVsValues.add(dto.getOccVs());
			adrVsValues.add(dto.getAdrVs());
			revparVsValues.add(dto.getRevparVs());
		}
		LOGGER.log(Level.INFO, "Perf1ChartPanel.setData()-end");
	}

	/**
	 * 
	 */
	public void runChart() {

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

	/**
	 * 
	 */
	private void drawChart() {
		LOGGER.log(Level.INFO, "drawChart()");
		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "");
		dataTable.addColumn(ColumnType.NUMBER, "Occ%(Act)");
		dataTable.addColumn(ColumnType.NUMBER, "ADR(Act)");
		dataTable.addColumn(ColumnType.NUMBER, "RevPAR(Act)");
		dataTable.addColumn(ColumnType.NUMBER, "Occ%(Vs)");
		dataTable.addColumn(ColumnType.NUMBER, "ADR(Vs)");
		dataTable.addColumn(ColumnType.NUMBER, "RevPAR(Vs)");

		if ((dimValues != null) && (dimValues.size() > 0)) {
			LOGGER.log(Level.INFO, "((dimValues != null) && (dimValues.size() > 0))");
			dataTable.addRows(dimValues.size());
			for (int i = 0; i < dimValues.size(); i++) {
				dataTable.setValue(i, 0, dimValues.get(i));
			}
		}

		if ((occActValues != null) && (occActValues.size() > 0)) {
			for (int i = 0; i < occActValues.size(); i++) {
				dataTable.setValue(i, 1, occActValues.get(i));
			}
		}

		if ((adrActValues != null) && (adrActValues.size() > 0)) {
			for (int i = 0; i < adrActValues.size(); i++) {
				dataTable.setValue(i, 2, adrActValues.get(i));
			}
		}

		if ((revparActValues != null) && (revparActValues.size() > 0)) {
			for (int i = 0; i < revparActValues.size(); i++) {
				dataTable.setValue(i, 3, revparActValues.get(i));
			}
		}

		if ((occVsValues != null) && (occVsValues.size() > 0)) {
			for (int i = 0; i < occVsValues.size(); i++) {
				dataTable.setValue(i, 4, occVsValues.get(i));
			}
		}

		if ((adrVsValues != null) && (adrVsValues.size() > 0)) {
			for (int i = 0; i < adrVsValues.size(); i++) {
				dataTable.setValue(i, 5, adrVsValues.get(i));
			}
		}

		if ((revparVsValues != null) && (revparVsValues.size() > 0)) {
			for (int i = 0; i < revparVsValues.size(); i++) {
				dataTable.setValue(i, 6, revparVsValues.get(i));
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

		Legend legend = Legend.create(LegendPosition.TOP);
		options.setLegend(legend);
		// options.setIsStacked(true);
		Gridlines gridlines = Gridlines.create();
		gridlines.setColor("grey");

		if (simpleView) {
			gridlines.setColor("white");

			ChartArea chartArea = ChartArea.create();
			chartArea.setTop("10px");
			chartArea.setLeft("0px");
			chartArea.setWidth("100%");
			chartArea.setHeight("100%");
			// chartArea.setLeft("10%");
			options.setChartArea(chartArea);
		}

		VAxis vAxis0 = VAxis.create();
		vAxis0.setMinValue(0);
		vAxis0.setTextPosition(TextPosition.NONE);
		vAxis0.setGridlines(gridlines);
		vAxis0.setBaseline(1);
		vAxis0.setBaselineColor("grey");
		vAxis0.setTitle("eFt");
		options.setVAxis(0, vAxis0);

		VAxis vAxis1 = VAxis.create();
		vAxis1.setMinValue(0);
		vAxis1.setTextPosition(TextPosition.NONE);
		vAxis1.setGridlines(gridlines);
		vAxis1.setBaseline(1);
		vAxis1.setBaselineColor("grey");
		vAxis1.setTitle("%");
		options.setVAxis(1, vAxis1);
		
		HAxis hAxis = HAxis.create();
		hAxis.setTextPosition(TextPosition.OUT);
		hAxis.setGridlines(gridlines);
		hAxis.setBaseline(1);
		hAxis.setBaselineColor("grey");
		hAxis.setTitle("Tartózkodás");
		hAxis.setSlantedText(false);
		options.setHAxis(hAxis);

		options.setSeriesType(SeriesType.LINE);

		ComboChartSeries occActSeries = ComboChartSeries.create();
		occActSeries.setType(SeriesType.LINE);
		occActSeries.setColor("#0000ff");
		occActSeries.setLineWidth(1);
		occActSeries.setTargetAxisIndex(1);
		options.setSeries(0, occActSeries);

		ComboChartSeries adrActSeries = ComboChartSeries.create();
		adrActSeries.setType(SeriesType.LINE);
		adrActSeries.setColor("#ff0000");
		adrActSeries.setLineWidth(1);
		options.setSeries(1, adrActSeries);

		ComboChartSeries revparActSeries = ComboChartSeries.create();
		revparActSeries.setType(SeriesType.LINE);
		revparActSeries.setColor("#00ff00");
		revparActSeries.setLineWidth(1);
		options.setSeries(2, revparActSeries);

		ComboChartSeries occVsSeries = ComboChartSeries.create();
		occVsSeries.setType(SeriesType.AREA);
		occVsSeries.setColor("#ddddff");
		occVsSeries.setLineWidth(1);
		occVsSeries.setTargetAxisIndex(1);
		options.setSeries(3, occVsSeries);

		ComboChartSeries adrVsSeries = ComboChartSeries.create();
		adrVsSeries.setType(SeriesType.AREA);
		adrVsSeries.setColor("#ffdddd");
		adrVsSeries.setLineWidth(1);
		options.setSeries(4, adrVsSeries);

		ComboChartSeries revparVsSeries = ComboChartSeries.create();
		revparVsSeries.setType(SeriesType.AREA);
		revparVsSeries.setColor("#ddffdd");
		revparVsSeries.setLineWidth(1);
		options.setSeries(5, revparVsSeries);

		return options;
	}

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

	public void setSimpleView(Boolean isSimple) {
		LOGGER.log(Level.INFO, "setSimpleView()");
		if (this.simpleView != isSimple)
			fullRedraw = true;
		this.simpleView = isSimple;
	}

	public void setHeight(Integer height) {
		if (this.height != height)
			fullRedraw = true;
		this.height = height;
	}
}
