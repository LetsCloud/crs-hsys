/**
 * 
 */
package io.crs.hsys.server.security;

import org.modelmapper.ModelMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.security.gae.GaeUser;
import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * A Spring Security context-be bejelentkezett felhasználó lekérdezését szolgáló
 * osztály
 * 
 * @author robi
 *
 */
public class LoggedInChecker {
//	private static final Logger logger = LoggerFactory.getLogger(LoggedInChecker.class);

	private final ModelMapper modelMapper;

	public LoggedInChecker(ModelMapper modelMapper) {
//		logger.info("LoggedInChecker()");
		this.modelMapper = modelMapper;
	}

	/**
	 * Visszaadja a bejelentkezett felhasználó adatait AppUser entitás formájában
	 * 
	 * @return
	 */
	public AppUser getLoggedInUser() {
		AppUser appUser = null;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();

			// principal can be "anonymousUser" (String)
			if (principal instanceof AppUserDetails) {
				AppUserDetails userDetails = (AppUserDetails) principal;
				AppUserDto dto = userDetails.getAppUserDto();
				try {
					appUser = modelMapper.map(dto, AppUser.class);
					return appUser;
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}

			// GaeUser esetén
			if (principal instanceof GaeUser) {
				GaeUser gaeUser = (GaeUser) principal;
				appUser = new AppUser();
				appUser.setEmailAddress(gaeUser.getEmail());
				appUser.setName(gaeUser.getNickname());
				return appUser;
			}
		}
		return appUser;
	}
}
