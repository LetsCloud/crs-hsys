/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.layout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.crs.hsys.client.fro.roomplan.widget.Booking;

/**
 * @author robi
 *
 */
public class RoomPlanLayoutDescription {

	private Map<String, RoomLayoutDescription> roomLayouts = new HashMap<String, RoomLayoutDescription>();

	public RoomPlanLayoutDescription(List<Booking> bookings) {
		for (Booking b : bookings) {
			RoomLayoutDescription rld = roomLayouts.get(b.getDescription());
			if (rld != null) {
				rld = new RoomLayoutDescription();
			}
			rld.addBooking(b);
		}

	}
}
