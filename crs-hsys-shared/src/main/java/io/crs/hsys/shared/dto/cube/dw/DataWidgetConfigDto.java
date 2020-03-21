/**
 * 
 */
package io.crs.hsys.shared.dto.cube.dw;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.shared.cnst.cube.DataWidgetType;
import io.crs.hsys.shared.dto.common.AccountChildDto;

/**
 * A Dashboard DataWidget konfigjait tároló entitás DTO-ja.
 * 
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class DataWidgetConfigDto extends AccountChildDto {

	/**
	 * Dashboardon elfoglalt pozíció (sorrend).
	 */
	private Integer widgetIndex;

	/**
	 * A DataWidget típusa.
	 */
	private DataWidgetType type;

	/**
	 * Felirat a DataWidget felső részén jelenik.
	 */
	private String caption;

	/**
	 * 
	 */
	private Integer sizeX;

	/**
	 * 
	 */
	private Integer sizeY;

	/**
	 * A DataWidget mezői. Azok az elemek, amelyek valamely mutató értékét
	 * jelenítik meg. DataWidget típusonként eltérő a mezők száma.
	 */
	private List<DataWidgetFieldDto> fields = new ArrayList<DataWidgetFieldDto>();

	public DataWidgetConfigDto() {
	}

	public DataWidgetConfigDto(Integer index, DataWidgetType type, String caption) {
		this();
		this.widgetIndex = index;
		this.type = type;
		this.caption = caption;
	}

	public DataWidgetConfigDto(Integer index, DataWidgetType type, String caption, Integer sizeX, Integer sizeY) {
		this(index, type, caption);
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public Integer getWidgetIndex() {
		return widgetIndex;
	}

	public void setWidgetIndex(Integer index) {
		this.widgetIndex = index;
	}

	public DataWidgetType getType() {
		return type;
	}

	public void setType(DataWidgetType type) {
		this.type = type;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Integer getSizeX() {
		return sizeX;
	}

	public void setSizeX(Integer sizeX) {
		this.sizeX = sizeX;
	}

	public Integer getSizeY() {
		return sizeY;
	}

	public void setSizeY(Integer sizeY) {
		this.sizeY = sizeY;
	}

	public List<DataWidgetFieldDto> getFields() {
		return fields;
	}

	public void setFields(List<DataWidgetFieldDto> fields) {
		this.fields = fields;
	}

	public void addField(DataWidgetFieldDto field) {
		this.fields.add(field);
	}

}
