/**
 * 
 */
package io.crs.hsys.server.entity.hotel;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;

/**
 * @author robi
 *
 */
public class RateByRoomType {

	private Ref<RoomType> roomType;

	private List<Rate> rates = new ArrayList<Rate>();

	public RateByRoomType() {
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

	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}

}
