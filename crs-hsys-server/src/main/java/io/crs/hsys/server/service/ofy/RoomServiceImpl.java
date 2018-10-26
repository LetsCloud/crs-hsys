/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Work;

import io.crs.hsys.server.entity.hotel.Room;
import io.crs.hsys.server.entity.hotel.RoomAvailability;
import io.crs.hsys.server.entity.reservation.Reservation;
import io.crs.hsys.server.entity.reservation.RoomStay;
import io.crs.hsys.server.repository.AccountRepository;
import io.crs.hsys.server.repository.HotelRepository;
import io.crs.hsys.server.repository.ReservationRepository;
import io.crs.hsys.server.repository.RoomRepository;
import io.crs.hsys.server.service.RoomService;
import io.crs.hsys.shared.constans.FoRoomStatus;
import io.crs.hsys.shared.constans.RoomStatus;

/**
 * @author CR
 *
 */
public class RoomServiceImpl extends HotelChildServiceImpl<Room, RoomRepository> implements RoomService {
	private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class.getName());

	private ReservationRepository reservationRepository;

	public RoomServiceImpl(RoomRepository repository, AccountRepository accountRepository,
			HotelRepository hotelRepository, ReservationRepository reservationRepository) {
		super(repository, accountRepository, hotelRepository);
		logger.info("RoomServiceImpl");
		this.reservationRepository = reservationRepository;
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
	public Room changeStatus(final String roomKey, final RoomStatus roomStatus) throws Throwable {
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
			return th;
		} catch (RuntimeException re) {
			// A csomagolt kivételt elcsípjük és továbbküldjük
			throw re.getCause();
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
}
