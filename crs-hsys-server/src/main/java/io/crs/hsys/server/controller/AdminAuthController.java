/**
 * 
 */
package io.crs.hsys.server.controller;

import static io.crs.hsys.shared.api.ApiPaths.SpaV1.CURRENTUSER;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.IS_LOGGED_IN;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.LOGIN;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiPaths.AdminV1.ADMIN;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.service.AppUserService;
import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.exception.ExceptionType;
import io.crs.hsys.shared.exception.RestApiException;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ADMIN + ROOT + LOGIN, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminAuthController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminAuthController.class);

	private final AppUserService userService;

	private final ModelMapper modelMapper;

	@Autowired
	AdminAuthController(AppUserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@RequestMapping(method = GET, value = IS_LOGGED_IN)
	ResponseEntity<Boolean> isCurrentUserLoggedIn() {
		logger.info("isCurrentUserLoggedIn()");
		return new ResponseEntity<Boolean>(userService.isCurrentUserLoggedIn(), OK);
	}

	@RequestMapping(method = GET, value = CURRENTUSER)
	ResponseEntity<AppUserDto> getCurrentUser() throws RestApiException {
		logger.info("getCurrentUser()");
		AppUser appUser = userService.getCurrentUser();
		logger.info("getCurrentUser()->appUser=" + appUser);
		if (appUser==null)
			throw new RestApiException(new Exception(ExceptionType.LOGIN_USERNAME_NOT_FOUND+" appUser==null"));
			
		AppUserDto appUserDto = modelMapper.map(appUser, AppUserDto.class);
		logger.info("getCurrentUser()->appUserDto=" + appUserDto);
		return new ResponseEntity<AppUserDto>(appUserDto, HttpStatus.OK);
	}
}
