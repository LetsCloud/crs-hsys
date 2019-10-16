/**
 * 
 */
package io.crs.hsys.server.service;

import io.crs.hsys.shared.dto.rate.update.RoomRateUpdateDto;
import io.crs.hsys.shared.exception.EntityValidationException;
import io.crs.hsys.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
public interface RoomRateService {
	
	void update(RoomRateUpdateDto dto) throws EntityValidationException, UniqueIndexConflictException;

}
