/**
 * 
 */
package io.crs.hsys.server.controller;

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
import io.crs.hsys.shared.dto.common.AccountUserDto;

import static io.crs.hsys.shared.api.ApiPaths.SpaV1.CURRENTUSER;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.IS_LOGGED_IN;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.LOGIN;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + LOGIN, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	private final AppUserService userService;

	private final ModelMapper modelMapper;

	@Autowired
	AuthController(AppUserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@RequestMapping(method = GET, value = IS_LOGGED_IN)
	ResponseEntity<Boolean> isCurrentUserLoggedIn() {
		logger.info("isCurrentUserLoggedIn()");
		return new ResponseEntity<Boolean>(userService.isCurrentUserLoggedIn(), OK);
	}

	@RequestMapping(method = GET, value = CURRENTUSER)
	ResponseEntity<AccountUserDto> getCurrentUser() {
		logger.info("getCurrentUser()");
		AppUser appUser = userService.getCurrentUser();
		logger.info("getCurrentUser()->appUser=");
		AccountUserDto appUserDto = modelMapper.map(appUser, AccountUserDto.class);
		logger.info("getCurrentUser()->appUserDto=" + appUserDto);
		return new ResponseEntity<AccountUserDto>(appUserDto, HttpStatus.OK);
	}

}
