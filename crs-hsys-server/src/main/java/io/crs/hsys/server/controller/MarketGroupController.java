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

import io.crs.hsys.server.entity.reservation.MarketGroup;
import io.crs.hsys.server.service.MarketGroupService;
import io.crs.hsys.shared.constans.InventoryType;
import io.crs.hsys.shared.dto.hotel.MarketGroupDto;
import io.crs.hsys.shared.exception.RestApiException;

import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;
import static io.crs.hsys.shared.api.ApiParameters.ONLY_ACTIVE;
import static io.crs.hsys.shared.api.ApiParameters.SEL_INV_TYPE;
import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.MARKET_GROUP;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + MARKET_GROUP, produces = MediaType.APPLICATION_JSON_VALUE)
public class MarketGroupController extends HotelChildController<MarketGroup, MarketGroupDto> {
	private static final Logger logger = LoggerFactory.getLogger(MarketGroupController.class);

	private final ModelMapper modelMapper;

	@Autowired
	MarketGroupController(MarketGroupService service, ModelMapper modelMapper) {
		super(MarketGroup.class, service, modelMapper);
		logger.info("RoomTypeController()");
		this.modelMapper = modelMapper;
	}

	@Override
	protected MarketGroupDto createDto(MarketGroup entity) {
		MarketGroupDto dto = modelMapper.map(entity, MarketGroupDto.class);
		return dto;
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<MarketGroupDto>> getByHotelWithFilters(@RequestParam(HOTEL_KEY) String hotelKey,
			@RequestParam(ONLY_ACTIVE) Boolean onlyActive, @RequestParam(SEL_INV_TYPE) InventoryType inventoryType) {
		logger.info("RoomTypeController().getByHotelWithFilters()");
		Map<String, Object> filters = new HashMap<String, Object>();
		if (onlyActive)
			filters.put("active", onlyActive);

		return getChildrenByFilters(hotelKey, filters);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<MarketGroupDto> get(@PathVariable String webSafeKey) throws RestApiException {
		logger.info("RoomTypeController().get()");
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<MarketGroupDto> saveOrCreate(@RequestBody MarketGroupDto dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

}
