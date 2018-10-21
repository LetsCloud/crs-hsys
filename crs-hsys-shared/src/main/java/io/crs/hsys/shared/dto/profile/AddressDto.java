/**
 * 
 */
package io.crs.hsys.shared.dto.profile;

import com.google.common.base.Strings;

import io.crs.hsys.shared.constans.PostalAddressLabel;
import io.crs.hsys.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class AddressDto implements Dto {

	private PostalAddressLabel label;

	/**
	 * Elsődleges postacím.
	 */
	private Boolean primary;

	/**
	 * Utca, házszám.
	 */
	private String street;

	/**
	 * Város.
	 */
	private String city;

	/**
	 * Postai irányítószám.
	 */
	private String postcode;

	/**
	 * Régió, állam, tartomány
	 */
	private String region;

	/**
	 * Ország.
	 */
	private String country;

	/**
	 * Formázott cím.
	 */
	private String fullAddress;

	public PostalAddressLabel getLabel() {
		return label;
	}

	public void setLabel(PostalAddressLabel label) {
		this.label = label;
	}

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFullAddress() {
		StringBuilder sb = new StringBuilder();

		if (!Strings.isNullOrEmpty(country))
			sb.append(country);

		if (!Strings.isNullOrEmpty(postcode)) {
			if (sb.length() != 0)
				sb.append(", ");
			sb.append(postcode);
		}

		if (!Strings.isNullOrEmpty(city)) {
			if (sb.length() != 0)
				sb.append(" ");
			sb.append(city);
		}

		if (!Strings.isNullOrEmpty(street)) {
			if (sb.length() != 0)
				sb.append(", ");
			sb.append(street);
		}

		fullAddress = sb.toString();
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
}
