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
import io.crs.hsys.shared.exception.OooRoomOverlapException;
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
	public void createOooRooms(OooCreateDto dto)
			throws OooRoomOverlapException, EntityValidationException, UniqueIndexConflictException {
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
					throw new OooRoomOverlapException(oooRoom.getRoom().getCode(), oooRoom.getFromDate(),
							oooRoom.getToDate());
				}
			}
		}

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
		logger.info("getRooms()->fromCode=" + dto.getFromRoom());
		logger.info("getRooms()->toCode=" + dto.getToRoom());
		logger.info("getRooms()->floor=" + dto.getFloor());

		List<String> typeCodes = new ArrayList<String>();
		for (RoomTypeDtor typeDtor : dto.getRoomTypes()) {
			logger.info("getRooms()->typeDtor.getCode()=" + typeDtor.getCode());
			if (!typeCodes.contains(typeDtor.getCode())) {
				logger.info("getRooms()->filterType=" + typeDtor.getCode());
				typeCodes.add(typeDtor.getCode());
			}
		}

		logger.info("getRooms()->typeCodes=" + typeCodes);
		logger.info("getRooms()->roomStatuses=" + dto.getRoomStatuses());

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
		int criterion = 0;
		int checksum = 0;
		// 000000 - 0
		// 000001 - 1

		// 000000 - 0
		// 000010 - 2

		// 000000 - 0
		// 000011 - 3

		// 000100 - 4
		// 000101 - 5
		// 000110 - 6
		// 000111 - 7

		logger.info("checkRoom()->check room=" + room.getCode());

		if (!Strings.isNullOrEmpty(fromCode) && !Strings.isNullOrEmpty(toCode)) {
			logger.info("checkRoom()->check room period");
			criterion = criterion + 1;
			if ((room.getCode().compareTo(fromCode) >= 0) && (room.getCode().compareTo(toCode) <= 0)) {
				logger.info("checkRoom()->selected1 room=" + room.getCode());
				checksum = checksum + 1;
			}
		}

		if (!Strings.isNullOrEmpty(fromCode) && Strings.isNullOrEmpty(toCode)) {
			logger.info("checkRoom()->check fromCode");
			criterion = criterion + 2;
			if (room.getCode().equals(fromCode)) {
				logger.info("checkRoom()->selected2 room=" + room.getCode());
				checksum = checksum + 2;
			}
		}

		if (Strings.isNullOrEmpty(fromCode) && !Strings.isNullOrEmpty(toCode)) {
			logger.info("checkRoom()->check toCode");
			criterion = criterion + 4;
			if (room.getCode().equals(toCode)) {
				logger.info("checkRoom()->selected3 room=" + room.getCode());
				checksum = checksum + 4;
			}
		}

		if (!typeCodes.isEmpty()) {
			logger.info("checkRoom()->check typeCodes");
			criterion = criterion + 8;
			if (typeCodes.contains(room.getRoomType().getCode())) {
				logger.info("checkRoom()->selected4 room=" + room.getCode());
				checksum = checksum + 8;
			}
		}

		if (!Strings.isNullOrEmpty(floor)) {
			logger.info("checkRoom()->check floor=" + floor);
			criterion = criterion + 16;
			if (room.getFloor().equals(floor)) {
				logger.info("checkRoom()->selected5 room=" + room.getCode());
				checksum = checksum + 16;
			}
		}

		if (!statuses.isEmpty()) {
			logger.info("checkRoom()->check statuses=" + statuses);
			criterion = criterion + 32;
			if (statuses.contains(room.getRoomStatus())) {
				logger.info("checkRoom()->selected5 room=" + room.getCode());
				checksum = checksum + 32;
			}
		}

		logger.info("checkRoom()->criterion=" + criterion);
		logger.info("checkRoom()->checksum=" + checksum);

		return checksum == criterion;
	}
}
