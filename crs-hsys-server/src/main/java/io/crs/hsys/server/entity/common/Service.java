/**
 * 
 */
package io.crs.hsys.server.entity.common;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.shared.cnst.ServiceType;

/**
 * @author CR
 *
 */
@Entity
public class Service extends AccountChild {

	/**
	 * Egyedi azonosító
	 */
	@Index
	private String code;

	/**
	 * Megnevezés
	 */
	private String description;

	private ServiceType type;

	/**
	 * ÁFA kód
	 */
	private String vatCode;

	public Service() {
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

	public ServiceType getType() {
		return type;
	}

	public void setType(ServiceType type) {
		this.type = type;
	}

	public String getVatCode() {
		return vatCode;
	}

	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}

}
