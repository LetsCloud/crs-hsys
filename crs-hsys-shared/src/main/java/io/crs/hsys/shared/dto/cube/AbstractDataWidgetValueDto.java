/**
 * 
 */
package io.crs.hsys.shared.dto.cube;

import io.crs.hsys.shared.cnst.cube.DataWidgetFieldType;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractDataWidgetValueDto implements CubeResultDto {

	private Integer widgetIndex;

	private DataWidgetFieldType fieldType;

	public AbstractDataWidgetValueDto() {
	}

	public AbstractDataWidgetValueDto(Integer widgetIndex, DataWidgetFieldType fieldIndex) {
		this.widgetIndex = widgetIndex;
		this.fieldType = fieldIndex;
	}

	public Integer getWidgetIndex() {
		return widgetIndex;
	}

	public void setWidgetIndex(Integer widgetIndex) {
		this.widgetIndex = widgetIndex;
	}

	public DataWidgetFieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(DataWidgetFieldType fieldIndex) {
		this.fieldType = fieldIndex;
	}

	@Override
	public String toString() {
		String result = "AbstractDataWidgetValueDto->widgetIndex:" + widgetIndex + ", fieldType:" + fieldType;
		return result;
	}

}
