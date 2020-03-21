/**
 * 
 */
package io.crs.hsys.shared.dto.cube;

import java.util.List;

import io.crs.hsys.shared.cnst.cube.DataWidgetFieldType;
import io.crs.hsys.shared.dto.Dto;

/**
 * @author CR
 *
 */
public interface CubeResultDto extends Dto {

	void setDimensionValues(List<String> dimensionValues);

	void setWidgetIndex(Integer widgetIndex);

	void setFieldType(DataWidgetFieldType fieldType);

	void addValue(Integer widgetIndex, DataWidgetFieldType fieldType, Double value);

}
