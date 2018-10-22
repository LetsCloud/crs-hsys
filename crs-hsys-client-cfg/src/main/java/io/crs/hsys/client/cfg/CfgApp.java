/**
 * 
 */
package io.crs.hsys.client.cfg;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.PreBootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.core.security.AppData;
import io.crs.hsys.shared.constans.SubSystem;

/**
 * @author CR
 *
 */
public class CfgApp implements Bootstrapper {
	private static Logger logger = Logger.getLogger(CfgApp.class.getName());

	private final PlaceManager placeManager;

	private final AppData appData;

	@Inject
	CfgApp(PlaceManager placeManager, AppData appData) {
		logger.info("App()");
		this.placeManager = placeManager;
		this.appData = appData;
	}

	public static class PreApplicationImpl implements PreBootstrapper {
		private static Logger logger = Logger.getLogger(PreApplicationImpl.class.getName());

		@Override
		public void onPreBootstrap() {
			logger.info("PreApplicationImpl().onPreBootstrap()");
			GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
				public void onUncaughtException(Throwable e) {
					logger.info(e.getMessage());
				}
			});
		}
	}

	@Override
	public void onBootstrap() {
		logger.info("CfgApp().onBootstrap()");
		appData.setAppCode(SubSystem.CFG);
		placeManager.revealCurrentPlace();
	}
}