/**
 * 
 */
package io.crs.hsys.client.core.gin;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Singleton;

import com.google.inject.Provides;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
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
import io.crs.hsys.shared.api.FcmResource;

/**
 * @author CR
 *
 */
public class CoreModule extends AbstractPresenterModule {
	private static Logger logger = Logger.getLogger(CoreModule.class.getName());

	@Override
	protected void configure() {
		install(new DefaultModule.Builder().placeManager(DefaultPlaceManager.class)
				.tokenFormatter(RouteTokenFormatter.class).build());

		bind(AppData.class).asEagerSingleton();
		bind(CurrentUser.class).asEagerSingleton();

		bindConstant().annotatedWith(DefaultPlace.class).to(CoreNameTokens.HOME);
		bindConstant().annotatedWith(ErrorPlace.class).to(CoreNameTokens.LOGIN);
		bindConstant().annotatedWith(UnauthorizedPlace.class).to(CoreNameTokens.LOGIN);

		bind(ResourceLoader.class).asEagerSingleton();

		install(new UnauthorizedModule());
		install(new ServiceModule());
		install(new DataSourceModule());
		install(new MenuModule());
	}

	@Provides
	@Singleton
	MessagingManager provideMessagingManager() {
		Config config = new Config();
		config.setApiKey("AIzaSyCldBkLB_W7v7p-CUCW_ZkedBVLoWSuKLU");
		config.setAuthDomain("hw-cloud8.firebaseapp.com");
		config.setDatabaseURL("https://hw-cloud3.firebaseio.com");
		config.setProjectId("hw-cloud8");
		config.setStorageBucket("hw-cloud8.appspot.com");
		config.setMessagingSenderId("103271768970");
		Firebase firebase = Firebase.initializeApp(config);
		logger.log(Level.INFO, "NotificationsPresenter.onBind().firebase.getName()" + firebase.getName());

		MessagingManager messagingManager = new MessagingManager(firebase);
		logger.log(Level.INFO, "NotificationsPresenter.onReveal().getMessagingManager()");

		return messagingManager;
	}

	@Provides
	@Singleton
	AppServiceWorkerManager provideAppServiceWorkerManager(EventBus eventBus, MessagingManager fcmManager,
			RestDispatch dispatch, FcmResource fcmService) {

		AppServiceWorkerManager serviceWorkerManager = new AppServiceWorkerManager("service-worker.js", eventBus,
				fcmManager, dispatch, fcmService);

		return serviceWorkerManager;
	}
}
