/**
 * 
 */
package io.crs.hsys.shared.dto.profile;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class OrganizationDto extends ProfileDto {

	private String code;

	private String taxNumber;

	private String euTaxNumber;

	public OrganizationDto() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getEuTaxNumber() {
		return euTaxNumber;
	}

	public void setEuTaxNumber(String euTaxNumber) {
		this.euTaxNumber = euTaxNumber;
	}
}
