/**
 * 
 */
package io.crs.hsys.server.controller;

import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;
import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiParameters.TASK_STATUS;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.TASK;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.TASK_STATUS_CHANGE;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.crs.hsys.server.entity.task.Task;
import io.crs.hsys.server.service.AppUserService;
import io.crs.hsys.server.service.TaskService;
import io.crs.hsys.shared.cnst.TaskStatus;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.shared.exception.RestApiException;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + TASK, produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController extends CrudController<Task, TaskDto> {
	private static final Logger logger = LoggerFactory.getLogger(TaskTypeController.class);

	private final TaskService taskService;

	@Autowired
	TaskController(TaskService service, AppUserService appUserService, ModelMapper modelMapper) {
		super(Task.class, service, modelMapper);
		logger.info("TaskTypeController()");
		taskService = service;
	}

	@Override
	protected TaskDto createDto(Task entity) {
		TaskDto dto = modelMapper.map(entity, TaskDto.class);
		return dto;
	}

	@RequestMapping(method = GET)
	public @ResponseBody ResponseEntity<List<TaskDto>> getAll() {
		return super.getAll();
	}

	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<TaskDto> get(@PathVariable String webSafeKey) throws RestApiException {
		logger.info("TaskTypeController().get()->webSafeKey=" + webSafeKey);
		return super.get(webSafeKey);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<TaskDto> saveOrCreate(@RequestBody TaskDto dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

	@RequestMapping(value = TASK_STATUS_CHANGE, method = GET)
	public @ResponseBody ResponseEntity<TaskDto> changeTaskStatus(@RequestParam(WEBSAFEKEY) String webSafeKey,
			@RequestParam(TASK_STATUS) TaskStatus status) throws RestApiException {
		return new ResponseEntity<TaskDto>(modelMapper.map(taskService.changeStatus(webSafeKey, status), TaskDto.class),
				HttpStatus.OK);
	}
}
