/**
 * 
 */
package io.crs.hsys.shared.dto.common;

import io.crs.hsys.shared.dto.Dto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RegisterDto implements Dto {

//	private String accountWebSafeKey;

	private Long accountId;

	private String accountName;

	private String street;

	private String city;

	private String postcode;

	private String country;

	private String username;

	private String userPassword;

	private String confPassword;

	private String userEmail;

	private String confEmail;

	public RegisterDto() {
	}

	public RegisterDto(String accountName, String street, String city, String postcode, String country, String username,
			String userPassword, String confPassword, String userEmail, String confEmail) {
		super();
		this.accountName = accountName;
		this.street = street;
		this.city = city;
		this.postcode = postcode;
		this.country = country;
		this.username = username;
		this.userPassword = userPassword;
		this.confPassword = confPassword;
		this.userEmail = userEmail;
		this.confEmail = confEmail;
	}
/*
	public String getAccountWebSafeKey() {
		return accountWebSafeKey;
	}

	public void setAccountWebSafeKey(String accountWebSafeKey) {
		this.accountWebSafeKey = accountWebSafeKey;
	}
*/
	
	public String getAccountName() {
		return accountName;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getConfEmail() {
		return confEmail;
	}

	public void setConfEmail(String confEmail) {
		this.confEmail = confEmail;
	}
	/*
	 * public AccountDto getAccountDto() { PostalAddressDto postalAddressDto =
	 * new PostalAddressDto(); postalAddressDto.setPrimary(true);
	 * postalAddressDto.setLabel(PostalAddressDto.Label.WORK);
	 * postalAddressDto.setStreet(street); postalAddressDto.setCity(city);
	 * postalAddressDto.setPostcode(postcode);
	 * postalAddressDto.setCountry(country);
	 * 
	 * AccountDto account = new AccountDto(); account.setName(accountName);
	 * account.setPostalAddress(postalAddressDto);
	 * 
	 * return account; }
	 * 
	 * public UserDto getUserDto() { UserDto userDto = new UserDto();
	 * userDto.setUsername(username); userDto.setPassword(userPassword);
	 * userDto.setEmailAddress(userEmail);
	 * 
	 * return userDto; }
	 */
}
