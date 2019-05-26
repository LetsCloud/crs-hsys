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

import io.crs.hsys.server.entity.hotel.Room;
import io.crs.hsys.server.service.RoomService;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.dto.filter.RoomStatusFilterDto;
import io.crs.hsys.shared.dto.hk.RoomStatusDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.exception.RestApiException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;
import static io.crs.hsys.shared.api.ApiParameters.ONLY_ACTIVE;
import static io.crs.hsys.shared.api.ApiParameters.ROOM_KEY;
import static io.crs.hsys.shared.api.ApiParameters.ROOM_STATUS;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOM;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.AVAILABLE_ON_DATE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOM_STATUS_CHANGE;

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
		if (onlyActive) {
			List<RoomDto> result = new ArrayList<RoomDto>();
			for (Room room : roomService.getActiveRoomsByHotel(hotelKey)) {
				result.add(modelMapper.map(room, RoomDto.class));
			}
			return new ResponseEntity<List<RoomDto>>(result, OK);
		} else {
			return getChildren(hotelKey);
		}
	}

	@RequestMapping(method = GET, value = ROOM_STATUS)
	public ResponseEntity<List<RoomStatusDto>> getRoomStatusesByHotel(@RequestParam(HOTEL_KEY) String hotelKey) {
		List<RoomStatusDto> result = roomService.getRoomStatusesByHotel(hotelKey);
		return new ResponseEntity<List<RoomStatusDto>>(result, OK);
	}

	@Override
	@RequestMapping(method = GET, value = PATH_WEBSAFEKEY)
	public ResponseEntity<RoomDto> get(@PathVariable String webSafeKey) throws RestApiException {
		return super.get(webSafeKey);
	}

	@RequestMapping(method = GET, value = ROOM_STATUS + PATH_WEBSAFEKEY)
	public ResponseEntity<RoomStatusDto> getRoomStatus(@PathVariable String webSafeKey) throws RestApiException {
		return new ResponseEntity<RoomStatusDto>(roomService.getRoomStatus(webSafeKey), OK);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<RoomDto> saveOrCreate(@RequestBody RoomDto dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(method = DELETE, value = PATH_WEBSAFEKEY)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

	@RequestMapping(method = GET, value = ROOM_STATUS_CHANGE)
	public ResponseEntity<RoomStatusDto> changeRoomStatus(@RequestParam(ROOM_KEY) String roomKey,
			@RequestParam(ROOM_STATUS) RoomStatus roomStatus) {
		try {
			return new ResponseEntity<RoomStatusDto>(roomService.changeStatus(roomKey, roomStatus), OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<RoomStatusDto>(NOT_FOUND);
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
