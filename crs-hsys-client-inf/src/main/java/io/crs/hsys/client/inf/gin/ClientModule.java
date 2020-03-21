/**
 * 
 */
package io.crs.hsys.client.inf.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.core.gin.CoreModule;
import io.crs.hsys.client.inf.ana.perf1.Perf1Module;
import io.crs.hsys.client.inf.analytics.AnalyticsModule;
import io.crs.hsys.client.inf.app.InfAppModule;
import io.crs.hsys.client.inf.dashboard.DashboardModule;
import io.crs.hsys.client.inf.dashboard.widget.DataWidgetModule;
import io.crs.hsys.client.inf.gps.GpsState;
import io.crs.hsys.client.inf.gps.display.GpsDisplayModule;

/**
 * @author CR
 *
 */
public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new CoreModule());

		bind(GpsState.class).asEagerSingleton();
		bind(ResourceLoader.class).asEagerSingleton();
		
		install(new InfAppModule());
		install(new GpsDisplayModule());
		install(new DashboardModule());
		install(new DataWidgetModule());
		install(new AnalyticsModule());
		install(new Perf1Module());
	}
}
