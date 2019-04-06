/**
 * 
 */
package io.crs.hsys.server.controller;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.QUOTATION;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.crs.hsys.server.entity.doc.Quotation;
import io.crs.hsys.server.service.QuotationService;
import io.crs.hsys.shared.dto.doc.QuotationDto;
import io.crs.hsys.shared.exception.RestApiException;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + QUOTATION, produces = MediaType.APPLICATION_JSON_VALUE)
public class QuotationController extends CrudController<Quotation, QuotationDto> {
	private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

	private final ModelMapper modelMapper;

	@Autowired
	QuotationController(QuotationService service, ModelMapper modelMapper) {
		super(Quotation.class, service, modelMapper);
		logger.info("ContactController()");
		this.modelMapper = modelMapper;
	}

	@Override
	protected QuotationDto createDto(Quotation entity) {
		QuotationDto dto = modelMapper.map(entity, QuotationDto.class);
		return dto;
	}

	// @RequestParam(ONLY_ACTIVE) Boolean onlyActive
	@RequestMapping(method = GET)
	public ResponseEntity<List<QuotationDto>> getAll() {
		return super.getAll();
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<QuotationDto> get(@PathVariable String webSafeKey) throws RestApiException {
		logger.info("get()->webSafeKey=" + webSafeKey);
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<QuotationDto> saveOrCreate(@RequestBody QuotationDto dto) throws RestApiException {
		logger.info("ContactController().saveOrCreate(" + dto + ")");
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

}
