/**
 * 
 */
package io.crs.hsys.shared.dto.common;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.shared.cnst.UserPerm;
import io.crs.hsys.shared.dto.hotel.HotelDtor;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class AppUserDto extends AccountChildDto {

	/**
	 * Kód
	 */
	private String code;

	/**
	 * Név
	 */
	private String name;

	/**
	 * Beosztás
	 */
	private String title;

	/**
	 * Fotó
	 */
	private String picture;

	/**
	 * Bejelentkezőnév
	 */
	private String username;

	/**
	 * Hash-elt jelszó
	 */
	private String password;

	/**
	 * Email cím
	 */
	private String emailAddress;

	/**
	 * Engedélyezett
	 */
	private Boolean enabled;

	/**
	 * Rendszergazda
	 */
	private Boolean admin;

	/**
	 * Felhasználói csoportok
	 */
	private List<UserGroupDto> userGroups = new ArrayList<UserGroupDto>();

	/**
	 * Felhasználói jogok
	 */
	private List<UserPerm> permissions = new ArrayList<UserPerm>();

	/**
	 * Szerepkörök
	 */
	private List<RoleDto> roles = new ArrayList<RoleDto>();

	/**
	 * Alapértelmezett hotel
	 */
	private HotelDtor defaultHotel;

	/**
	 * Engedélyezett hotelek
	 */
	private List<HotelDtor> availableHotels = new ArrayList<HotelDtor>();

	/**
	 * Firebase Messaging Token
	 */
	private List<FcmTokenDto> fcmTokens = new ArrayList<FcmTokenDto>();

	public AppUserDto() {
	}

	public AppUserDto(String picture, Boolean enabled, String name, String emailAddress) {
		this();
		this.picture = picture;
		this.enabled = enabled;
		this.name = name;
		this.emailAddress = emailAddress;
		this.picture = picture;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

	public HotelDtor getDefaultHotel() {
		return defaultHotel;
	}

	public void setDefaultHotel(HotelDtor defaultHotel) {
		this.defaultHotel = defaultHotel;
	}

	public List<HotelDtor> getAvailableHotels() {
		return availableHotels;
	}

	public void setAvailableHotels(List<HotelDtor> availableHotels) {
		this.availableHotels = availableHotels;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<UserGroupDto> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UserGroupDto> userGroups) {
		this.userGroups = userGroups;
	}

	public List<FcmTokenDto> getFcmTokens() {
		return fcmTokens;
	}

	public void setFcmTokens(List<FcmTokenDto> fcmTokens) {
		this.fcmTokens = fcmTokens;
	}

	public List<UserPerm> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<UserPerm> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		String ret = "AppUserDto:{name=" + name + ", username=" + username + ", password=" + password
				+ ", emailAddress=" + emailAddress + ", picture=" + picture + ", enabled=" + enabled + ", admin="
				+ admin + ", roles=" + roles + ", userGroups=" + userGroups + ", defaultHotel=" + defaultHotel
				+ ", permissions=" + permissions + ", " + super.toString() + "}";
		return ret;
	}
}
