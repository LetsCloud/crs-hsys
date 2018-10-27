/**
 * 
 */
package io.crs.hsys.server.security.gae;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.google.appengine.api.users.User;

/**
 * @author robi
 *
 */
public class GoogleAccountsAuthenticationProvider implements AuthenticationProvider {
	private static final Logger logger = LoggerFactory.getLogger(GoogleAccountsAuthenticationProvider.class);

	private UserRegistry userRegistry;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		logger.info("authenticate");
		User googleUser = (User) authentication.getPrincipal();
		logger.info("authenticate()->googleUser=" + googleUser);

		GaeUser user = userRegistry.findUser(googleUser.getUserId());
		logger.info("authenticate()->user=" + user);

		if (user == null) {
			// User not in registry. Needs to register
			user = new GaeUser(googleUser.getUserId(), googleUser.getNickname(), googleUser.getEmail());
			logger.info("authenticate()->user2=" + user);
		}

		if (!user.isEnabled()) {
			throw new DisabledException("Account is disabled");
		}

		return (Authentication) new GaeUserAuthentication(user, authentication.getDetails());
	}

	public final boolean supports(Class<?> authentication) {
		return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public void setUserRegistry(UserRegistry userRegistry) {
		this.userRegistry = userRegistry;
	}
}