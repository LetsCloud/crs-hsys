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
public class ContractType extends AccountChild {

	private String code;

	private String description;

	public ContractType() {
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
		return "ContractType [code=" + code + ", description=" + description + "]" + super.toString();
	}

}
