/**
 * 
 */
package io.crs.hsys.server.entity.hotel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import io.crs.hsys.shared.dto.hotel.RoomAvailabilityDto;

/**
 * Szoba rendelkezésre állás
 * 
 * @author CR
 *
 */
public class RoomAvailability {

	/**
	 * Rendelkezésre áll
	 */
	private Boolean available;

	/**
	 * Dátum
	 */
	private Date date;

	public RoomAvailability() {
	}

	/**
	 * Entitás létrehozása DTO-ból
	 * 
	 * @param dto
	 */
	public RoomAvailability(RoomAvailabilityDto dto) {
		this();
		this.date = dto.getDate();
		this.available = dto.getAvailable();
	}

	public RoomAvailability(Boolean open, Date date) {
		this();
		this.available = open;
		this.date = date;
	}

	public RoomAvailability(RoomAvailability source) {
		this();
		this.available = source.available;
		this.date = source.date;
	}

	public Boolean isAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "RoomAvailability [available=" + available + ", date=" + date + "]";
	}

	/**
	 * DTO létrehozása entitásból
	 * 
	 * @param entity
	 * @return
	 */
	public static RoomAvailabilityDto createDto(RoomAvailability entity) {
		RoomAvailabilityDto dto = new RoomAvailabilityDto();
		dto = entity.updateDto(dto);
		return dto;
	}

	/**
	 * DTO módosítása entitás értékekkel
	 * 
	 * @param dto
	 * @return
	 */
	public RoomAvailabilityDto updateDto(RoomAvailabilityDto dto) {
		dto.setDate(this.getDate());
		dto.setAvailable(this.isAvailable());
		return dto;
	}

	/**
	 * Enitás lista létrehozása DTO listából
	 * 
	 * @param dtos
	 * @return
	 */
	public static List<RoomAvailability> createList(List<RoomAvailabilityDto> dtos) {
		List<RoomAvailability> list = new ArrayList<RoomAvailability>();
		for (RoomAvailabilityDto dto : dtos) {
			RoomAvailability entity = new RoomAvailability(dto);
			list.add(entity);
		}
		return list;
	}

	/**
	 * Az IFA mértékek érvényeségi dátum szerint rendezését szolgáló Comparator
	 */
	public static Comparator<RoomAvailability> ORDER_BY_DATE = new Comparator<RoomAvailability>() {
		public int compare(RoomAvailability one, RoomAvailability other) {
			return one.getDate().compareTo(other.getDate());
		}
	};
}
