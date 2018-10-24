/**
 * 
 */
package io.crs.hsys.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.entity.common.UserGroup;
import io.crs.hsys.server.service.AppUserService;
import io.crs.hsys.server.service.UserGroupService;
import io.crs.hsys.shared.dto.common.UserGroupDto;
import io.crs.hsys.shared.exception.RestApiException;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.USER_GROUP;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + USER_GROUP, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserGroupController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AppUserController.class);

	private final UserGroupService userGroupService;

	private final AppUserService appUserService;

	private final ModelMapper modelMapper;

	@Autowired
	UserGroupController(UserGroupService userGroupService, AppUserService appUserService, ModelMapper modelMapper) {
		this.userGroupService = userGroupService;
		this.appUserService = appUserService;
		this.modelMapper = modelMapper;
	}

	@RequestMapping(method = GET)
	public @ResponseBody ResponseEntity<List<UserGroupDto>> list() {
		List<UserGroupDto> userGroupDtos = new ArrayList<UserGroupDto>();

		AppUser appUser = appUserService.getCurrentUser();
		if (appUser == null)
			return new ResponseEntity<List<UserGroupDto>>(userGroupDtos, OK);

		String accountWebSafeKey = appUser.getAccount().getWebSafeKey();
		if (accountWebSafeKey == null)
			return new ResponseEntity<List<UserGroupDto>>(userGroupDtos, OK);

		for (UserGroup userGroup : userGroupService.getAll(accountWebSafeKey))
			userGroupDtos.add(modelMapper.map(userGroup, UserGroupDto.class));

		return new ResponseEntity<List<UserGroupDto>>(userGroupDtos, OK);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<UserGroupDto> create(@RequestBody UserGroupDto dto) throws RestApiException {
		logger.info("create()-dto.name=" + dto.getName());
		try {
			UserGroup entity = userGroupService.create(modelMapper.map(dto, UserGroup.class));
			dto = modelMapper.map(entity, UserGroupDto.class);
			return new ResponseEntity<UserGroupDto>(dto, OK);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	@RequestMapping(value = WEBSAFEKEY, method = GET)
	public ResponseEntity<UserGroupDto> read(@PathVariable String id) throws Throwable {
		UserGroup user = userGroupService.read(id);
		UserGroupDto userDto = modelMapper.map(user, UserGroupDto.class);
		return new ResponseEntity<UserGroupDto>(userDto, OK);
	}

	@RequestMapping(method = PUT)
	public ResponseEntity<UserGroupDto> update(@RequestBody UserGroupDto userDto) throws RestApiException {
		try {
			UserGroup user = userGroupService.update(modelMapper.map(userDto, UserGroup.class));
			userDto = modelMapper.map(user, UserGroupDto.class);
			return new ResponseEntity<UserGroupDto>(userDto, OK);
		} catch (Throwable e) {
			// e.printStackTrace();
			throw new RestApiException(e);
		}
	}

	@RequestMapping(method = DELETE, value = "{" + WEBSAFEKEY + "}")
	public ResponseEntity<Void> delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		try {
			userGroupService.delete(webSafeKey);
			return new ResponseEntity<Void>(OK);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}
}
