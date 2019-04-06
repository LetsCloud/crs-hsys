/**
 * 
 */
package io.crs.hsys.shared.dto.doc;

import io.crs.hsys.shared.dto.common.AccountChildDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class QuotationStatusDto extends AccountChildDto {
	private String code;

	private String description;

	public QuotationStatusDto() {
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
		return "QuotationStatusDto [code=" + code + ", description=" + description + "]";
	}
}
