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

import io.crs.hsys.server.entity.GlobalConfig;
import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.model.Registration;
import io.crs.hsys.server.security.OnRegistrationCompleteEvent;
import io.crs.hsys.server.service.AccountService;
import io.crs.hsys.server.service.AppUserService;
import io.crs.hsys.server.service.GlobalConfigService;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
@Controller
public class AppController {
	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	private final GlobalConfigService globalConfigService;

	private final AccountService accountService;

	private final AppUserService appUserService;

	private final ApplicationEventPublisher eventPublisher;

	@Autowired
	AppController(GlobalConfigService globalConfigService, AccountService accountService, AppUserService appUserService,
			ApplicationEventPublisher eventPublisher) {
		this.globalConfigService = globalConfigService;
		this.accountService = accountService;
		this.appUserService = appUserService;
		this.eventPublisher = eventPublisher;
	}


    @ModelAttribute(value = "globalConfig")
    public GlobalConfig construct() {
        return new GlobalConfig();
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

	@RequestMapping("/globalconfig")
	public String globalConfig(Model model) {
		logger.info("globalConfig()");
		globalConfigService.checkParams();
		model.addAttribute("globalConfigs", globalConfigService.getParams());
		return "globalconfig";
	}

	@RequestMapping(value = "globalconfig/load/{webSafeKey}", method = GET)
	public GlobalConfig loadGlobalConfig(@PathVariable String webSafeKey) {
		logger.info("loadGlobalConfig()->webSafeKey=" + webSafeKey);
		try {
			return globalConfigService.read(webSafeKey);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}

	@ResponseBody
	@PostMapping("globalconfig/update}")
	public void updateGlobalConfig(@ModelAttribute("globalConfig") GlobalConfig globalConfig) {
		logger.info("updateGlobalConfig()->globalConfig=" + globalConfig);
		try {
			globalConfigService.update(globalConfig);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/editGlobalConfig/{webSafeKey}", method = GET)
	public String editGlobalConfig(@PathVariable String webSafeKey) {
		logger.info("editGlobalConfig()->webSafeKey=" + webSafeKey);
		return "globalconfig";
	}
}
