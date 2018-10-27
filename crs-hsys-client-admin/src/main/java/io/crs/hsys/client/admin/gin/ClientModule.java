/**
 * 
 */
package io.crs.hsys.client.admin.gin;

import java.util.logging.Logger;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.admin.AppModule;
import io.crs.hsys.client.admin.config.system.SystemConfigModule;
import io.crs.hsys.client.admin.dashboard.DashboardModule;
import io.crs.hsys.client.core.gin.CoreModule;

/**
 * @author CR
 *
 */
public class ClientModule extends AbstractPresenterModule {
	private static Logger logger = Logger.getLogger(ClientModule.class.getName());

	@Override
	protected void configure() {
		logger.info("ClientModule().configure(");

        install(new CoreModule());

		bind(ResourceLoader.class).asEagerSingleton();

		install(new AppModule());

		install(new DashboardModule());

		install(new SystemConfigModule());
	}
}
