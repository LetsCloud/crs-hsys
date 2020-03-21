/**
 * 
 */
package io.crs.hsys.client.inf.analytics.model;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class ModelD2M1 extends ModelD1M1 {
	
	private String dimension2;

	public String getDimension2() {
		return dimension2;
	}

	public void setDimension2(String dimension2) {
		this.dimension2 = dimension2;
	}

	public ModelD2M1(String dimension1, Double measure1) {
		super(dimension1, measure1);
	}

}
