/**
 * 
 */
package io.crs.hsys.server.controller;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

import io.crs.hsys.server.entity.hotel.Room;
import io.crs.hsys.server.service.RoomService;
import io.crs.hsys.shared.constans.RoomStatus;
import io.crs.hsys.shared.dto.filter.RoomStatusFilterDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.exception.RestApiException;

import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;
import static io.crs.hsys.shared.api.ApiParameters.ONLY_ACTIVE;
import static io.crs.hsys.shared.api.ApiParameters.ROOM_KEY;
import static io.crs.hsys.shared.api.ApiParameters.ROOM_STATUS;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOM;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.AVAILABLE_ON_DATE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.STATUS_CHANGE;

/**
 * @author CR
 *
 */
@RestController
@RequestMapping(value = ROOT + ROOM, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomController extends HotelChildController<Room, RoomDto> {
	private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

	private final RoomService roomService;

	private final ModelMapper modelMapper;

	@Autowired
	RoomController(RoomService roomService, ModelMapper modelMapper) {
		super(Room.class, roomService, modelMapper);
		logger.info("RoomController()");
		this.roomService = roomService;
		this.modelMapper = modelMapper;
	}

	@Override
	protected RoomDto createDto(Room entity) {
		RoomDto dto = modelMapper.map(entity, RoomDto.class);
		return dto;
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<RoomDto>> getByHotel(@RequestParam(HOTEL_KEY) String hotelKey,
			@RequestParam(ONLY_ACTIVE) Boolean onlyActive) {
		logger.info("RoomController().getByHotel()");
		if (onlyActive) {
			logger.info("RoomController().getByHotel()->onlyActive");
			List<RoomDto> result = new ArrayList<RoomDto>();
			for (Room room : roomService.getActiveRoomsByHotel(hotelKey)) {
				logger.info("RoomController().getByHotel()->onlyActive->room.getCode()=" + room.getCode());
				result.add(modelMapper.map(room, RoomDto.class));
			}
			return new ResponseEntity<List<RoomDto>>(result, OK);
		} else {
			return getChildren(hotelKey);
		}
	}

	@Override
	@RequestMapping(method = GET, value = PATH_WEBSAFEKEY)
	public ResponseEntity<RoomDto> get(@PathVariable String webSafeKey) throws RestApiException {
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<RoomDto> saveOrCreate(@RequestBody RoomDto dto) throws RestApiException {
		logger.info("RoomController()->dto=" + dto);
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(method = DELETE, value = PATH_WEBSAFEKEY)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

	@RequestMapping(method = GET, value = STATUS_CHANGE)
	public ResponseEntity<RoomDto> changeStatus(@RequestParam(ROOM_KEY) String roomKey,
			@RequestParam(ROOM_STATUS) RoomStatus roomStatus) {
		Room room;
		try {
			room = roomService.changeStatus(roomKey, roomStatus);
			RoomDto roomDto = modelMapper.map(room, RoomDto.class);
			// LOGGER.info("changeStatus()->roomDto=" + roomDto);
			return new ResponseEntity<RoomDto>(roomDto, OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<RoomDto>(NOT_FOUND);
		}
	}

	// public List<RoomDto> getAvailableRoomsByHotel(@QueryParam(HOTEL_KEY)
	// String hotelKey,
	// @QueryParam(DATE) @DateTimeFormat(iso = ISO.DATE) Date date, @RequestBody
	// RoomStatusFilterDto filterDto) {

	@RequestMapping(method = POST, value = AVAILABLE_ON_DATE)
	public ResponseEntity<List<RoomDto>> getAvailableRoomsByHotel(@RequestBody RoomStatusFilterDto filterDto) {

		List<Room> allRooms = roomService.getAvailableRoomsByHotelOnDateWithReservations(filterDto.getHotelKey(),
				filterDto.getDate());
		allRooms = Room.filterByRoomStatus(allRooms, filterDto);

		List<RoomDto> roomDtos = new ArrayList<RoomDto>();
		for (Room room : allRooms)
			roomDtos.add(modelMapper.map(room, RoomDto.class));

		return new ResponseEntity<List<RoomDto>>(roomDtos, OK);
	}
}
