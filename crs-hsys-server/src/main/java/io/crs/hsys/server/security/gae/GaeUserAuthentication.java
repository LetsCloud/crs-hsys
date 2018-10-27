/**
 * 
 */
package io.crs.hsys.server.security.gae;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author robi
 *
 */
public class GaeUserAuthentication implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(GaeUserAuthentication.class);

	private final GaeUser user;
	private final Object details;

	public GaeUserAuthentication(GaeUser user, Object details) {
		logger.info("GaeUserAuthentication()->user=" + user);
		logger.info("GaeUserAuthentication()->details=" + details);
		this.user = user;
		this.details = details;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("loadUserByUsername()->username=" + username);
		logger.info("loadUserByUsername()->user=" + user);
		logger.info("loadUserByUsername()->details=" + details);
		return null;
	}

}
