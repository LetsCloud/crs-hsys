/**
 * 
 */
package io.crs.hsys.server.security.gae;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * @author robi
 *
 */
public class GaeAuthenticationFilter extends GenericFilterBean {
	private static final Logger logger = LoggerFactory.getLogger(GaeAuthenticationFilter.class);

	private static final String REGISTRATION_URL = "/register.htm";
	private AuthenticationDetailsSource ads = new WebAuthenticationDetailsSource();
	private AuthenticationManager authenticationManager;
	private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		logger.info("doFilter()->authentication=" + authentication);

		if (authentication == null) {
			// User isn't authenticated. Check if there is a Google Accounts user
			User googleUser = UserServiceFactory.getUserService().getCurrentUser();
			logger.info("doFilter()->googleUser=" + googleUser);

			if (googleUser != null) {
				// User has returned after authenticating through GAE. Need to authenticate to
				// Spring Security.
				PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(googleUser, null);
				logger.info("doFilter()->token=" + token);
				token.setDetails(ads.buildDetails(request));
				logger.info("doFilter()->token2=" + token);

				try {
					authentication = authenticationManager.authenticate(token);
					logger.info("doFilter()->authentication2=" + authentication);
					// Setup the security context
					SecurityContextHolder.getContext().setAuthentication(authentication);
					logger.info("doFilter()->authentication3=" + authentication);
					// Send new users to the registration page.
					if (authentication.getAuthorities().contains(AppRole.NEW_USER)) {
						((HttpServletResponse) response).sendRedirect(REGISTRATION_URL);
						return;
					}
				} catch (AuthenticationException e) {
					// Authentication information was rejected by the authentication manager
					failureHandler.onAuthenticationFailure((HttpServletRequest) request, (HttpServletResponse) response,
							e);
					return;
				}
			}
		}

		chain.doFilter(request, response);
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
		this.failureHandler = failureHandler;
	}
}