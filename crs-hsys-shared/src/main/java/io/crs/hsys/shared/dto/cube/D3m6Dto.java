/**
 * 
 */
package io.crs.hsys.shared.dto.cube;

import java.util.List;

import io.crs.hsys.shared.cnst.cube.DataWidgetFieldType;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class D3m6Dto implements CubeResultDto {

	private String d1;

	private String d2;

	private String d3;

	private Double m1;

	private Double m2;

	private Double m3;

	private Double m4;

	private Double m5;

	private Double m6;

	public D3m6Dto() {
	}

	public D3m6Dto(List<String> dimensionValues) {
		setDimensionValues(dimensionValues);
	}

	public String getD1() {
		return d1;
	}

	public void setD1(String d1) {
		this.d1 = d1;
	}

	public String getD2() {
		return d2;
	}

	public void setD2(String d2) {
		this.d2 = d2;
	}

	public String getD3() {
		return d3;
	}

	public void setD3(String d3) {
		this.d3 = d3;
	}

	public Double getM1() {
		return m1;
	}

	public void setM1(Double m1) {
		this.m1 = m1;
	}

	public Double getM2() {
		return m2;
	}

	public void setM2(Double m2) {
		this.m2 = m2;
	}

	public Double getM3() {
		return m3;
	}

	public void setM3(Double m3) {
		this.m3 = m3;
	}

	public Double getM4() {
		return m4;
	}

	public void setM4(Double m4) {
		this.m4 = m4;
	}

	public Double getM5() {
		return m5;
	}

	public void setM5(Double m5) {
		this.m5 = m5;
	}

	public Double getM6() {
		return m6;
	}

	public void setM6(Double m6) {
		this.m6 = m6;
	}

	@Override
	public void addValue(Integer widgetIndex, DataWidgetFieldType fieldIndex, Double value) {
		switch (fieldIndex.getIndex()) {
		case 0:
			m1 += value;
			break;
		case 1:
			m2 += value;
			break;
		case 2:
			m3 += value;
			break;
		case 3:
			m4 += value;
			break;
		case 4:
			m5 += value;
			break;
		case 5:
			m6 += value;
			break;
		}
	}

	@Override
	public void setDimensionValues(List<String> dimensionValues) {
		for (int i = 0; i < dimensionValues.size(); i++) {
			switch (i) {
			case 0:
				this.d1 = dimensionValues.get(i);
			case 1:
				this.d2 = dimensionValues.get(i);
			case 2:
				this.d3 = dimensionValues.get(i);
			}
		}
		m1 = m2 = m3 = m4 = m5 = m6 = 0d;
	}

	@Override
	public void setWidgetIndex(Integer index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFieldType(DataWidgetFieldType fieldType) {
		// TODO Auto-generated method stub
		
	}
}
