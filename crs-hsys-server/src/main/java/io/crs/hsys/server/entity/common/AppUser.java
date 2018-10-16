/**
 * 
 */
package io.crs.hsys.server.entity.common;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.server.entity.hotel.Hotel;
import io.crs.hsys.shared.dto.common.RegisterDto;

/**
 * @author robi
 *
 */
@Entity
public class AppUser extends AccountChild {
	private static final Logger logger = LoggerFactory.getLogger(AppUser.class.getName());

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
	@Index
	private String username;

	/**
	 * Hash-elt jelszó
	 */
	private String password;

	/**
	 * Email cím
	 */
	@Index
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
	private List<Ref<UserGroup>> userGroups = new ArrayList<Ref<UserGroup>>();

	/**
	 * Szerepkörök
	 */
	private List<Ref<Role>> roles = new ArrayList<Ref<Role>>();

	/**
	 * Alapértelmezett hotel
	 */
	private Ref<Hotel> defaultHotel;

	/**
	 * Engedélyezett hotelek
	 */
	private List<Ref<Hotel>> availableHotels = new ArrayList<Ref<Hotel>>();

	/**
	 * Firebase Messaging Token
	 */
	private List<FcmToken> fcmTokens = new ArrayList<FcmToken>();

	/**
	 * Verifikációs tóken
	 */
	private List<VerificationToken> verificationTokens;

	/**
	 * Paraméter nélküli kontruktor Objectify-hoz
	 */
	public AppUser() {
		logger.info("AppUser()");
		this.enabled = false;
		this.admin = false;
	}

	/**
	 * Kontruktor RegisterDTO-ból
	 * 
	 * @param registerDto
	 */
	public AppUser(RegisterDto registerDto) {
		this();
		setUsername(registerDto.getUsername());
		setPassword(registerDto.getUserPassword());
		setEmailAddress(registerDto.getUserEmail());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		logger.info("setCode()");
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		logger.info("setName()");
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		logger.info("setTitle()");
		this.title = title;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		logger.info("setPicture()");
		this.picture = picture;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		logger.info("setUsername()");
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String hashedPassword) {
		logger.info("setPassword()");
		this.password = hashedPassword;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		logger.info("setEmailAddress()");
		this.emailAddress = emailAddress;
	}

	public List<VerificationToken> getVerificationTokens() {
		return verificationTokens;
	}

	public void setVerificationTokens(List<VerificationToken> verificationTokens) {
		logger.info("setVerificationTokens()");
		this.verificationTokens = verificationTokens;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		logger.info("setEnabled()");
		this.enabled = enabled;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		logger.info("setAdmin()");
		this.admin = admin;
	}

	public List<Role> getRoles() {
		List<Role> list = new ArrayList<Role>();
		for (Ref<Role> ref : roles) {
			list.add(ref.get());
		}
		return list;
	}

	public void setRoles(List<Role> roles) {
		logger.info("setRoles()");
		ArrayList<Ref<Role>> list = new ArrayList<Ref<Role>>();
		for (Role role : roles) {
			list.add(Ref.create(role));
		}
		this.roles = list;
	}

	public List<Hotel> getAvailableHotels() {
		List<Hotel> list = new ArrayList<Hotel>();
		for (Ref<Hotel> ref : availableHotels) {
			list.add(ref.get());
		}
		return list;
	}

	public void setAvailableHotels(List<Hotel> accessibleHotels) {
		logger.info("setAvailableHotels()");
		ArrayList<Ref<Hotel>> list = new ArrayList<Ref<Hotel>>();
		for (Hotel hotel : accessibleHotels) {
			list.add(Ref.create(hotel));
		}
		this.availableHotels = list;
	}

	public Hotel getDefaultHotel() {
		try {
			return defaultHotel.get();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public void setDefaultHotel(Hotel defaultHotel) {
		logger.info("setDefaultHotel()->defaultHotel=" + defaultHotel);
		if (defaultHotel.getId() == null)
			return;
		this.defaultHotel = Ref.create(defaultHotel);
	}

	public List<FcmToken> getFcmTokens() {
		return fcmTokens;
	}

	public void setFcmTokens(List<FcmToken> fcmTokens) {
		logger.info("setFcmTokens()");
		this.fcmTokens = fcmTokens;
	}

	public List<UserGroup> getUserGroups() {
		List<UserGroup> list = new ArrayList<UserGroup>();
		for (Ref<UserGroup> ref : userGroups) {
			list.add(ref.get());
		}
		return list;
	}

	public void setUserGroups(List<UserGroup> userGroups) {
		logger.info("setUserGroups()");
		ArrayList<Ref<UserGroup>> list = new ArrayList<Ref<UserGroup>>();
		for (UserGroup userGroup : userGroups) {
			list.add(Ref.create(userGroup));
		}
		this.userGroups = list;
	}

	@Override
	public String toString() {
		String ret = "User:{" + super.toString() + ", username=" + username + ", password=" + password
				+ ", emailAddress=" + emailAddress + ", enabled=" + enabled + ", admin=" + admin + "}";
		return ret;
	}

	public static List<AppUser> ref2EntityList(List<Ref<AppUser>> refList) {
		List<AppUser> list = new ArrayList<AppUser>();
		for (Ref<AppUser> ref : refList) {
			list.add(ref.get());
		}
		return list;
	}

	public static List<Ref<AppUser>> entity2RefList(List<AppUser> entityList) {
		List<Ref<AppUser>> list = new ArrayList<Ref<AppUser>>();
		for (AppUser entity : entityList) {
			list.add(Ref.create(entity));
		}
		return list;
	}
}
