/**
 * 
 */
package io.crs.hsys.server.entity.hotel;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.shared.cnst.OooReturnWhen;
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
	@Index
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

	private OooReturnWhen returnWhen;

	/**
	 * Megjegyzés.
	 */
	private String remarks;

	private Ref<AppUser> createdBy;

	public OooRoom() {
		logger.info("OooRoom(");
	}

	public OooRoom(Room room, Date fromDate, Date toDate, RoomStatus returnAs, OooReturnWhen returnWhen, String remarks,
			AppUser createdBy, Hotel hotel) {
		this();
		setRoom(room);
		setFromDate(fromDate);
		setToDate(toDate);
		setReturnAs(returnAs);
		setReturnWhen(returnWhen);
		setRemarks(remarks);
		setCreatedBy(createdBy);
		setHotel(hotel);
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

	public OooReturnWhen getReturnWhen() {
		return returnWhen;
	}

	public void setReturnWhen(OooReturnWhen returnWhen) {
		this.returnWhen = returnWhen;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public AppUser getCreatedBy() {
		if (createdBy == null)
			return null;
		return createdBy.get();
	}

	public void setCreatedBy(AppUser createdBy) {
		if (createdBy.getId() != null)
			this.createdBy = Ref.create(createdBy);
	}

	@Override
	public String toString() {
		return "OooRoom [room=" + room + ", fromDate=" + fromDate + ", toDate=" + toDate + ", returnAs=" + returnAs
				+ ", returnWhen=" + returnWhen + ", remarks=" + remarks + ", createdBy=" + createdBy + "]";
	}

}
