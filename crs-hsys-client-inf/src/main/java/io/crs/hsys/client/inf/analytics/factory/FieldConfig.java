/**
 * 
 */
package io.crs.hsys.client.inf.analytics.factory;

/**
 * @author CR
 *
 */
public abstract class FieldConfig {
	
	private String fieldName;
	
	private String caption;
	
	private Integer index;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}
