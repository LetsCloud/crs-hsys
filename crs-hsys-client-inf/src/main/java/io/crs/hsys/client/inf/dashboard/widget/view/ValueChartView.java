/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget.view;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.crs.hsys.client.inf.dashboard.widget.DataWidgetPresenter;
import io.crs.hsys.shared.cnst.cube.DataWidgetFieldType;
import io.crs.hsys.shared.dto.cube.DataWidgetValueM1Dto;

/**
 * @author CR
 *
 */
public class ValueChartView extends ValueOnlyView implements DataWidgetPresenter.DataWidgetView {

	private LineChartPanel chartPanel;
	
	@Inject
	ValueChartView() {
		super();
		init();
	}

	@Override
	protected void init() {
		super.init();
//		setWidgetHeight(CARD_H2X);
		chartPanel = new LineChartPanel();
		addContent(chartPanel);
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
	}
	
	@Override
	public void setWidth(String height1, String height2) {
		setWidth(height2);
	}

}
