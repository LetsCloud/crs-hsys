/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import io.crs.hsys.shared.cnst.RateSubject;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RateCodeDtor extends HotelChildDto {

	/**
	 * Az árkód egyedi azonosítója.
	 */
	private String code;

	/**
	 * Az árkód megnevezése.
	 */
	private String description;

	/**
	 * Az árkód típusa: Szoba, vendég.
	 */
	private RateSubject subject;

	/**
	 * 
	 */
	public RateCodeDtor() {
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

	public RateSubject getSubject() {
		return subject;
	}

	public void setSubject(RateSubject subject) {
		this.subject = subject;
	}
}
