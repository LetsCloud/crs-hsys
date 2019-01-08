/**
 * 
 */
package io.crs.hsys.client.kip;

import javax.inject.Inject;

import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.core.AbstractAppBootstrapper;
import io.crs.hsys.client.core.app.AppServiceWorkerManager;
import io.crs.hsys.client.core.firebase.messaging.MessagingManager;
import io.crs.hsys.client.core.security.AppData;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.AuthResource;
import io.crs.hsys.shared.api.FcmResource;
import io.crs.hsys.shared.api.GlobalConfigResource;
import io.crs.hsys.shared.constans.SubSystem;

/**
 * @author CR
 *
 */
public class KipApp extends AbstractAppBootstrapper {

	@Inject
	KipApp(PlaceManager placeManager, AppData appData, ResourceDelegate<GlobalConfigResource> globalConfigResource,
			MessagingManager messagingManager, AppServiceWorkerManager swManager, RestDispatch dispatch,
			AuthResource authService, CurrentUser currentUser, FcmResource fcmService) {
		super(placeManager, appData, globalConfigResource, messagingManager, swManager, dispatch, authService,
				currentUser, fcmService);
		setAppCode(SubSystem.KIP);
	}
}