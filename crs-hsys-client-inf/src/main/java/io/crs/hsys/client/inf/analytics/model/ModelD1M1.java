/**
 * 
 */
package io.crs.hsys.client.inf.analytics.model;

import java.io.Serializable;

import gwt.material.design.client.data.HasDataCategory;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class ModelD1M1 implements HasDataCategory, Serializable {
	
	private String dimension1;
	
	private Double measure1;

	public ModelD1M1(String dimension1, Double measure1) {
		this.dimension1 = dimension1;
		this.measure1 = measure1;
	}
	
	public String getDimension1() {
		return dimension1;
	}


	public void setDimension1(String dimension1) {
		this.dimension1 = dimension1;
	}


	public Double getMeasure1() {
		return measure1;
	}


	public void setMeasure1(Double measure1) {
		this.measure1 = measure1;
	}


	@Override
	public String getDataCategory() {
		// TODO Auto-generated method stub
		return null;
	}

}
