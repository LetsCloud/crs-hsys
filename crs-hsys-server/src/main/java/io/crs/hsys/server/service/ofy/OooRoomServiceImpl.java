/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.appengine.repackaged.com.google.api.client.util.Strings;

import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.entity.hotel.Hotel;
import io.crs.hsys.server.entity.hotel.OooRoom;
import io.crs.hsys.server.entity.hotel.Room;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.HotelRepository;
import io.crs.hsys.server.repository.OooRoomRepository;
import io.crs.hsys.server.repository.RoomRepository;
import io.crs.hsys.server.service.OooRoomService;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.dto.hotel.OooCreateDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.ExceptionSubType;
import io.crs.hsys.shared.exception.ForeignKeyConflictException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author CR
 *
 */
public class OooRoomServiceImpl extends HotelChildServiceImpl<OooRoom, OooRoomRepository> implements OooRoomService {
	private static final Logger logger = LoggerFactory.getLogger(OooRoomServiceImpl.class.getName());

	private final RoomRepository roomRepository;
	private final ModelMapper modelMapper;

	public OooRoomServiceImpl(OooRoomRepository repository, AccountRepository accountRepository,
			HotelRepository hotelRepository, RoomRepository roomRepository, ModelMapper modelMapper) {
		super(repository, accountRepository, hotelRepository);
		logger.info("OooRoomServiceImpl");
		this.roomRepository = roomRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void createOooRooms(OooCreateDto dto) throws ForeignKeyConflictException, EntityValidationException, UniqueIndexConflictException {
		logger.info("createOooRooms()");
		List<Room> rooms = new ArrayList<Room>();
		if (dto.getRooms().isEmpty()) {
			rooms = getRooms(dto);
		} else {
			for (RoomDto roomDto : dto.getRooms()) {
				rooms.add(roomRepository.findByWebSafeKey(roomDto.getWebSafeKey()));
			}
		}

		logger.info("createOooRooms()-2");
		for (Room room : rooms) {
			Map<String, Object> filters = new HashMap<String, Object>();
			filters.put("room =", room);

			List<OooRoom> oooRooms = repository.getChildrenByFilters(dto.getHotel().getWebSafeKey(), filters);
			for (OooRoom oooRoom : oooRooms) {
				if (((oooRoom.getFromDate().equals(dto.getToDate())) || (oooRoom.getFromDate().before(dto.getToDate())))
						&& ((oooRoom.getToDate().equals(dto.getFromDate()))
								|| (oooRoom.getToDate().after(dto.getFromDate())))) {
					throw new ForeignKeyConflictException(ExceptionSubType.OOO_ROOM_OVERLAP);
				}
			}
		}
		logger.info("createOooRooms()-3");

		AppUser appUser = modelMapper.map(dto.getCreatedBy(), AppUser.class);
		Hotel hotel = modelMapper.map(dto.getHotel(), Hotel.class);
		logger.info("createOooRooms()-4");
		for (Room room : rooms) {
			OooRoom newOooRoom = new OooRoom(room, dto.getFromDate(), dto.getToDate(), dto.getReturnAs(),
					dto.getReturnWhen(), dto.getRemarks(), appUser, hotel);
			logger.info("createOooRooms()->newOooRoom=" + newOooRoom);
			repository.save(newOooRoom);
		}
		logger.info("createOooRooms()-5");
	}

	private List<Room> getRooms(OooCreateDto dto) {
		List<String> typeCodes = new ArrayList<String>();
		for (RoomTypeDtor typeDtor : dto.getRoomTypes()) {
			if (typeCodes.contains(typeDtor.getCode()))
				typeCodes.add(typeDtor.getCode());
		}

		List<Room> rooms = new ArrayList<Room>();

		for (Room room : roomRepository.getChildren(dto.getHotel().getWebSafeKey())) {
			if ((checkRoom(room, dto.getFromRoom(), dto.getToRoom(), typeCodes, dto.getFloor(), dto.getRoomStatuses()))
					&& (!rooms.contains(room)))
				rooms.add(room);
		}

		return rooms;
	}

	private Boolean checkRoom(Room room, String fromCode, String toCode, List<String> typeCodes, String floor,
			List<RoomStatus> statuses) {
		Boolean result = false;

		if (!Strings.isNullOrEmpty(fromCode) && !Strings.isNullOrEmpty(toCode)) {
			if ((room.getCode().compareTo(fromCode) >= 0) && (room.getCode().compareTo(toCode) <= 0))
				return true;
		}

		if (!Strings.isNullOrEmpty(fromCode)) {
			if (room.getCode().equals(fromCode))
				return true;
		}

		if (!Strings.isNullOrEmpty(toCode)) {
			if (room.getCode().equals(toCode))
				return true;
		}

		if (!typeCodes.isEmpty()) {
			if (typeCodes.contains(room.getRoomType().getCode()))
				return true;
		}

		if (!Strings.isNullOrEmpty(floor)) {

			if (room.getFloor().equals(floor))
				return true;
		}

		if (!statuses.isEmpty()) {
			if (statuses.contains(room.getRoomStatus()))
				return true;
		}

		return result;
	}
}
