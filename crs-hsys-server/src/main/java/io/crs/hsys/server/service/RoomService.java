/**
 * 
 */
package io.crs.hsys.server.service;

import java.util.Date;
import java.util.List;

import io.crs.hsys.server.entity.hotel.Room;
import io.crs.hsys.shared.constans.RoomStatus;
import io.crs.hsys.shared.dto.hk.RoomStatusDto;
import io.crs.hsys.shared.exception.RestApiException;

/**
 * @author CR
 *
 */
public interface RoomService extends HotelChildService<Room> {

	/**
	 * 
	 * @param hotelKey
	 * @return
	 */
	List<RoomStatusDto> getRoomStatusesByHotel(String hotelKey);

	/**
	 * 
	 * @param hotelKey
	 * @return
	 * @throws Throwable 
	 */
	RoomStatusDto getRoomStatus(String webSafeKey) throws RestApiException;

	/**
	 * 
	 * @param hotelKey
	 * @return
	 */
	List<Room> getActiveRoomsByHotel(String hotelKey);

	/**
	 * Visszaadja a megadott szálloda adott adott nappon rendelkezésre álló szobáit
	 * 
	 * @param hotelKey
	 * @param date
	 * @return
	 */
	List<Room> getAvailableRoomsByHotelOnDate(String hotelKey, Date date);

	List<Room> getAvailableRoomsByHotelOnDateWithReservations(String hotelKey, Date date);

	RoomStatusDto changeStatus(String hotelKey, RoomStatus roomStatus) throws RestApiException;

	/**
	 * 
	 * @param hotelKey
	 * @return
	 * @throws RestApiException 
	 */
	void resetRoomStatus(String hotelKey) throws RestApiException;
}
