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
import com.google.gwt.user.client.Timer;
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
import io.crs.hsys.shared.api.AuthResource;
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

	private final PlaceManager placeManager;

	private String manifest;
	private final AppData appData;
	private final ResourceDelegate<GlobalConfigResource> globalConfigResource;
	private final MessagingManager messagingManager;
	private final AppServiceWorkerManager swManager;
	private final RestDispatch dispatch;
	private final AuthResource authService;
	private final CurrentUser currentUser;

	public AbstractAppBootstrapper(PlaceManager placeManager, AppData appData,
			ResourceDelegate<GlobalConfigResource> globalConfigResource, MessagingManager messagingManager,
			AppServiceWorkerManager swManager, RestDispatch dispatch, AuthResource authService,
			CurrentUser currentUser) {
		this.placeManager = placeManager;
		this.appData = appData;
		this.globalConfigResource = globalConfigResource;
		this.messagingManager = messagingManager;
		this.swManager = swManager;
		this.dispatch = dispatch;
		this.authService = authService;
		this.currentUser = currentUser;
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
				messagingManager.setFirebase(firebase);

				configPwaManagerLoop(manifest, 0);
			}
		}).getAll();
	}

	private String getGlobalSetting(List<GlobalConfigDto> result, String key) {
		return result.stream().filter(o -> o.getCode().equals(key)).findFirst().get().getValue();
	}

	private void configPwaManagerLoop(String manifest, Integer attempt) {
		logger.info("configPwaManagerLoop()->attempt=" + attempt);
		if (attempt > 50)
			return;
		if (!configPwaManager(manifest)) {
			Timer t = new Timer() {
				@Override
				public void run() {
					configPwaManagerLoop(manifest, attempt + 1);
				}
			};
			t.schedule(100);
		}
	}

	private Boolean configPwaManager(String manifest) {
//		logger.info("configPwaManager()");
		if (swManager.getFcmManager().isRegistered()) {
			logger.info("configPwaManager()->OK");
			PwaManager.getInstance().setServiceWorker(swManager).setWebManifest(manifest).load();
			configOnFcmMessage();
			swManager.onFcmTokenRefresh(token -> swManager.fcmSubscribe(token));
			checkCurrentUserLoop(0);
			return true;
		}
		return false;
	}

	private void configOnFcmMessage() {
		logger.info("configOnFcmMessage()");
		swManager.onFcmMessage(fcmMessage -> {
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
						Window.Location.reload();
					}
				});
			} else {
				link.setText("MEGNYITOM");
				String href = action.substring(action.indexOf("#"));
				logger.info("configOnFcmMessage()->href=" + href);
				link.setHref(href);
			}
			new MaterialToast(link).toast("ÜZENET:" + fcmMessage.getData().getTitle() + "->"
					+ fcmMessage.getData().getBody(), 10000);
		});
	}

	private void checkCurrentUserLoop(Integer attempt) {
		if (attempt > 100) {
			logger.info("checkCurrentUserLoop()->attempt=" + attempt);
			return;
		}

		if (!checkCurrentUser()) {
			Timer t = new Timer() {
				@Override
				public void run() {
					checkCurrentUserLoop(attempt + 1);
				}
			};
			t.schedule(200);
		}
	}

	private Boolean checkCurrentUser() {
		if (!swManager.isRegistered())
			return false;

		dispatch.execute(authService.getCurrentUser(), new AsyncCallback<AppUserDto>() {

			@Override
			public void onSuccess(AppUserDto result) {
				if (result == null) {
					currentUser.setLoggedIn(false);
					return;
				}
				currentUser.setAppUserDto(result);
				currentUser.getAppUserDto().getAvailableHotels()
						.sort((HotelDtor h1, HotelDtor h2) -> h1.getName().compareTo(h2.getName()));
				currentUser.setLoggedIn(true);

				swManager.requestFcbPermission(() -> swManager.getFcbToken(token -> {
					swManager.fcmSubscribe(token);
				}));

//				menuPresenter.referesh();
				placeManager.revealCurrentPlace();
			}

			@Override
			public void onFailure(Throwable caught) {
				logger.info("AbstractAppPresenter().checkCurrentUser().onFailure()->caught.getMessage()="
						+ caught.getMessage());
			}
		});
		return true;
	}

	protected void setAppCode(String code) {
		appData.setAppCode(code);
		manifest = code.toString() + "_manifest.json";
	}
}