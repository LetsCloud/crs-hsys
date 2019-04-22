/**
 * 
 */
package io.crs.hsys.server.entity.profile;

import java.util.Date;

import com.googlecode.objectify.annotation.Subclass;

import io.crs.hsys.shared.cnst.Gender;
import io.crs.hsys.shared.dto.profile.StructuredName;

/**
 * @author CR
 *
 */
@Subclass(index = true)
public class Person extends Profile {

	private StructuredName structuredName;

	private String salutation;

	private String language;

	private Date dateOfBirth;

	private Gender gender;

	public Person() {
	}

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

	/**
	 * 
	 * @author CR
	 *
	 */
	public static class Builder extends Profile.Builder {

		private StructuredName structuredName;

		private String salutation;

		private String language;

		private Date dateOfBirth;

		private Gender gender;

		public Builder() {
		}

		public Builder structuredName(StructuredName structuredName) {
			this.structuredName = structuredName;
			return this;
		}

		public Builder salutation(String salutation) {
			this.salutation = salutation;
			return this;
		}

		public Builder language(String language) {
			this.language = language;
			return this;
		}

		public Builder dateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}

		public Builder gender(Gender gender) {
			this.gender = gender;
			return this;
		}

		public Person build() {
			return new Person(this);
		}
	}

	protected Person(Builder builder) {
		super(builder);
		this.dateOfBirth = builder.dateOfBirth;
		this.gender = builder.gender;
		this.language = builder.language;
		this.salutation = builder.salutation;
		this.structuredName = builder.structuredName;
	}
}