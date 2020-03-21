/**
 * 
 */
package io.crs.hsys.server.controller;

import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOM_RATE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import io.crs.hsys.server.service.RoomRateService;
import io.crs.hsys.shared.dto.rate.RateRestrictionDto;
import io.crs.hsys.shared.dto.rate.update.RoomRateOperationDto;
import io.crs.hsys.shared.dto.rate.update.RoomRateUpdateDto;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + ROOM_RATE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomRateController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(RoomRateController.class);

	private final RoomRateService roomRateService;

	@Autowired
	RoomRateController(RoomRateService roomRateService) {
		logger.info("RoomRateController()");
		this.roomRateService = roomRateService;
	}

	@RequestMapping(method = POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void update(@RequestBody RoomRateUpdateDto dto, WebRequest request) {
		logger.info("update()->RoomRateUpdateDto=" + dto);
		for (RoomRateOperationDto o : dto.getOperations())
			logger.info("update()->operation=" + o);
		for (RateRestrictionDto r : dto.getRestrictions())
			logger.info("update()->operation=" + r);

		try {
			roomRateService.update(dto);
		} catch (EntityValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UniqueIndexConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
