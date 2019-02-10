/**
 * 
 */
package io.crs.hsys.client.core;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.PreBootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import gwt.material.design.client.pwa.PwaManager;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;

import io.crs.hsys.client.core.app.AppServiceWorkerManager;
import io.crs.hsys.client.core.firebase.Config;
import io.crs.hsys.client.core.firebase.Firebase;
import io.crs.hsys.client.core.firebase.messaging.MessagingManager;
import io.crs.hsys.client.core.security.AppData;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.util.AbstractAsyncCallback;
import io.crs.hsys.client.core.util.Base64Utils;
import io.crs.hsys.shared.api.AuthResource;
import io.crs.hsys.shared.api.FcmResource;
import io.crs.hsys.shared.api.GlobalConfigResource;
import io.crs.hsys.shared.constans.GlobalParam;
import io.crs.hsys.shared.dto.GlobalConfigDto;
import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.dto.hotel.HotelDtor;

/**
 * @author robi
 *
 */
public abstract class AbstractAppBootstrapper implements Bootstrapper {
	private static Logger logger = Logger.getLogger(AbstractAppBootstrapper.class.getName());

	public static final String TARGET_URL = "targetUrl";
	public static final String LOGIN_URL = "/login";

	private final PlaceManager placeManager;

	private String manifest;
	private final AppData appData;
	private final ResourceDelegate<GlobalConfigResource> globalConfigResource;
	private final MessagingManager messagingManager;
	private final AppServiceWorkerManager swManager;
	private final RestDispatch dispatch;
	private final AuthResource authService;
	private final FcmResource fcmService;
	private final CurrentUser currentUser;

	public AbstractAppBootstrapper(PlaceManager placeManager, AppData appData,
			ResourceDelegate<GlobalConfigResource> globalConfigResource, MessagingManager messagingManager,
			AppServiceWorkerManager swManager, RestDispatch dispatch, AuthResource authService, CurrentUser currentUser,
			FcmResource fcmService) {
		this.placeManager = placeManager;
		this.appData = appData;
		this.globalConfigResource = globalConfigResource;
		this.messagingManager = messagingManager;
		this.swManager = swManager;
		this.dispatch = dispatch;
		this.authService = authService;
		this.currentUser = currentUser;
		this.fcmService = fcmService;
	}

	public static class PreApplicationImpl implements PreBootstrapper {
		private static Logger logger = Logger.getLogger(PreApplicationImpl.class.getName());

