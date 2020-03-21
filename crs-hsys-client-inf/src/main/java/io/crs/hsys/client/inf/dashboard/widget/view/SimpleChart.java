package io.crs.hsys.client.inf.dashboard.widget.view;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

public interface SimpleChart extends IsWidget {

	void setAxisSeriesData(List<String> axisValues, List<Double> actualValues);

	void setDataSeries(List<Double> factValues);

	void setDataSeries(List<Double> factValues, List<Double> baseValues);
	
	void redrawChart();

}
