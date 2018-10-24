/**
 * 
 */
package io.crs.hsys.server.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.crs.hsys.server.entity.profile.Profile;
import io.crs.hsys.server.service.ProfileService;
import io.crs.hsys.shared.dto.profile.ProfileDtor;
import io.crs.hsys.shared.exception.RestApiException;

import static io.crs.hsys.shared.api.ApiParameters.ONLY_ACTIVE;
import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.PROFILE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + PROFILE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController extends CrudController<Profile, ProfileDtor> {
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

	private final ModelMapper modelMapper;

	@Autowired
	ProfileController(ProfileService service, ModelMapper modelMapper) {
		super(Profile.class, service, modelMapper);
		logger.info("ProfileController()");
		this.modelMapper = modelMapper;
	}

	@Override
	protected ProfileDtor createDto(Profile entity) {
		ProfileDtor dto = modelMapper.map(entity, ProfileDtor.class);
		return dto;
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<ProfileDtor>> getAll(@RequestParam(ONLY_ACTIVE) Boolean onlyActive) {
		return super.getAll();
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<ProfileDtor> get(@PathVariable String webSafeKey) throws RestApiException {
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<ProfileDtor> saveOrCreate(@RequestBody ProfileDtor dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

}
