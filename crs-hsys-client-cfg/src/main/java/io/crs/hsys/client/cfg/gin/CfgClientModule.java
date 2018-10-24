/**
 * 
 */
package io.crs.hsys.client.cfg.gin;

import java.util.logging.Logger;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.cfg.AppModule;
import io.crs.hsys.client.cfg.config.hotel.HotelConfigModule;
import io.crs.hsys.client.cfg.config.profile.ProfileConfigModule;
import io.crs.hsys.client.cfg.config.system.SystemConfigModule;
import io.crs.hsys.client.cfg.creator.contact.ContactCreateModule;
import io.crs.hsys.client.cfg.creator.organization.OrganizationCreateModule;
import io.crs.hsys.client.cfg.dashboard.DashboardModule;
import io.crs.hsys.client.cfg.display.contact.ContactConfigModule;
import io.crs.hsys.client.cfg.display.organization.OrganizationConfigModule;
import io.crs.hsys.client.cfg.filter.FilterModule;
import io.crs.hsys.client.core.gin.CoreModule;

/**
 * @author CR
 *
 */
public class CfgClientModule extends AbstractPresenterModule {
	private static Logger logger = Logger.getLogger(CfgClientModule.class.getName());

	@Override
	protected void configure() {
		logger.info("CfgClientModule().configure(");

        install(new CoreModule());

		bind(CfgResourceLoader.class).asEagerSingleton();

		install(new AppModule());

		install(new DashboardModule());

		install(new SystemConfigModule());
		install(new ProfileConfigModule());
		install(new HotelConfigModule());

		install(new OrganizationConfigModule());
		install(new ContactConfigModule());

		install(new OrganizationCreateModule());
		install(new ContactCreateModule());

		install(new FilterModule());
	}
}