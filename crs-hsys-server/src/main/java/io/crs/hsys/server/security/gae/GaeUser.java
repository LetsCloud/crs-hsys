/**
 * 
 */
package io.crs.hsys.server.security.gae;

import java.io.Serializable;
import java.util.Set;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class GaeUser implements Serializable {
	private String userId;
	private String email;
	private String nickname;
	private String forename;
	private String surname;
	private Set<AppRole> authorities;
	private boolean enabled;

	public GaeUser(String userId, String nickname, String email) {
		this.userId = userId;
		this.nickname = nickname;
		this.email = email;
	}

	public GaeUser(String userId, String nickname, String email, String forename, String surname,
			Set<AppRole> authorities, boolean enabled) {
		this(userId, nickname, email);

		this.forename = forename;
		this.surname = surname;
		this.authorities = authorities;
		this.enabled = enabled;
	}

	public String getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public String getNickname() {
		return nickname;
	}

	public String getForename() {
		return forename;
	}

	public String getSurname() {
		return surname;
	}

	public Set<AppRole> getAuthorities() {
		return authorities;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String toString() {
		return "GaeUser [userId=" + userId + ", email=" + email + ", nickname=" + nickname + ", forename=" + forename
				+ ", surname=" + surname + ", authorities=" + authorities + ", enabled=" + enabled + "]";
	}

}
