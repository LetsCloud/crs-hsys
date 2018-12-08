/**
 * 
 */
package io.crs.hsys.client.core.gin;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Singleton;

import com.google.inject.Provides;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AppServiceWorkerManager;
import io.crs.hsys.client.core.datasource.DataSourceModule;
import io.crs.hsys.client.core.firebase.Config;
import io.crs.hsys.client.core.firebase.Firebase;
import io.crs.hsys.client.core.firebase.messaging.MessagingManager;
import io.crs.hsys.client.core.menu.MenuModule;
import io.crs.hsys.client.core.security.AppData;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.unauthorized.UnauthorizedModule;
import io.crs.hsys.client.core.util.AbstractAsyncCallback;
import io.crs.hsys.shared.api.FcmResource;
import io.crs.hsys.shared.api.GlobalConfigResource;
import io.crs.hsys.shared.constans.GlobalParam;
import io.crs.hsys.shared.dto.GlobalConfigDto;

/**
 * @author CR
 *
 */
public class CoreModule extends AbstractPresenterModule {
	private static Logger logger = Logger.getLogger(CoreModule.class.getName());

	@Override
	protected void configure() {
		logger.info("CoreModule.configure(");
		install(new DefaultModule.Builder().placeManager(DefaultPlaceManager.class)
				.tokenFormatter(RouteTokenFormatter.class).build());

		bind(AppData.class).asEagerSingleton();
		bind(CurrentUser.class).asEagerSingleton();

		bindConstant().annotatedWith(DefaultPlace.class).to(CoreNameTokens.HOME);
		bindConstant().annotatedWith(ErrorPlace.class).to(CoreNameTokens.HOME);
		bindConstant().annotatedWith(UnauthorizedPlace.class).to(CoreNameTokens.HOME);

		bind(ResourceLoader.class).asEagerSingleton();

		install(new UnauthorizedModule());
		install(new ServiceModule());
		install(new DataSourceModule());
		install(new MenuModule());
	}

	@Provides
	@Singleton
	MessagingManager provideMessagingManager(ResourceDelegate<GlobalConfigResource> resourceDelegate) {

		MessagingManager messagingManager = new MessagingManager();

		resourceDelegate.withCallback(new AbstractAsyncCallback<List<GlobalConfigDto>>() {
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
			}
		}).getAll();

		return messagingManager;
	}

	private String getGlobalSetting(List<GlobalConfigDto> result, String key) {
		return result.stream().filter(o -> o.getCode().equals(key)).findFirst().get().getValue();
	}

	@Provides
	@Singleton
	AppServiceWorkerManager provideAppServiceWorkerManager(EventBus eventBus, MessagingManager fcmManager,
			RestDispatch dispatch, FcmResource fcmService) {
		logger.info("provideAppServiceWorkerManager()");

		AppServiceWorkerManager serviceWorkerManager = new AppServiceWorkerManager("service-worker.js", eventBus,
				fcmManager, dispatch, fcmService);

		return serviceWorkerManager;
	}
}
