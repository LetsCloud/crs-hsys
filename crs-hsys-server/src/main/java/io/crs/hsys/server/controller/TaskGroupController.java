/**
 * 
 */
package io.crs.hsys.server.controller;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.TASK_GROUP;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.crs.hsys.server.entity.task.TaskGroup;
import io.crs.hsys.server.service.AppUserService;
import io.crs.hsys.server.service.TaskGroupService;
import io.crs.hsys.shared.dto.task.TaskGroupDto;
import io.crs.hsys.shared.exception.RestApiException;

/**
 * @author CR
 *
 */
@RestController
@RequestMapping(value = ROOT + TASK_GROUP, produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskGroupController extends CrudController<TaskGroup, TaskGroupDto> {
	private static final Logger logger = LoggerFactory.getLogger(TaskGroupController.class);

	@Autowired
	TaskGroupController(TaskGroupService service, AppUserService appUserService, ModelMapper modelMapper) {
		super(TaskGroup.class, service, modelMapper);
		logger.info("TaskGroupController()");
	}

	@Override
	protected TaskGroupDto createDto(TaskGroup entity) {
		TaskGroupDto dto = modelMapper.map(entity, TaskGroupDto.class);
		return dto;
	}

	@RequestMapping(method = GET)
	public @ResponseBody ResponseEntity<List<TaskGroupDto>> getAll() {
		logger.info("TaskGroupController().getAll()");
		return super.getAll();
	}

	@RequestMapping(value = WEBSAFEKEY, method = GET)
	public ResponseEntity<TaskGroupDto> get(@PathVariable String webSafeKey) throws RestApiException {
		return super.get(webSafeKey);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<TaskGroupDto> saveOrCreate(@RequestBody TaskGroupDto dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}
}
