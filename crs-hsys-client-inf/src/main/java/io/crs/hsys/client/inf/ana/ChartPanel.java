/**
 * 
 */
package io.crs.hsys.client.inf.ana;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author CR
 *
 */
public interface ChartPanel extends IsWidget {
	
	void setTitle(String title);
	
	void setDataSeries(List<Double> actualValues);
	
	void setDataSeries(List<Double> actualValues, List<Double> baseValues);

	void setSimpleView(Boolean isSimple);

	void setHeight(Integer height);

	void redrawChart();
}
