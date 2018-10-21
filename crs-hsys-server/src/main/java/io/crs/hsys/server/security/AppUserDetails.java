/**
 * 
 */
package io.crs.hsys.server.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public class AppUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String username;
	private final AppUserDto appUserDto;
	private final List<GrantedAuthority> authorities;
	private final boolean accountNonLocked;

	public AppUserDetails(String username, AppUserDto appUserDto, List<GrantedAuthority> authorities,
			boolean accountNonLocked) {
		this.username = username;
		this.appUserDto = appUserDto;
		this.authorities = authorities;
		this.accountNonLocked = accountNonLocked;
	}

	public AppUserDto getAppUserDto() {
		return appUserDto;
	}

	@Override
	public String getPassword() {
		return appUserDto.getPassword();
	}

	@Override
	public String getUsername() {
		return appUserDto.getEmailAddress();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return appUserDto.getEnabled();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
}
