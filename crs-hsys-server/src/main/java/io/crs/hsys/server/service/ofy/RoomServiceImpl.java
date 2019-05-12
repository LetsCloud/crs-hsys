/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Work;

import io.crs.hsys.server.entity.hotel.Room;
import io.crs.hsys.server.entity.hotel.RoomAvailability;
import io.crs.hsys.server.entity.reservation.Reservation;
import io.crs.hsys.server.entity.reservation.RoomStay;
import io.crs.hsys.server.entity.task.Task;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.HotelRepository;
import io.crs.hsys.server.repository.ReservationRepository;
import io.crs.hsys.server.repository.RoomRepository;
import io.crs.hsys.server.repository.TaskRepository;
import io.crs.hsys.server.service.RoomService;
import io.crs.hsys.shared.cnst.FoRoomStatus;
import io.crs.hsys.shared.cnst.OccStatus;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.dto.hk.GuestNumber;
import io.crs.hsys.shared.dto.hk.RoomStatusDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomOccDto;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.shared.exception.RestApiException;

/**
 * @author CR
 *
 */
public class RoomServiceImpl extends HotelChildServiceImpl<Room, RoomRepository> implements RoomService {
	private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class.getName());

	private final ReservationRepository reservationRepository;
	private final TaskRepository taskRepository;
	private final ModelMapper modelMapper;

	public RoomServiceImpl(RoomRepository repository, AccountRepository accountRepository,
			HotelRepository hotelRepository, ReservationRepository reservationRepository, TaskRepository taskRepository,
			ModelMapper modelMapper) {
		super(repository, accountRepository, hotelRepository);
		logger.info("RoomServiceImpl");
		this.reservationRepository = reservationRepository;
		this.taskRepository = taskRepository;
		this.modelMapper = modelMapper;
	}
	@Override
	public Room create(Room entity) throws Throwable {
		entity.setRoomStatus(RoomStatus.RS_DIRTY);
		return super.create(entity);
	}

	@Override
	public List<Room> getActiveRoomsByHotel(String hotelKey) {
		logger.info("RoomServiceImpl().getActiveRoomsByHotel()");
		Date today = new Date();
		logger.info("RoomServiceImpl().getActiveRoomsByHotel()->today=" + today);
		List<Room> result = new ArrayList<Room>();
		for (Room room : repository.getChildren(hotelKey)) {
			logger.info("RoomServiceImpl().getActiveRoomsByHotel()->room.getCode()=" + room.getCode());
			List<RoomAvailability> ral = room.getRoomAvailabilities();
			Optional<RoomAvailability> open = ral.stream()
					.filter(o -> ((o.getDate().compareTo(today) <= 0) && (o.isAvailable())))
					.max(Comparator.comparing(RoomAvailability::getDate));
			logger.info("RoomServiceImpl().getActiveRoomsByHotel()->open=" + open);
			if (open.isPresent()) {
				logger.info("RoomServiceImpl().getActiveRoomsByHotel()->open.isPresent()");
				Optional<RoomAvailability> closed = ral.stream()
						.filter(o -> ((o.getDate().compareTo(open.get().getDate()) > 0)
								&& (o.getDate().compareTo(today) <= 0) && (!o.isAvailable())))
						.max(Comparator.comparing(RoomAvailability::getDate));
				logger.info("RoomServiceImpl().getActiveRoomsByHotel()->closed=" + closed);
				if (!closed.isPresent()) {
					logger.info("RoomServiceImpl().getActiveRoomsByHotel()->!closed.isPresent()");
					result.add(room);
				}
			}
		}
		return result;
	}

	@Override
	public List<Room> getAvailableRoomsByHotelOnDate(String hotelKey, Date date) {
		// LOGGER.info("getAvailableRoomsByHotel()->date:" + date.toString());
		// A szálloda összes szobája
		List<Room> rooms = getActiveRoomsByHotel(hotelKey);
		// Rendelkezésre nem álló szobák
		List<Room> unavailableRooms = new ArrayList<Room>();
		// Végigpásztázzuk az összes szobát, hogy megállapítsuk a rendelkezésre
		// állásukat
		for (Room room : rooms) {
			// LOGGER.info("getAvailableRoomsByHotel()->allRoom:" +
			// room.getCode());
			List<RoomAvailability> roomAvailabilities = room.getRoomAvailabilities();
			// LOGGER.info("getAvailableRoomsByHotel()->roomAvailabilities.size()="
			// + roomAvailabilities.size());
			// for (RoomAvailability ra : roomAvailabilities) {
			// LOGGER.info("getAvailableRoomsByHotel()->date(unOrdered)=" +
			// ra.getDate());
			// }
			// A rendelkezésre állások listáját dátum szerint rendezzük
			Collections.sort(roomAvailabilities, RoomAvailability.ORDER_BY_DATE);
			// for (RoomAvailability ra : roomAvailabilities) {
			// LOGGER.info("getAvailableRoomsByHotel()->date(ordered)=" +
			// ra.getDate());
			// }
			// Megizsgáljuk, hogy az adott szoba a megadott napon rendelkezésre
			// áll e
			if (!isAvailableRoom(roomAvailabilities, date))
				unavailableRooms.add(room);
		}
		// Töröljük a rendelkezésre nem álló szobákat
		if (!unavailableRooms.isEmpty())
			rooms.removeAll(unavailableRooms);

		// for (Room room : rooms) {
		// LOGGER.info("getAvailableRoomsByHotel()->room=" + room.getCode());
		// }

		return rooms;
	}

	/**
	 * Megállapítja, hogy rendelkezésre áll e a szoba
	 * 
	 * @param roomOpenings
	 * @param date
	 * @return
	 */
	private Boolean isAvailableRoom(List<RoomAvailability> roomAvailabilies, Date date) {
		// LOGGER.info("isAvailableRoom()->Date=" + date.toString());
		// Alapból nem áll rendelkezésre a szoba
		Boolean available = false;
		for (RoomAvailability roomAvailability : roomAvailabilies) {
			// LOGGER.info("isAvailableRoom()->roomAvailability.getDate()=" +
			// roomAvailability.getDate().toString());
			if (roomAvailability.getDate().before(date)) {
				// Megjegyezzük a rendelkezésre állási állapotot
				available = roomAvailability.isAvailable();
				// LOGGER.info("isAvailableRoom()->before->available=" +
				// available);
			} else {
				// LOGGER.info("isAvailableRoom()->after or equals->available="
				// + available);
				if (roomAvailability.getDate().equals(date)) {
					available = roomAvailability.isAvailable();
					// LOGGER.info("isAvailableRoom()->equals->available=" +
					// available);
				}
				// Ha túl vagyunk a vizsgált napon, akkor visszaadjuk az eddig
				// jegyzett rendelkezésre állási állapotot
				return available;
			}
		}
		return available;
	}

	@Override
	public RoomStatusDto changeStatus(final String roomKey, final RoomStatus roomStatus) throws RestApiException {
		try {
			// Objectify tranzakció indul
			Room th = ofy().transact(new Work<Room>() {
				public Room run() {
					Room entity = repository.findByWebSafeKey(roomKey);
					try {
						// LOGGER.info("changeStatus()->roomStatus=" +
						// roomStatus);
						entity.setRoomStatus(roomStatus);
						entity = repository.save(entity);
						return entity;
					} catch (Throwable e) {
						e.printStackTrace(System.out);
						throw new RuntimeException(e);
					}
				}
			});
			return createRoomStatus(th);
		} catch (RuntimeException re) {
			throw new RestApiException(re);
		}
	}

	@Override
	public List<Room> getAvailableRoomsByHotelOnDateWithReservations(String hotelKey, Date date) {
		List<Reservation> reservations = reservationRepository.getInHouseReservations(hotelKey, date);
		List<Room> rooms = getAvailableRoomsByHotelOnDate(hotelKey, date);

		for (Room room : rooms) {
			room.setOccupied(false);
			room.setFoRoomStatus(FoRoomStatus.NOT_RESERVED);

			List<Reservation> filteredReservations = Reservation.filterByRoom(reservations, room);
			if ((filteredReservations != null) && (filteredReservations.size() > 0)) {
				for (Reservation r : filteredReservations) {
					if (r.getRoomStays() != null) {
						for (RoomStay rs : r.getRoomStays()) {
							if (rs.getRoom().equals(room)) {
								switch (r.getStatus()) {
								case PROSPECT:
									// Még nem érkezett meg
									room.setFoRoomStatus(FoRoomStatus.ARRIVALS);
									break;
								case TENTATIVE:
									// Még nem érkezett meg
									room.setFoRoomStatus(FoRoomStatus.ARRIVALS);
									break;
								case DEFINITIVE:
									// Még nem érkezett meg
									room.setFoRoomStatus(FoRoomStatus.ARRIVALS);
									break;
								case CHECKED_IN:
									// Már megérkezett
									room.setOccupied(true);
									if (rs.getArrival().equals(rs.getDeparture())) {
										room.setFoRoomStatus(FoRoomStatus.DAY_USE);
									} else if (rs.getArrival().equals(date)) {
										room.setFoRoomStatus(FoRoomStatus.ARRIVED);
									} else if (rs.getDeparture().equals(date)) {
										room.setFoRoomStatus(FoRoomStatus.DUE_OUT);
									} else {
										room.setFoRoomStatus(FoRoomStatus.STAYOVER);
									}
									break;
								case CHECKED_OUT:
									// Elutazott
									room.setFoRoomStatus(FoRoomStatus.DEPARTED);
									break;
								case OUTSTANDING:
									// Elutazott
									room.setFoRoomStatus(FoRoomStatus.DEPARTED);
									break;
								case CANCELLED:
									// Elutazott
									if (room.getFoRoomStatus() == null)
										room.setFoRoomStatus(FoRoomStatus.NOT_RESERVED);
									break;
								default:
									if (room.getFoRoomStatus() == null)
										room.setFoRoomStatus(FoRoomStatus.NOT_RESERVED);
									break;
								}
							}
						}
					}
				}
			}
		}
		return rooms;
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomStatusDto> getRoomStatusesByHotel(String hotelKey) {
		List<RoomStatusDto> result = new ArrayList<RoomStatusDto>();
		for (Room room : getActiveRoomsByHotel(hotelKey)) {
			result.add(createRoomStatus(room));
		}
		return result;
	}

	private RoomStatusDto createRoomStatus(Room room) {
		logger.info("RoomServiceImpl().createRoomStatus()");
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("room", room);
		List<Task> tasks = taskRepository.getChildrenByFilters(room.getHotel().getAccount().getWebSafeKey(), filters);

		List<TaskDto> taskDtos = new ArrayList<TaskDto>();
		for (Task task : tasks) {
			logger.info("RoomServiceImpl().createRoomStatus()->task=" + task);
			taskDtos.add(modelMapper.map(task, TaskDto.class));
		}

		return RoomStatusDto.builder().room(modelMapper.map(room, RoomDto.class))
				.currOccStatus(new RoomOccDto(OccStatus.VACANT, new GuestNumber(0, 0, 0, 0)))
				.nextOccStatus(new RoomOccDto(OccStatus.VACANT, new GuestNumber(0, 0, 0, 0))).tasks(taskDtos).build();
	}

	@Override
	public RoomStatusDto getRoomStatus(String webSafeKey) throws RestApiException {
		logger.info("RoomServiceImpl().getRoomStatus()->webSafeKey=" + webSafeKey);
		Room room;
		try {
			room = this.read(webSafeKey);
			logger.info("RoomServiceImpl().getRoomStatus()->room.getCode()=" + room.getCode());
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
		return createRoomStatus(room);
	}

	@Override
	public void resetRoomStatus(String hotelKey) throws RestApiException {
		for (Room room : getActiveRoomsByHotel(hotelKey)) {
			room.setRoomStatus(RoomStatus.RS_DIRTY);
			try {
				this.update(room);
			} catch (Throwable e) {
				throw new RestApiException(e);
			}
		}
	}
}
