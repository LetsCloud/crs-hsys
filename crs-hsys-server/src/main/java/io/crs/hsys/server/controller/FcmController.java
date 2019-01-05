/**
 * 
 */
package io.crs.hsys.server.controller;

import static io.crs.hsys.shared.api.ApiParameters.IID_TOKEN;
import static io.crs.hsys.shared.api.ApiParameters.USER_AGENT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.FCM;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.MESSAGE;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.ROOT;
import static io.crs.hsys.shared.api.ApiPaths.SpaV1.SUBSCRIBE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.crs.hsys.server.entity.common.AppUser;
import io.crs.hsys.server.entity.common.FcmToken;
import io.crs.hsys.server.model.Subscription;
import io.crs.hsys.server.service.AppUserService;
import io.crs.hsys.server.service.FcmService;
import io.crs.hsys.shared.dto.chat.FcmMessageDto;
import io.crs.hsys.shared.dto.chat.FcmNotificationDto;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + FCM, produces = MediaType.APPLICATION_JSON_VALUE)
public class FcmController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FcmController.class);

	static List<Subscription> subscriptions = new ArrayList<Subscription>();

	private final AppUserService userService;
	private final FcmService fcmService;
	private final ObjectMapper objectMapper;

	@Autowired
	FcmController(AppUserService userService, FcmService fcmService, ObjectMapper objectMapper) {
		logger.info("FcmController()");
		this.userService = userService;
		this.fcmService = fcmService;
		this.objectMapper = objectMapper;
	}

	@RequestMapping(value = SUBSCRIBE, params = { IID_TOKEN, USER_AGENT }, method = POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void subscribe(@RequestParam String iidToken, @RequestParam String userAgent) {
		logger.info("subscribe(IID_TOKEN=" + iidToken + ", USER_AGENT=" + userAgent + ")");
		try {
			userService.fcmSubscribe(iidToken, userAgent);
		} catch (Throwable e) {
			logger.info("subscribe->catch Exeption->" + e.getMessage());
			e.printStackTrace();
		}
	}

	@RequestMapping(value = SUBSCRIBE, method = DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void unsubscribeAll() {
		logger.info("unsubscribeAll()");
		subscriptions.clear();
	}

	@RequestMapping(value = MESSAGE, method = POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void notifyAllUser(@RequestBody FcmNotificationDto notification, WebRequest request) {
		logger.info("notifyAllUser()-START");

		List<String> tokens = getTokens(userService.getAll(getAccountId(request)));
		try {
			for (String token : tokens) {
				FcmMessageDto message = new FcmMessageDto(token, notification);
				fcmService.postMessage(objectMapper.writeValueAsString(message));
			}
			logger.info("notifyAllUser()-END");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> getTokens(List<AppUser> appUsers) {
		List<String> tokens = new ArrayList<String>();

		for (AppUser au : appUsers) {
			for (FcmToken t : au.getFcmTokens()) {
				if (!tokens.contains(t.getToken()))
					tokens.add(t.getToken());
			}
		}
		return tokens;
	}
}
