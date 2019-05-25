/**
 * 
 */
package io.crs.hsys.server.service;

import io.crs.hsys.server.entity.hotel.OooRoom;
import io.crs.hsys.shared.dto.hotel.OooCreateDto;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.ForeignKeyConflictException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author CR
 *
 */
public interface OooRoomService extends HotelChildService<OooRoom> {
	
	void createOooRooms(OooCreateDto dto) throws ForeignKeyConflictException, EntityValidationException, UniqueIndexConflictException;

}
