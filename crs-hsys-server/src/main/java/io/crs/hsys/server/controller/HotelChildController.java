/**
 * 
 */
package io.crs.hsys.server.controller;

import org.modelmapper.ModelMapper;

import io.crs.hsys.server.entity.BaseEntity;
import io.crs.hsys.server.service.HotelChildService;
import io.crs.hsys.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public abstract class HotelChildController<T extends BaseEntity, D extends BaseDto> extends CrudController<T, D> {

	public HotelChildController(Class<T> clazz, HotelChildService<T> service, ModelMapper modelMapper) {
		super(clazz, service, modelMapper);
	}

}
