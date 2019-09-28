/**
 * 
 */
package io.crs.hsys.server.entity.hotel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

/**
 * @author robi
 *
 */
@Entity
public class RoomRate extends HotelChild {

	/**
	 * Árkód.
	 */
	@Index
	private Ref<RateCode> rateCode;

	/**
	 * Árkód.
	 */
	@Index
	private Ref<RoomType> roomType;

	/**
	 * 
	 */
	private Map<Date, RoomRateByDate> roomRatesByDate = new HashMap<Date, RoomRateByDate>();

	/**
	 * 
	 */
	public RoomRate() {
	}

	public RateCode getRateCode() {
		if (rateCode == null)
			return null;
		return rateCode.get();
	}

	public void setRateCode(RateCode rateCode) {
		this.rateCode = Ref.create(rateCode);
	}

	public RoomType getRoomType() {
		if (roomType == null)
			return null;
		return roomType.get();
	}

	public void setRoomType(RoomType roomType) {
		if (roomType.getId() != null)
			this.roomType = Ref.create(roomType);
	}

	public Map<Date, RoomRateByDate> getRoomRatesByDate() {
		return roomRatesByDate;
	}

	public void setRoomRatesByDate(Map<Date, RoomRateByDate> roomRatesByDate) {
		this.roomRatesByDate = roomRatesByDate;
	}

	public RoomRateByDate getRoomRate(Date date) {
		return roomRatesByDate.get(date);
	}

	public void setRoomRate(Date date, RoomRateByDate roomRateByDate) {
		if (roomRatesByDate.containsKey(date))
			roomRatesByDate.replace(date, roomRateByDate);
		else
			roomRatesByDate.put(date, roomRateByDate);
	}
}
