/**
 * 
 */
package io.crs.hsys.client.core.gin;

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
import io.crs.hsys.client.core.firebase.messaging.MessagingManager;
import io.crs.hsys.client.core.menu.MenuModule;
import io.crs.hsys.client.core.security.AppData;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.unauthorized.UnauthorizedModule;
import io.crs.hsys.shared.api.GlobalConfigResource;

/**
 * @author CR
 *
 */
public class CoreModule extends AbstractPresenterModule {
	private static Logger logger = Logger.getLogger(CoreModule.class.getName());

//	private static final String SW_PATH="service-worker.js";
	private static final String SW_PATH = "./service-worker.js";

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

		return messagingManager;
	}

	@Provides
	@Singleton
	AppServiceWorkerManager provideAppServiceWorkerManager(EventBus eventBus, MessagingManager fcmManager,
			RestDispatch dispatch) {
//		logger.info("provideAppServiceWorkerManager()");

		AppServiceWorkerManager serviceWorkerManager = new AppServiceWorkerManager(SW_PATH, fcmManager);

		return serviceWorkerManager;
	}
}
