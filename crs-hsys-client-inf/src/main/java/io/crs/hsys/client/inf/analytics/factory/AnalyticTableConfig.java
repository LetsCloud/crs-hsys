/**
 * 
 */
package io.crs.hsys.client.inf.analytics.factory;

import java.util.List;

/**
 * @author CR
 *
 */
public class AnalyticTableConfig {

	private List<DimensionConfig> dimensionConfigs;

	private List<MeasureConfig> measureConfigs;

	public List<DimensionConfig> getDimensionConfigs() {
		return dimensionConfigs;
	}

	public void setDimensionConfigs(List<DimensionConfig> dimensionConfigs) {
		this.dimensionConfigs = dimensionConfigs;
	}

	public List<MeasureConfig> getMeasureConfigs() {
		return measureConfigs;
	}

	public void setMeasureConfigs(List<MeasureConfig> measureConfigs) {
		this.measureConfigs = measureConfigs;
	}

}
