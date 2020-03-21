/**
 * 
 */
package io.crs.hsys.client.inf.analytics.factory;

/**
 * @author CR
 *
 */
public class MeasureConfig extends FieldConfig {
	
	private String dataSource;

	private String periodType;
	
	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

}
