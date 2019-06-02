/**
 * 
 */
package io.crs.hsys.client.fro.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.core.gin.CoreModule;
import io.crs.hsys.client.fro.AppModule;
import io.crs.hsys.client.fro.calendar.CalendarModule;
import io.crs.hsys.client.fro.dashboard.DashboardModule;
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

		install(new AppModule());
		install(new DashboardModule());
		install(new ResNewModule());
		install(new ReservationModule());
		install(new CalendarModule());
		install(new RoomPlanModule());

	}
}
