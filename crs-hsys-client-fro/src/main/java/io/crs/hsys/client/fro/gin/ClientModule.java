/**
 * 
 */
package io.crs.hsys.client.fro.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.core.gin.CoreModule;
import io.crs.hsys.client.fro.FroAppModule;
import io.crs.hsys.client.fro.calendar.CalendarModule;
import io.crs.hsys.client.fro.config.rate.RateConfigModule;
import io.crs.hsys.client.fro.dashboard.DashboardModule;
import io.crs.hsys.client.fro.filter.FroFilterModule;
import io.crs.hsys.client.fro.rate.manager.RateManagerModule;
import io.crs.hsys.client.fro.rate.updater.RateUpdaterModule;
import io.crs.hsys.client.fro.reservation.ReservationModule;
import io.crs.hsys.client.fro.resnew.ResNewModule;
import io.crs.hsys.client.fro.roomplan.RoomPlanModule;

/**
 * @author CR
 *
 */
public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new CoreModule());

		bind(ResourceLoader.class).asEagerSingleton();

		install(new FroAppModule());
		install(new DashboardModule());
		install(new ResNewModule());
		install(new ReservationModule());
		install(new CalendarModule());
		install(new RoomPlanModule());
		install(new RateManagerModule());
		install(new RateUpdaterModule());
		install(new FroFilterModule());
		install(new RateConfigModule());
	}
}
