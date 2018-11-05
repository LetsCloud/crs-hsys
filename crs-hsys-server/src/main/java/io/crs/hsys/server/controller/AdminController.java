/**
 * 
 */
package io.crs.hsys.server.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.users.UserServiceFactory;

import static io.crs.hsys.shared.api.ApiPaths.AdminV1.ADMIN;
import static io.crs.hsys.shared.api.ApiPaths.AdminV1.LOGOUT;
import static io.crs.hsys.shared.api.ApiPaths.AdminV1.LOGGEDOUT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

/**
 * @author robi
 *
 */
@Controller
@RequestMapping(value = ADMIN + ROOT)
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value = LOGOUT, method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();

		String logoutUrl = UserServiceFactory.getUserService().createLogoutURL(ADMIN + ROOT + LOGGEDOUT);

		response.sendRedirect(logoutUrl);
	}

	@RequestMapping(value = LOGGEDOUT, method = RequestMethod.GET)
	public String loggedOut() {
		return "loggedout";
	}

}
