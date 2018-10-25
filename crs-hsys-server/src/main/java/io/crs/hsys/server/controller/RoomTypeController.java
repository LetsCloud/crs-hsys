/**
 * 
 */
package io.crs.hsys.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import io.crs.hsys.server.entity.hotel.RoomType;
import io.crs.hsys.server.service.RoomTypeService;
import io.crs.hsys.shared.constans.InventoryType;
import io.crs.hsys.shared.dto.hotel.RoomTypeDto;
import io.crs.hsys.shared.exception.RestApiException;

import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOMTYPE;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;
import static io.crs.hsys.shared.api.ApiParameters.ONLY_ACTIVE;
import static io.crs.hsys.shared.api.ApiParameters.SEL_INV_TYPE;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + ROOMTYPE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomTypeController extends HotelChildController<RoomType, RoomTypeDto> {
	private static final Logger logger = LoggerFactory.getLogger(RoomTypeController.class);

	private final ModelMapper modelMapper;

	@Autowired
	RoomTypeController(RoomTypeService service, ModelMapper modelMapper) {
		super(RoomType.class, service, modelMapper);
		logger.info("RoomTypeController()");
		this.modelMapper = modelMapper;
	}

	@Override
	protected RoomTypeDto createDto(RoomType entity) {
		RoomTypeDto dto = modelMapper.map(entity, RoomTypeDto.class);
		return dto;
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<RoomTypeDto>> getByHotelWithFilters(@RequestParam(HOTEL_KEY) String hotelKey,
			@RequestParam(ONLY_ACTIVE) Boolean onlyActive, @RequestParam(SEL_INV_TYPE) InventoryType inventoryType) {
		Map<String, Object> filters = new HashMap<String, Object>();
		if (onlyActive)
			filters.put("active", onlyActive);

		if (inventoryType != null)
			filters.put(SEL_INV_TYPE, inventoryType);

		return getChildrenByFilters(hotelKey, filters);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<RoomTypeDto> get(@PathVariable String webSafeKey) throws RestApiException {
		logger.info("RoomTypeController().get()");
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<RoomTypeDto> saveOrCreate(@RequestBody RoomTypeDto dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

}
