/**
 * 
 */
package io.crs.hsys.server.controller;

import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.OOO_ROOM;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.OOO_ROOMS_CREATE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.crs.hsys.server.entity.hotel.OooRoom;
import io.crs.hsys.server.service.OooRoomService;
import io.crs.hsys.shared.dto.hotel.OooCreateDto;
import io.crs.hsys.shared.dto.hotel.OooRoomDto;
import io.crs.hsys.shared.exception.RestApiException;

/**
 * @author CR
 *
 */
@RestController
@RequestMapping(value = ROOT + OOO_ROOM, produces = MediaType.APPLICATION_JSON_VALUE)
public class OooRoomController extends HotelChildController<OooRoom, OooRoomDto> {
	private static final Logger logger = LoggerFactory.getLogger(OooRoomController.class);

	private final OooRoomService service;
	private final ModelMapper modelMapper;

	@Autowired
	OooRoomController(OooRoomService service, ModelMapper modelMapper) {
		super(OooRoom.class, service, modelMapper);
		logger.info("OooRoomController()");
		this.service = service;
		this.modelMapper = modelMapper;
	}

	@Override
	protected OooRoomDto createDto(OooRoom entity) {
		OooRoomDto dto = modelMapper.map(entity, OooRoomDto.class);
		return dto;
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<OooRoomDto>> getByHotel(@RequestParam(HOTEL_KEY) String hotelKey) {
		return getChildren(hotelKey);
	}

	@Override
	@RequestMapping(method = GET, value = PATH_WEBSAFEKEY)
	public ResponseEntity<OooRoomDto> get(@PathVariable String webSafeKey) throws RestApiException {
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<OooRoomDto> saveOrCreate(@RequestBody OooRoomDto dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@RequestMapping(method = POST, value = OOO_ROOMS_CREATE)
	public void createOooRooms(@RequestBody OooCreateDto dto) throws RestApiException {
		logger.info("createOooRooms()->dto=" + dto);
		try {
			service.createOooRooms(dto);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	@Override
	@RequestMapping(method = DELETE, value = PATH_WEBSAFEKEY)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}
}
