/**
 * 
 */
package io.crs.hsys.server.repository.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;

import io.crs.hsys.server.entity.hotel.Hotel;
import io.crs.hsys.server.entity.reservation.Reservation;
import io.crs.hsys.server.entity.reservation.RoomStay;
import io.crs.hsys.server.repository.ReservationRepository;
import io.crs.hsys.shared.constans.ReservationStatus;

/**
 * @author CR
 *
 */
public class ReservationRepositoryImpl extends CrudRepositoryImpl<Reservation> implements ReservationRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRepositoryImpl.class.getName());

	public ReservationRepositoryImpl() {
		super(Reservation.class);
		LOGGER.info("ReservationRepositoryImpl()");
	}

	@Override
	protected Object getParent(Reservation entity) {
		return entity.getHotel();
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<Reservation> key = getKey(webSafeString);
		return key.getParent().getParent().getString();
	}

	@Override
	public List<Reservation> getInHouseReservations(String hotelWebSafeKey, Date onDate) {
		List<Reservation> result = new ArrayList<Reservation>();
		Key<Hotel> hotelKey = Key.create(hotelWebSafeKey);

		List<Reservation> result1 = getArrivalsOnDate(hotelKey, onDate);
		if (result1 != null)
			result.addAll(result1);

		List<Reservation> result2 = getStayoversOnDate(hotelKey, onDate);
		if (result2 != null)
			result.addAll(result2);

		return result;
	}

	/**
	 * 
	 * @param hotelKey
	 * @param onDate
	 * @return
	 */
	private List<Reservation> getArrivalsOnDate(Key<Hotel> hotelKey, Date onDate) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(Reservation.FLD_STATUS + " =", ReservationStatus.DEFINITIVE);
		filters.put(Reservation.FLD_ROOMSTAYS + "." + RoomStay.FLD_ARRIVAL + " =", onDate);
		List<Reservation> result = getChildrenByFilters(hotelKey, filters);
		return result;
	}

	/**
	 * 
	 * @param hotelKey
	 * @param onDate
	 * @return
	 */
	private List<Reservation> getStayoversOnDate(Key<Hotel> hotelKey, Date onDate) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(Reservation.FLD_STATUS + " =", ReservationStatus.CHECKED_IN);
		filters.put(Reservation.FLD_ROOMSTAYS + "." + RoomStay.FLD_ARRIVAL + " <=", onDate);
		List<Reservation> result = getChildrenByFilters(hotelKey, filters);
		return result;
	}

	@Override
	public List<Reservation> getReservationsArrivalDate(String hotelWebSafeKey, Date onDate) {
		Key<Hotel> hotelKey = Key.create(hotelWebSafeKey);
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(Reservation.FLD_ROOMSTAYS + "." + RoomStay.FLD_ARRIVAL + " =", onDate);
		List<Reservation> result = getChildrenByFilters(hotelKey, filters);
		return result;
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Hotel> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(Reservation entiy) {
		// TODO Auto-generated method stub
		
	}
}
