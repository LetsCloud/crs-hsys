/**
 * 
 */
package io.crs.hsys.server.entity.hotel;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

/**
 * @author robi
 *
 */
@Entity
public class HotelChildGuid extends HotelChild {

	public static final String RESERVATION_NO = "reservationNo";

	@Index
	private String idKind;

	private Long lastId;

	public String getIdKind() {
		return idKind;
	}

	public void setIdKind(String idKind) {
		this.idKind = idKind;
	}

	public Long getLastId() {
		return lastId;
	}

	public void setLastId(Long lastId) {
		this.lastId = lastId;
	}

	@Override
	public String toString() {
		return "HotelChildGuid [idKind=" + idKind + ", lastId=" + lastId + "]";
	}

}
