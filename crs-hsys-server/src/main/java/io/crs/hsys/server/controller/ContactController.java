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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.crs.hsys.server.entity.profile.Contact;
import io.crs.hsys.server.entity.profile.Profile;
import io.crs.hsys.server.entity.profile.ProfileLink;
import io.crs.hsys.server.entity.profile.Relationship;
import io.crs.hsys.server.service.ContactService;
import io.crs.hsys.server.service.ProfileLinkService;
import io.crs.hsys.shared.dto.profile.ContactDto;
import io.crs.hsys.shared.dto.profile.ProfileDtor;
import io.crs.hsys.shared.dto.profile.ProfileLinkDto;
import io.crs.hsys.shared.exception.RestApiException;

import static io.crs.hsys.shared.api.ApiParameters.ONLY_ACTIVE;
import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.CONTACT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + CONTACT, produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactController extends CrudController<Contact, ContactDto> {
	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

	private final ModelMapper modelMapper;

	private final ProfileLinkService profileLinkService;

	@Autowired
	ContactController(ContactService service, ModelMapper modelMapper, ProfileLinkService profileLinkService) {
		super(Contact.class, service, modelMapper);
		logger.info("ContactController()");
		this.modelMapper = modelMapper;
		this.profileLinkService = profileLinkService;
	}

	@Override
	protected ContactDto createDto(Contact entity) {
		ContactDto dto = modelMapper.map(entity, ContactDto.class);
		return dto;
	}

	// @RequestParam(ONLY_ACTIVE) Boolean onlyActive
	@RequestMapping(method = GET)
	public ResponseEntity<List<ContactDto>> getAll() {
		return super.getAll();
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<ContactDto> get(@PathVariable String webSafeKey) throws RestApiException {
		logger.info("get()->webSafeKey=" + webSafeKey);
		return super.get(webSafeKey);
	}

	@Override
	protected ContactDto afterGet(ContactDto dto, Contact entity) throws RestApiException {
		logger.info("afterGet()");
		List<ProfileLinkDto> profileLinkDtos = new ArrayList<ProfileLinkDto>();
		profileLinkDtos.addAll(getPartOfProfileLinks(entity, ProfileLink.FLD_PROFILE_FORWARD));
		profileLinkDtos.addAll(getPartOfProfileLinks(entity, ProfileLink.FLD_PROFILE_REVERSE));
		dto.setProfileLinks(profileLinkDtos);
		return dto;
	}

	private List<ProfileLinkDto> getPartOfProfileLinks(Contact entity, String partName) {
		logger.info("getPartOfProfileLinks()");
		List<ProfileLinkDto> profileLinkDtos = new ArrayList<ProfileLinkDto>();
		/*
		 * try { PropertyMap<ProfileLink, ProfileLinkDto> personMap = new
		 * PropertyMap<ProfileLink, ProfileLinkDto>() { protected void configure() {
		 * skip().setProfile(null); } }; modelMapper.addMappings(personMap); } catch
		 * (Throwable e) { e.printStackTrace(); }
		 */
		List<ProfileLink> profileLinks = profileLinkService.getByProfile(entity, partName);
		for (ProfileLink profileLink : profileLinks) {
			logger.info("getPartOfProfileLinks()->profileLink=" + profileLink);

			try {
				ProfileLinkDto profileLinkDto = modelMapper.map(profileLink, ProfileLinkDto.class);

				logger.info("getPartOfProfileLinks()-2");
				profileLinkDto.setSwitched(false);
				logger.info("getPartOfProfileLinks()-3");
				if (partName.equals(ProfileLink.FLD_PROFILE_REVERSE)) {
					logger.info("getPartOfProfileLinks()-4");
					profileLinkDto.setProfile(modelMapper.map(profileLink.getForward(), ProfileDtor.class));
				} else {
					logger.info("getPartOfProfileLinks()-5");
					profileLinkDto.setProfile(modelMapper.map(profileLink.getReverse(), ProfileDtor.class));
				}
				logger.info("getPartOfProfileLinks()-6");
				profileLinkDtos.add(profileLinkDto);
				logger.info("getPartOfProfileLinks()->profileLinkDto=" + profileLinkDto);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		return profileLinkDtos;

	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<ContactDto> saveOrCreate(@RequestBody ContactDto dto) throws RestApiException {
		logger.info("ContactController().saveOrCreate(" + dto + ")");
		return super.saveOrCreate(dto);
	}

	@Override
	protected void afterSaveOrCreate(ContactDto sourceDto, Contact saved) throws RestApiException {
		logger.info("afterSaveOrCreate()->sourceDto=" + sourceDto);
		logger.info("afterSaveOrCreate()->saved=" + saved);
		for (ProfileLinkDto profileLinkDto : sourceDto.getProfileLinks()) {
			logger.info("afterSaveOrCreate()->profileLinkDto=" + profileLinkDto);
			Profile profile = modelMapper.map(profileLinkDto.getProfile(), Profile.class);
			ProfileLink profileLink = new ProfileLink();
			profileLink.setId(profileLinkDto.getId());
			profileLink.setWebSafeKey(profileLinkDto.getWebSafeKey());
			profileLink.setVersion(profileLinkDto.getVersion());
			profileLink.setAccount(saved.getAccount());
			profileLink.setRelationship(modelMapper.map(profileLinkDto.getRelationship(), Relationship.class));
			if (profileLinkDto.getSwitched()) {
				profileLink.setForward(saved);
				profileLink.setReverse(profile);
			} else {
				profileLink.setForward(profile);
				profileLink.setReverse(saved);
			}
			logger.info("afterSaveOrCreate()->profileLink=" + profileLink);
			try {
				if (profileLinkDto.getId() == null)
					profileLinkService.create(profileLink);
				else
					profileLinkService.update(profileLink);
			} catch (Throwable e) {
				e.printStackTrace();
				throw new RestApiException(e);
			}
		}
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

}
