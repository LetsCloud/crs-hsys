/**
 * 
 */
package io.crs.hsys.server.controller;

import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.AdminV1.GLOBAL_CONFIG;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.crs.hsys.server.entity.GlobalConfig;
import io.crs.hsys.server.service.GlobalConfigService;
import io.crs.hsys.shared.dto.GlobalConfigDto;
import io.crs.hsys.shared.exception.RestApiException;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = GLOBAL_CONFIG, produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalConfigController extends CrudController<GlobalConfig, GlobalConfigDto> {
	private static final Logger logger = LoggerFactory.getLogger(GlobalConfigController.class);

	private final ModelMapper modelMapper;

	@Autowired
	GlobalConfigController(GlobalConfigService service, ModelMapper modelMapper) {
		super(GlobalConfig.class, service, modelMapper);
		logger.info("GlobalConfigController()");
		this.modelMapper = modelMapper;
	}

	@Override
	protected GlobalConfigDto createDto(GlobalConfig entity) {
		GlobalConfigDto dto = modelMapper.map(entity, GlobalConfigDto.class);
		return dto;
	}

	@Override
	@RequestMapping(method = GET)
	public @ResponseBody ResponseEntity<List<GlobalConfigDto>> getAll() {
		logger.info("getAll()");
		List<GlobalConfigDto> dtos = new ArrayList<GlobalConfigDto>();

		for (GlobalConfig entity : service.getAll()) {
			logger.info("getAll()->entity=" + entity);
			dtos.add(createDto(entity));
		}

		return new ResponseEntity<List<GlobalConfigDto>>(dtos, OK);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<GlobalConfigDto> get(@PathVariable String webSafeKey) throws RestApiException {
		return super.get(webSafeKey);
	}
}
