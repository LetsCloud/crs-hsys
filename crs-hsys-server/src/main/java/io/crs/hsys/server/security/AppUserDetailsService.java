/**
 * 
 */
package io.crs.hsys.server.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.service.AppUserService;
import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public class AppUserDetailsService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(AppUserDetailsService.class);

	private final AppUserService userService;

	private LoginAttemptService loginAttemptService;

	private final ModelMapper modelMapper;

	AppUserDetailsService(AppUserService userService, LoginAttemptService loginAttemptService,
			ModelMapper modelMapper) {
		logger.info("AppUserDetailsService()");
		this.userService = userService;
		this.loginAttemptService = loginAttemptService;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
		logger.info("loadUserByUsername()->input=" + input);
		String ip = getRequestRemoteAddr();
		boolean accountNonLocked = !loginAttemptService.isBlocked(ip);
		/*
		 * String[] split = input.split(":"); if (split.length < 2) throw new
		 * UsernameNotFoundException("Must specify both username and corporate domain identifier"
		 * );
		 * 
		 * String username = split[0]; String corporateId = split[1];
		 */
		AppUser appUser = userService.getByEmail(input);
		logger.info("loadUserByUsername()->appUser=" + appUser);
		if (appUser == null)
			throw new UsernameNotFoundException("User not found");

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		/*
		 * List<String> permissions = userService.getPermissions(appUser.getUsername());
		 * 
		 * for (String permission : permissions) { grantedAuthorities.add(new
		 * SimpleGrantedAuthority(permission)); }
		 */
		AppUserDto dto = modelMapper.map(appUser, AppUserDto.class);
		AppUserDetails aud = new AppUserDetails(input, dto, grantedAuthorities, accountNonLocked);

		return aud;
	}

	/**
	 * 
	 * @return private String getClientIP() { String xfHeader =
	 *         request.getHeader("X-Forwarded-For"); if (xfHeader == null) { return
	 *         request.getRemoteAddr(); } return xfHeader.split(",")[0]; }
	 */

	public static String getRequestRemoteAddr() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		return request.getRemoteAddr();
	}
}
