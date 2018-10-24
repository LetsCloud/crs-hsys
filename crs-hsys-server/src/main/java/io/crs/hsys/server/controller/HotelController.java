/**
 * 
 */
package io.crs.hsys.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.crs.hsys.server.entity.hotel.Hotel;
import io.crs.hsys.server.service.HotelService;
import io.crs.hsys.shared.dto.hotel.HotelDto;
import io.crs.hsys.shared.dto.hotel.HotelDtor;
import io.crs.hsys.shared.exception.RestApiException;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.REDUCED;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.HOTEL;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + HOTEL, produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelController extends CrudController<Hotel, HotelDto> {
	private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

	private final ModelMapper modelMapper;

	@Autowired
	HotelController(HotelService service, ModelMapper modelMapper) {
		super(Hotel.class, service, modelMapper);
		logger.info("HotelController()");
		this.modelMapper = modelMapper;
	}

	@Override
	protected HotelDto createDto(Hotel entity) {
		HotelDto dto = modelMapper.map(entity, HotelDto.class);
		return dto;
	}

	/**
	 * Visszaadja a bejelentkezett felhasználó előfizetéséhez tartozó összes
	 * szállodát
	 * 
	 * @return
	 */
	@Override
	@RequestMapping(method = GET)
	public @ResponseBody ResponseEntity<List<HotelDto>> getAll() {
		return super.getAll();
	}

	/**
	 * Visszaadja a bejelentkezett felhasználó előfizetéséhez tartozó összes
	 * szállodát
	 * 
	 * @return
	 */
	@RequestMapping(value = REDUCED, method = GET)
	public @ResponseBody ResponseEntity<List<HotelDtor>> getAllReduced() {
		List<HotelDtor> dtos = new ArrayList<HotelDtor>();

		for (Hotel entity : getAll2())
			dtos.add(modelMapper.map(entity, HotelDtor.class));

		return new ResponseEntity<List<HotelDtor>>(dtos, OK);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<HotelDto> get(@PathVariable String webSafeKey) throws RestApiException {
		logger.info("HotelController().get()");
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<HotelDto> saveOrCreate(@RequestBody HotelDto dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}
}
