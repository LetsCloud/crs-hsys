/**
 * 
 */
package io.crs.hsys.server.entity.hotel;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;

import io.crs.hsys.shared.cnst.OooReturnTime;
import io.crs.hsys.shared.cnst.RoomStatus;

/**
 * @author CR
 *
 */
@Entity
public class OooRoom extends HotelChild {
	private static final Logger logger = LoggerFactory.getLogger(OooRoom.class.getName());

	/**
	 * Szoba hivatkozás.
	 */
	private Ref<Room> room;

	/**
	 * Mettől.
	 */
	private Date fromDate;

	/**
	 * Meddig.
	 */
	private Date toDate;

	private RoomStatus returnAs;
	
	private OooReturnTime returnTime;
	
	/**
	 * Megjegyzés.
	 */
	private String remarks;

	public OooRoom() {
		logger.info("OooRoom(");
	}

	public Room getRoom() {
		if (room == null)
			return null;
		return room.get();
	}

	public void setRoom(Room room) {
		if (room.getId() != null)
			this.room = Ref.create(room);
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public RoomStatus getReturnAs() {
		return returnAs;
	}

	public void setReturnAs(RoomStatus returnAs) {
		this.returnAs = returnAs;
	}

	public OooReturnTime getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(OooReturnTime returnTime) {
		this.returnTime = returnTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