		@Override
		public void onPreBootstrap() {
			GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
				public void onUncaughtException(Throwable e) {
					logger.log(Level.SEVERE, e.getMessage(), e);
				}
			});
		}
	}

	@Override
	public void onBootstrap() {
		initFirebase();
	}

	private void initFirebase() {
		logger.info("initFirebase()");

		globalConfigResource.withCallback(new AbstractAsyncCallback<List<GlobalConfigDto>>() {
			@Override
			public void onSuccess(List<GlobalConfigDto> result) {
				logger.info("provideMessagingManager().onSuccess()");

				Config config = new Config();
				config.setApiKey(getGlobalSetting(result, GlobalParam.FB1_API_KEY.name()));
				config.setAuthDomain(getGlobalSetting(result, GlobalParam.FB2_AUTH_DOMAIN.name()));
				config.setDatabaseURL(getGlobalSetting(result, GlobalParam.FB3_DATABASE_URL.name()));
				config.setProjectId(getGlobalSetting(result, GlobalParam.FB4_PROJECT_ID.name()));
				config.setStorageBucket(getGlobalSetting(result, GlobalParam.FB5_STORAGE_BUCKET.name()));
				config.setMessagingSenderId(getGlobalSetting(result, GlobalParam.FB6_MESSAGE_SENDER_ID.name()));

				Firebase firebase = Firebase.initializeApp(config);
				logger.info("provideMessagingManager().onSuccess()->firebase.getName()" + firebase.getName());

				cfgMessagingManager(firebase);

				configFcmOnMessage();

				cfgPwaManager();

				checkCurrentUser();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				logger.info("provideMessagingManager().onFailure()");
				checkCurrentUser();
			}
		}).getAll();
	}

	private String getGlobalSetting(List<GlobalConfigDto> result, String key) {
		return result.stream().filter(o -> o.getCode().equals(key)).findFirst().get().getValue();
	}

	private void cfgMessagingManager(Firebase firebase) {
		messagingManager.setFirebase(firebase);
//		messagingManager.onTokenRefresh(token -> fcmSubscribe(token));
	}

	private void configFcmOnMessage() {
		logger.info("configOnFcmMessage()");
		messagingManager.onMessage(fcmMessage -> {
			logger.info("configOnFcmMessage()->dataMessage.getData().getClick_action()="
					+ fcmMessage.getData().getAction());

			String action = fcmMessage.getData().getAction();
			logger.info("configOnFcmMessage()->action=" + action);

			String href2 = Window.Location.getHref();
			logger.info("configOnFcmMessage()->href2=" + href2);

			MaterialLink link = new MaterialLink();
			if (action.equals(href2)) {
				link.setText("FRISSÍTEM");
				link.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						logger.info("configFcmOnMessage()->refresh.onClick()");
						Window.Location.reload();
					}
				});
			} else {
				link.setText("MEGNYITOM");
				String href = action.substring(action.indexOf("#"));
				logger.info("configOnFcmMessage()->href=" + href);
				link.setHref(href);
			}
			new MaterialToast(link)
					.toast("ÜZENET:" + fcmMessage.getData().getTitle() + "->" + fcmMessage.getData().getBody(), 10000);
		});
	}

	private void cfgPwaManager() {
		logger.info("configPwaManager()->OK");
		PwaManager.getInstance().setServiceWorker(swManager).setWebManifest(getManifest()).load();
	}

	private void checkCurrentUser() {
		dispatch.execute(authService.getCurrentUser(), new AsyncCallback<AppUserDto>() {

			@Override
			public void onSuccess(AppUserDto result) {
				logger.info("checkCurrentUser().onSuccess()");
				if (result == null) {
					logger.info("checkCurrentUser().onSuccess()->(result == null)");
					currentUser.setLoggedIn(false);
					return;
				}
				currentUser.setAppUserDto(result);
				currentUser.getAppUserDto().getAvailableHotels()
						.sort((HotelDtor h1, HotelDtor h2) -> h1.getName().compareTo(h2.getName()));
				currentUser.setLoggedIn(true);

				messagingManager.requestPermission(() -> messagingManager.getToken(token -> {
					fcmSubscribe(token);
				}));

//				menuPresenter.referesh();
				placeManager.revealCurrentPlace();
			}

			@Override
			public void onFailure(Throwable caught) {
				logger.info("AbstractAppPresenter().checkCurrentUser().onFailure()->caught.getMessage()="
						+ caught.getMessage());

				String baseUrl = GWT.getHostPageBaseURL();
				logger.log(Level.INFO, "getApplicationPath()->baseUrl=" + baseUrl);

				if (baseUrl.endsWith("/")) {
					baseUrl = baseUrl.substring(0, baseUrl.length() - 5);
					logger.log(Level.INFO, "if (baseUrl.endsWith(/))->baseUrl=" + baseUrl);
				} else {
					baseUrl = baseUrl.substring(0, baseUrl.length() - 4);
					logger.log(Level.INFO, "if (baseUrl.endsWith(/))->baseUrl=" + baseUrl);
				}

				String pathString = Window.Location.getPath() + Window.Location.getHash();
				String pathB64 = Base64Utils.toBase64(pathString.getBytes());

				Window.Location.replace(baseUrl + LOGIN_URL + "?" + TARGET_URL + "=" + pathB64);
			}
		});
	}

	protected void setAppCode(String code) {
		appData.setAppCode(code);
		manifest = code.toString() + "_manifest.json";
	}

	private String getManifest() {
		return manifest;
	}

	/**
	 * 
	 * @param iidToken
	 */
	public void fcmSubscribe(String iidToken) {
//		logger.info("fcmSubscribe()->iidToken=" + iidToken);
		String userAgent = Base64Utils.toBase64(getUserAgent().getBytes());
//		logger.info("fcmSubscribe()->userAgent=" + userAgent);
		dispatch.execute(fcmService.subscribe(iidToken, userAgent), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void response) {
				MaterialToast.fireToast("Sussecfull subscription!");
			}

			@Override
			public void onFailure(Throwable throwable) {
				MaterialToast.fireToast(throwable.getMessage());
			}
		});
	}

	/**
	 * 
	 * @return
	 */
	public static native String getUserAgent() /*-{
												return $wnd.navigator.userAgent.toLowerCase();
												}-*/;

}