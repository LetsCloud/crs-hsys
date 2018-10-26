/**
 * 
 */
package io.crs.hsys.server.repository;

import java.util.Date;
import java.util.List;

import io.crs.hsys.server.entity.reservation.Reservation;

/**
 * @author CR
 *
 */
public interface ReservationRepository extends CrudRepository<Reservation> {

	List<Reservation> getInHouseReservations(String hotelWebSafeKey, Date onDate);

	List<Reservation> getReservationsArrivalDate(String hotelWebSafeKey, Date onDate);

}
