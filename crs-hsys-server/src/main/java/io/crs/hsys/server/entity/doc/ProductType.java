/**
 * 
 */
package io.crs.hsys.server.entity.doc;

import com.googlecode.objectify.annotation.Entity;

import io.crs.hsys.server.entity.common.AccountChild;

/**
 * @author robi
 *
 */
@Entity
public class ProductType extends AccountChild {
	public static final String PROPERTY_CODE = "code";

	private String code;

	private String description;

	public ProductType() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProductType [code=" + code + ", description=" + description + "]" + super.toString();
	}

}
