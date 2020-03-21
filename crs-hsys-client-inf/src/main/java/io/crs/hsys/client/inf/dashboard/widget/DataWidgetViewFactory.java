/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget;

import com.google.inject.name.Named;

import io.crs.hsys.shared.cnst.cube.DataWidgetType;

/**
 * @author CR
 *
 */
public interface DataWidgetViewFactory {
	@Named(DataWidgetType.Cnst.VALUE_ONLY)
	DataWidgetPresenter.DataWidgetView getValueOnly();

	@Named(DataWidgetType.Cnst.VALUE_DELTA)
	DataWidgetPresenter.DataWidgetView getValueChange();

	@Named(DataWidgetType.Cnst.VALUE_CHART)
	DataWidgetPresenter.DataWidgetView getValueChart();

	@Named(DataWidgetType.Cnst.VALUE_DELTA_CHART)
	DataWidgetPresenter.DataWidgetView getValueChangeChart();

	@Named(DataWidgetType.Cnst.VALUE_DELTA_CHART2)
	DataWidgetPresenter.DataWidgetView getValueChangeChart2();

	@Named(DataWidgetType.Cnst.MULTI_VALUE)
	DataWidgetPresenter.DataWidgetView getMultiMeasure();

	@Named(DataWidgetType.Cnst.GEO_CHART)
	DataWidgetPresenter.DataWidgetView getGeoView();
}
