/**
 * 
 */
package io.crs.hsys.server.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.model.Registration;
import io.crs.hsys.server.security.OnRegistrationCompleteEvent;
import io.crs.hsys.server.service.AccountService;
import io.crs.hsys.server.service.AppUserService;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
@Controller
public class AppController {
	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	private final AccountService accountService;

	private final AppUserService appUserService;

	private final ApplicationEventPublisher eventPublisher;

	@Autowired
	AppController(AccountService accountService, AppUserService appUserService,
			ApplicationEventPublisher eventPublisher) {
		this.accountService = accountService;
		this.appUserService = appUserService;
		this.eventPublisher = eventPublisher;
	}
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		logger.info("signup()");
		model.addAttribute("registration", new Registration());
		return "signup";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute Registration registration, WebRequest request) {
		logger.info("registration()->registration=" + registration);
		AppUser appuser;
		try {
			appuser = accountService.register(registration);
			logger.debug("register->appuser=" + appuser);
			try {
				String appUrl = request.getContextPath();
				eventPublisher.publishEvent(new OnRegistrationCompleteEvent(appuser, request.getLocale(), appUrl));
				Locale locale = request.getLocale();
				logger.info("register->locale=" + locale);
				return "login";
			} catch (Exception e) {
				logger.info(e.toString());
				e.printStackTrace();
				return "signup";
			}
		} catch (EntityValidationException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return "signup";
		} catch (UniqueIndexConflictException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return "signup";
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return "signup";
		}
	}

	@RequestMapping(value = "/activate/{token}", method = GET)
	@ResponseBody
	public Boolean activate(@PathVariable String token) {
		try {
			appUserService.activate(token);
			return true;
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}
}
