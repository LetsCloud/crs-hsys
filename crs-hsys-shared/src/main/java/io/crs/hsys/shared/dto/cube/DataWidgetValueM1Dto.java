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
public class DataWidgetValueM1Dto extends AbstractDataWidgetValueDto {

	private String label;
	private Double value;

	public DataWidgetValueM1Dto() {
		super();
		this.value = 0d;
	}

	public DataWidgetValueM1Dto(Integer widgetIndex, DataWidgetFieldType fieldIndex) {
		super(widgetIndex, fieldIndex);
		this.value = 0d;
	}

	public DataWidgetValueM1Dto(Integer widgetIndex, DataWidgetFieldType fieldIndex, Double value) {
		super(widgetIndex, fieldIndex);
		this.value = value;
	}

	public DataWidgetValueM1Dto(Integer widgetIndex, DataWidgetFieldType fieldIndex, Double value, String label) {
		super(widgetIndex, fieldIndex);
		this.value = value;
		this.label = label;
	}

	public Double getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public void addValue(Double value) {
		this.value += value;
	}

	@Override
	public void setDimensionValues(List<String> dimensionValues) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addValue(Integer widgetIndex, DataWidgetFieldType fieldIndex, Double value) {
		addValue(value);
	}

	@Override
	public String toString() {
		String result = "DataWidgetValueM1Dto->" + super.toString() + ", value:" + value;
		return result;
	}
}
