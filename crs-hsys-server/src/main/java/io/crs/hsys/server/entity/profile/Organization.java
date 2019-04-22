/**
 * 
 */
package io.crs.hsys.server.entity.profile;

import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Subclass;

/**
 * @author CR
 *
 */
@Subclass(index = true)
public class Organization extends Profile {

	@Index
	private String code;

	private String taxNumber;

	private String euTaxNumber;

	public Organization() {
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

	@Override
	public String toString() {
		return "Organization:[code=" + code + ", taxNumber=" + taxNumber + ", euTaxNumber=" + euTaxNumber + "]>>"
				+ super.toString();
	}
}
