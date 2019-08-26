/**
 * 
 */
package io.crs.hsys.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.crs.hsys.server.entity.hotel.RateCode;
import io.crs.hsys.server.service.RateCodeService;
import io.crs.hsys.shared.cnst.InventoryType;
import io.crs.hsys.shared.dto.hotel.RateCodeDto;
import io.crs.hsys.shared.dto.hotel.RateCodeDtor;
import io.crs.hsys.shared.exception.RestApiException;

import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;
import static io.crs.hsys.shared.api.ApiParameters.ONLY_ACTIVE;
import static io.crs.hsys.shared.api.ApiParameters.SEL_INV_TYPE;
import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.REDUCED;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.RATE_CODE;
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
@RequestMapping(value = ROOT + RATE_CODE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RateCodeController extends HotelChildController<RateCode, RateCodeDto> {
	private static final Logger logger = LoggerFactory.getLogger(RateCodeController.class);

	private final ModelMapper modelMapper;

	@Autowired
	RateCodeController(RateCodeService service, ModelMapper modelMapper) {
		super(RateCode.class, service, modelMapper);
		logger.info("RateCodeController()");
		this.modelMapper = modelMapper;
	}

	@Override
	protected RateCodeDto createDto(RateCode entity) {
		RateCodeDto dto = modelMapper.map(entity, RateCodeDto.class);
		return dto;
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<RateCodeDto>> getByHotelWithFilters(@RequestParam(HOTEL_KEY) String hotelKey,
			@RequestParam(ONLY_ACTIVE) Boolean onlyActive) {
		Map<String, Object> filters = new HashMap<String, Object>();
//		if (onlyActive)
//			filters.put("active", onlyActive);

		return getChildrenByFilters(hotelKey, filters);
	}

	/**
	 * Visszaadja a bejelentkezett felhasználó előfizetéséhez tartozó összes
	 * szállodát
	 * 
	 * @return
	 */
	@RequestMapping(value = REDUCED, method = GET)
	public @ResponseBody ResponseEntity<List<RateCodeDtor>> getAllReduced(@RequestParam(HOTEL_KEY) String hotelKey) {
		List<RateCodeDtor> dtos = new ArrayList<RateCodeDtor>();

		Map<String, Object> filters = new HashMap<String, Object>();
		
		for (RateCode entity : service.getChildrenByFilters(hotelKey, filters)) {
			dtos.add(modelMapper.map(entity, RateCodeDtor.class));
		}

		return new ResponseEntity<List<RateCodeDtor>>(dtos, OK);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<RateCodeDto> get(@PathVariable String webSafeKey) throws RestApiException {
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<RateCodeDto> saveOrCreate(@RequestBody RateCodeDto dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

}
