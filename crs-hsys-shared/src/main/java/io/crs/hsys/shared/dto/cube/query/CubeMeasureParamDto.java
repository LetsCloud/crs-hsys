/**
 * 
 */
package io.crs.hsys.shared.dto.cube.query;

import io.crs.hsys.shared.cnst.cube.Aggregation;
import io.crs.hsys.shared.cnst.cube.DataSource;
import io.crs.hsys.shared.cnst.cube.DataWidgetFieldType;
import io.crs.hsys.shared.cnst.cube.Dimension;
import io.crs.hsys.shared.cnst.cube.Measure;
import io.crs.hsys.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class CubeMeasureParamDto implements Dto {

	private Integer widgetIndex;

	private DataWidgetFieldType fieldIndex;

	private DataSource dataSource;

	private Dimension periodField;

	private Aggregation aggrFunc;

	private Measure measure1;

	private Measure measure2;

	private Boolean basisValue;

	public CubeMeasureParamDto() {
		widgetIndex = 0;
		periodField = Dimension.BSNS_DATE;
		aggrFunc = Aggregation.SUM;
		basisValue = false;
	}

	public CubeMeasureParamDto(DataWidgetFieldType fieldIndex, DataSource dataSource, Measure measure1) {
		this();
		this.fieldIndex = fieldIndex;
		this.dataSource = dataSource;
		this.measure1 = measure1;
	}

	public CubeMeasureParamDto(Integer widgetIndex, DataWidgetFieldType fieldIndex, DataSource dataSource,
			Measure measure1) {
		this(fieldIndex, dataSource, measure1);
		this.widgetIndex = widgetIndex;
	}

	public CubeMeasureParamDto(Integer widgetIndex, DataWidgetFieldType fieldIndex, DataSource dataSource,
			Measure measure1, Measure measure2) {
		this(widgetIndex, fieldIndex, dataSource, measure1);
		this.measure2 = measure2;
	}

	public Integer getWidgetIndex() {
		return widgetIndex;
	}

	public void setWidgetIndex(Integer widgetIndex) {
		this.widgetIndex = widgetIndex;
	}

	public DataWidgetFieldType getFieldIndex() {
		return fieldIndex;
	}

	public void setFieldIndex(DataWidgetFieldType fieldIndex) {
		this.fieldIndex = fieldIndex;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Dimension getPeriodField() {
		return periodField;
	}

	public void setPeriodField(Dimension periodField) {
		this.periodField = periodField;
	}

	public Measure getMeasure1() {
		return measure1;
	}

	public void setMeasure1(Measure measure1) {
		this.measure1 = measure1;
	}

	public Measure getMeasure2() {
		return measure2;
	}

	public void setMeasure2(Measure measure2) {
		this.measure2 = measure2;
	}

	public Aggregation getAggrFunc() {
		return aggrFunc;
	}

	public void setAggrFunc(Aggregation aggrFunc) {
		this.aggrFunc = aggrFunc;
	}

	public Boolean getBasisValue() {
		return basisValue;
	}

	public void setBasisValue(Boolean basisValue) {
		this.basisValue = basisValue;
	}

	@Override
	public String toString() {
		String result = "CubeMeasureParamDto=[widgetIndex:" + widgetIndex + ", fieldIndex:" + fieldIndex
				+ ", dataSource:" + dataSource + ", periodField:" + periodField + ", aggrFunc:" + aggrFunc
				+ ", measure1:" + measure1 + ", measure2:" + measure2 + ", basisValue:" + basisValue + "]";
		return result;
	}

}
