/**
 * 
 */
package io.crs.hsys.server.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.security.OnRegistrationCompleteEvent;
import io.crs.hsys.server.service.AppUserService;
import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.dto.common.FcmTokenDto;
import io.crs.hsys.shared.exception.RestApiException;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.INVITE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.SUBSCRIBE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.USER;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + USER, produces = MediaType.APPLICATION_JSON_VALUE)
public class AppUserController extends CrudController<AppUser, AppUserDto> {
	private static final Logger logger = LoggerFactory.getLogger(AppUserController.class);

	private final ApplicationEventPublisher eventPublisher;

	private final ModelMapper modelMapper;

	@Autowired
	AppUserController(AppUserService service, ApplicationEventPublisher eventPublisher, ModelMapper modelMapper) {
		super(AppUser.class, service, modelMapper);
		logger.info("AppUserController()");
		this.eventPublisher = eventPublisher;
		this.modelMapper = modelMapper;
	}

	@Override
	protected AppUserDto createDto(AppUser entity) {
		AppUserDto dto = modelMapper.map(entity, AppUserDto.class);
		return dto;
	}

	@Override
	@RequestMapping(method = GET)
	public @ResponseBody ResponseEntity<List<AppUserDto>> getAll() {
		return super.getAll();
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<AppUserDto> get(@PathVariable String webSafeKey) throws RestApiException {
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<AppUserDto> saveOrCreate(@RequestBody AppUserDto dto) throws RestApiException {
		logger.info("saveOrCreate()->dto=" + dto);
		return super.saveOrCreate(dto);
	}

	@RequestMapping(value = INVITE, method = POST)
	public ResponseEntity<AppUserDto> invite(@RequestBody AppUserDto userDto, WebRequest request) {
		try {
			String appUrl = request.getContextPath();
			AppUser user = modelMapper.map(userDto, AppUser.class);
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
			return new ResponseEntity<AppUserDto>(userDto, OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<AppUserDto>(NOT_FOUND);
		}
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

	@RequestMapping(value = SUBSCRIBE, method = POST)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Boolean> subscribe(@RequestBody FcmTokenDto token) {
		logger.info("subscribe(IID_TOKEN=" + token.getToken() + ", USER_AGENT=" + token.getUserAgent() + ")");
		try {
			userService.fcmSubscribe(token.getToken(), token.getUserAgent());
			return new ResponseEntity<Boolean>(true, NOT_FOUND);
		} catch (Throwable e) {
			logger.info("subscribe->catch Exeption->" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Boolean>(NOT_FOUND);
		}
	}

}
