/**
 * 
 */
package io.crs.hsys.shared.dto.profile;

import java.util.Date;

import io.crs.hsys.shared.cnst.Gender;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class PersonDto extends ProfileDto {

	private StructuredName structuredName;

	private String salutation;

	private String language;

	private Date dateOfBirth;

	private Gender gender;

	public StructuredName getStructuredName() {
		return structuredName;
	}

	public void setStructuredName(StructuredName structuredName) {
		this.structuredName = structuredName;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
