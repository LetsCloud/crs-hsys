/**
 * 
 */
package io.crs.hsys.shared.dto.profile;

import io.crs.hsys.shared.dto.Dto;

/**
 * Strukturált személynév.
 * 
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class StructuredName implements Dto {

	/**
	 * Előtag.
	 */
	private String prefix;

	/**
	 * Utónév.
	 */
	private String firstName;

	/**
	 * Középnév.
	 */
	private String middleName;

	/**
	 * Vezetéknév.
	 */
	private String lastName;

	/**
	 * 
	 */
	private String sufix;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSufix() {
		return sufix;
	}

	public void setSufix(String sufix) {
		this.sufix = sufix;
	}
}
