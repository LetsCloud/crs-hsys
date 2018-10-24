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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.entity.profile.Relationship;
import io.crs.hsys.server.service.RelationshipService;
import io.crs.hsys.shared.dto.profile.RelationshipDto;
import io.crs.hsys.shared.dto.profile.RelationshipDtor;
import io.crs.hsys.shared.exception.RestApiException;

import static io.crs.hsys.shared.api.ApiParameters.ONLY_ACTIVE;
import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.REDUCED;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.RELATIONSHIP;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + RELATIONSHIP, produces = MediaType.APPLICATION_JSON_VALUE)
public class RelationshipController extends CrudController<Relationship, RelationshipDto> {
	private static final Logger logger = LoggerFactory.getLogger(ProfileGroupController.class);

	private final ModelMapper modelMapper;

	@Autowired
	RelationshipController(RelationshipService service, ModelMapper modelMapper) {
		super(Relationship.class, service, modelMapper);
		logger.info("ProfileGroupController()");
		this.modelMapper = modelMapper;
	}

	@Override
	protected RelationshipDto createDto(Relationship entity) {
		RelationshipDto dto = modelMapper.map(entity, RelationshipDto.class);
		return dto;
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<RelationshipDto>> getAll(@RequestParam(ONLY_ACTIVE) Boolean onlyActive) {
		return super.getAll();
	}

	@RequestMapping(value = REDUCED, method = GET)
	public ResponseEntity<List<RelationshipDtor>> getAllReduced(@RequestParam(ONLY_ACTIVE) Boolean onlyActive) {
		List<RelationshipDtor> dtos = new ArrayList<RelationshipDtor>();

		AppUser appUser = userService.getCurrentUser();
		if (appUser == null)
			return new ResponseEntity<List<RelationshipDtor>>(dtos, OK);

		String accountWebSafeKey = appUser.getAccount().getWebSafeKey();
		if (accountWebSafeKey == null)
			return new ResponseEntity<List<RelationshipDtor>>(dtos, OK);

		for (Relationship entity : service.getChildren(accountWebSafeKey))
			dtos.add(modelMapper.map(entity, RelationshipDtor.class));

		return new ResponseEntity<List<RelationshipDtor>>(dtos, OK);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<RelationshipDto> get(@PathVariable String webSafeKey) throws RestApiException {
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<RelationshipDto> saveOrCreate(@RequestBody RelationshipDto dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

}
