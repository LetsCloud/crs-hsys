/**
 * 
 */
package io.crs.hsys.server.entity.doc;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.server.entity.common.AccountChild;

/**
 * @author robi
 *
 */
@Entity
public class QuotationStatus extends AccountChild {
	public static final String PROPERTY_CODE = "code";

	@Index
	private String code;

	private String description;

	private Boolean active;

	public QuotationStatus() {
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "QuotationType [code=" + code + ", description=" + description + ", active=" + active + "]"
				+ super.toString();
	}

}
